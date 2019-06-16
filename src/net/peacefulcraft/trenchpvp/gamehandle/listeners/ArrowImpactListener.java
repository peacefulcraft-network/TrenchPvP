package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowImpactListener implements Listener{

	/*
	 * Prevent arrows from sticking in players / around the map
	 */
	@EventHandler(priority = EventPriority.LOWEST)
	public void onArrowImpact(ProjectileHitEvent ev) {
		Projectile p = ev.getEntity();
		if(p instanceof AbstractArrow) {
			p.remove();
		}
	}
	
}
