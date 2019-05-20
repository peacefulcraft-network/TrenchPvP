package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class WindsongBladeListener implements Listener
{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.SCOUT)) return;
		
		if((p.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD))
		{
			if((p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Windsong Blade")))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 3));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
			}
		}
		else
		{
			p.removePotionEffect(PotionEffectType.FAST_DIGGING);
			p.removePotionEffect(PotionEffectType.SPEED);
		}
		
	}        
}
