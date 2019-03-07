package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class SugarRushListener implements Listener
{
	@EventHandler
	public void onrightClick(PlayerInteractEvent e)
	{
		Player user = e.getPlayer();
		//Checks item in main hand is Shell
		if(!(user.getInventory().getItemInMainHand().getType() == Material.SUGAR)) return;
		if(!(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Sugar Rush"))) return;
		//Confirms location
		int location = TrenchPlayer.findTrenchPlayer(user);
		if(location == -1) return;
		//Confirms class
		TrenchPlayer player = TrenchTeam.trenchPlayers[location];
		if(!(player.getPlayerClass() == TrenchClass.SPY)) return;
	
		SugarRush sugar = new SugarRush();
		sugar.updateClick();
		if(!(System.currentTimeMillis() >= (sugar.getClick() + 10000))) return;
	
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6, 3));
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4, 2));
		
	}
}
