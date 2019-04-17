package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;

public class ConsumeListener implements Listener
{
	StatTracker s = new StatTracker();
	@EventHandler
	private void consumeEvent(PlayerItemConsumeEvent e) {
		ItemStack food = e.getItem();
		Player p = e.getPlayer();
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		} catch(RuntimeException x) {
			return;
		}
		TrenchKits kit = t.getKitType();
		
		if(food.getType() == Material.BREAD) {
			s.track(p.getUniqueId(), GeneralStat.BreadEaten, 1);
		}
		if(food.getType() == Material.POTION) {
			s.track(p.getUniqueId(), GeneralStat.HealthPotionUsage, 1);
		}
	}
}
