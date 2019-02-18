package net.peacefulcraft.parsonswy.trenchpvp.gamehande.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.peacefulcraft.parsonswy.trenchpvp.gamehande.TrenchCueHandle;

public class leaveGameListen implements Listener {
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e){
		TrenchCueHandle.leaveTeam(e.getPlayer());
	}
}
