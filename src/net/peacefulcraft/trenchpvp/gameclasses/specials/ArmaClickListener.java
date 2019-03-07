package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.specials.ArmadilloShell;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class ArmaClickListener implements Listener
{
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player user = e.getPlayer();
		//Checks item in main hand is Shell
		if(!(user.getInventory().getItemInMainHand().getType() == Material.SHULKER_SHELL)) return;
		if(!(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "Armadillo Shell")) return;
		//if(!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		//Confirms location
		int location = TrenchPlayer.findTrenchPlayer(user);
		if(location == -1) return;
		//Confirms class
		TrenchPlayer player = TrenchTeam.trenchPlayers[location];
		if(!(player.getPlayerClass() == TrenchClass.HEAVY)) return;
		
		user.sendMessage("It worked.");
		
		ArmadilloShell shell = new ArmadilloShell();
		shell.updateClick();
		//Check for cooldown time 14 seconds
		if(!(System.currentTimeMillis() >= (shell.getClick() + 14000))) return;
		//Potion effects
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 4));
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10, 1));
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 5));
	}
}
