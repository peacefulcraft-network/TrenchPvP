package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.specials.ArmadilloShell;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class ArmaClickListener implements Listener
{
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		//Checks item in main hand is Shell
		if(!(p.getInventory().getItemInMainHand().getType() == Material.SHULKER_SHELL)) return;

		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Armadillo Shell"))) return;
		//if(!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		//Confirms location

		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.HEAVY)) return;
		
		p.sendMessage("It worked.");
		
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
