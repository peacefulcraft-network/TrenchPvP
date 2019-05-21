package net.peacefulcraft.trenchpvp.stats.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;

public class KillStreakListener implements Listener
{
	private HashMap<UUID, Integer> reference = new HashMap<UUID, Integer>();
	@EventHandler
	private void onKillEvent(PlayerDeathEvent e) {
		
		if( !(e.getEntity().getKiller() instanceof Player) ) { return; }
		
		Player killer = e.getEntity().getKiller();
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(killer);
		if(t == null) {
			return;
		}
		
		TrenchPvP.getStatTracker().track(killer.getUniqueId(), GeneralStat.player_highest_kill_streak, 1);
		if(reference.containsKey(killer.getUniqueId())) {
			reference.put(killer.getUniqueId(), reference.get(killer.getUniqueId()) + 1);
		} else if(!(reference.containsKey(killer.getUniqueId()))) {
			reference.put(killer.getUniqueId(), 1);
		}
	}
	/*
	 * Compares killstreak to reference on death of player
	 */
	@EventHandler
	private void onDeathEvent(PlayerDeathEvent e) {
		Player victim = e.getEntity().getPlayer();
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(victim);
		} catch(RuntimeException x) {
			return;
		}
		
		if(TrenchPvP.getStatTracker().check(victim.getUniqueId(), GeneralStat.player_highest_kill_streak) == true) {
			if(reference.containsKey(victim.getUniqueId())) {
				int streak = reference.get(victim.getUniqueId());
				if(streak >= TrenchPvP.getStatTracker().getValue(victim.getUniqueId(), GeneralStat.player_highest_kill_streak)) {
					TrenchPvP.getStatTracker().track(victim.getUniqueId(), GeneralStat.player_highest_kill_streak, streak);
					reference.put(victim.getUniqueId(), 0);
				} else {
					reference.put(victim.getUniqueId(), 0);
				}
			} 
		}
	}
}
