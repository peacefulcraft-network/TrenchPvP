package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;

public class FriendlykillListener
{
	@EventHandler
	private void friendlyKill(PlayerDeathEvent e) {
		Player agent = e.getEntity().getKiller();
		Player victim = e.getEntity();
		TrenchPlayer killer, vic;
		try {
			killer = TeamManager.findTrenchPlayer(agent);
			vic = TeamManager.findTrenchPlayer(victim);
		} catch(RuntimeException x) {
			return;
		}
		if(killer.getPlayerTeam() == vic.getPlayerTeam()) {
			TrenchPvP.getStatTracker().track(agent.getUniqueId(), GeneralStat.player_friendly_kills, 1);
		}
	}
}
