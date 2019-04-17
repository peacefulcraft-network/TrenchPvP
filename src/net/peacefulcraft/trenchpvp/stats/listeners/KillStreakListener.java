package net.peacefulcraft.trenchpvp.stats.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;

public class KillStreakListener implements Listener
{
	private HashMap<UUID, Integer> reference = new HashMap<UUID, Integer>();
	StatTracker s = new StatTracker();
	@EventHandler
	private void onKillEvent(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(killer);
		} catch(RuntimeException x) {
			return;
		}
		
		s.track(killer.getUniqueId(), GeneralStat.HighestKillStreak, 1);
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
		
		if(s.check(victim.getUniqueId(), GeneralStat.HighestKillStreak) == true) {
			int streak = reference.get(victim.getUniqueId());
			if(streak >= s.getValue(victim.getUniqueId(), GeneralStat.HighestKillStreak)) {
				s.track(victim.getUniqueId(), GeneralStat.HighestKillStreak, streak);
				reference.put(victim.getUniqueId(), 0);
			} else {
				reference.put(victim.getUniqueId(), 0);
			}
		}
	}
}
