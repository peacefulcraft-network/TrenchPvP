package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class TNTIgnition implements Listener
{
	@EventHandler
	public void ignition(ExplosionPrimeEvent e) {
		if(e.getEntityType() == EntityType.PRIMED_TNT) {
			
			//Spawn a fun particle to indicate that this TNT did not explode
			//Aggressively because it was lit illegally
			Location loc = e.getEntity().getLocation();
			loc.setY(loc.getY()+1);
			loc.getWorld().spawnParticle(Particle.BUBBLE_COLUMN_UP, loc, 10);
			
			e.setCancelled(true);
		}
	}
}
