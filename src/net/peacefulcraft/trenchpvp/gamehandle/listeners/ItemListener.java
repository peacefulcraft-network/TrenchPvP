package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemListener implements Listener
{
	@EventHandler
	private void dropEvent(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	
}
