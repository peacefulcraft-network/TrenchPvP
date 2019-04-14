package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.peacefulcraft.trenchpvp.gamehande.GameManager;

public class QuitGameListen implements Listener {
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e){
		
		GameManager.quitPlayer(e.getPlayer());
		
	}
}
