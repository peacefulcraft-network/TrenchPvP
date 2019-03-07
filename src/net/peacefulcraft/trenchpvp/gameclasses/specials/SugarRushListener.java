package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class SugarRushListener implements Listener
{
	@EventHandler
	public void onrightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		//Checks item in main hand is Shell
		if(!(p.getInventory().getItemInMainHand().getType() == Material.SUGAR)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Sugar Rush"))) return;
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SPY)) return;
	
		SugarRush sugar = new SugarRush();
		sugar.updateClick();
		if(!(System.currentTimeMillis() >= (sugar.getClick() + 10000))) return;
	
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6, 3));
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 4, 2));
		
	}
}
