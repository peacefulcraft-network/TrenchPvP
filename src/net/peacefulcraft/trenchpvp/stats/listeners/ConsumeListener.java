package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;

public class ConsumeListener implements Listener
{
	@EventHandler
	private void consumeEvent(PlayerItemConsumeEvent e) {
		ItemStack food = e.getItem();
		Player p = e.getPlayer();
		
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(p);
		if(t == null) {
			return;
		}
		
		if(food.getType() == Material.BREAD) {
			TrenchPvP.getStatTracker().track(t, GeneralStat.player_bread_eaten, 1);
		}
		if(food.getType() == Material.POTION) {
			TrenchPvP.getStatTracker().track(t, GeneralStat.player_health_potions_drank, 1);
		}
	}
}
