package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TNTIgnition implements Listener
{
	@EventHandler
	public void ignition(EntityExplodeEvent e) {
		if(e.getEntityType() == EntityType.PRIMED_TNT) {
			e.setCancelled(true);
		}
	}
}
