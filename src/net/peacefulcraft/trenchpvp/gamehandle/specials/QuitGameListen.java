package net.peacefulcraft.trenchpvp.gamehandle.specials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.peacefulcraft.trenchpvp.gamehande.TeamManager;

public class QuitGameListen implements Listener {
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e){
		
		try {
			TeamManager.findTrenchPlayer(e.getPlayer());
			TeamManager.leaveTeam(e.getPlayer());
		}catch(RuntimeException x) {
			return;
		}
		
	}
}
