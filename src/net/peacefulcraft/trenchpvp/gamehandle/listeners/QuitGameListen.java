package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;

public class QuitGameListen implements Listener {
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e){
		
		try {
			TeamManager.findTrenchPlayer(e.getPlayer()).dequipKits();;
			TrenchPvP.getTeamManager().leaveTeam(e.getPlayer());
		}catch(RuntimeException x) {
			return;
		}
		
	}
}
