package net.peacefulcraft.trenchpvp.gameclasses.listeners;





import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class DoubleJumpListener implements Listener
{
	HashMap<UUID, Integer> cooldown = new HashMap<UUID, Integer>();	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		p.setAllowFlight(true);
		p.setFlying(false);
    }
	@EventHandler
	public void resetDoubleJump(PlayerMoveEvent e) {
		 Player p = e.getPlayer();
	        
		 if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;
	        
	     TrenchPlayer t;
	     try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
			
		if(!(t.getKitType() == TrenchKits.SCOUT)) return;
			
		if(cooldown.containsKey(p.getUniqueId())) {	
			if(cooldown.get(p.getUniqueId()) == 1 && p.isOnGround()) {
				cooldown.put(p.getUniqueId(), 0);
			}
		}
	}
		
    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent e){
        Player p = e.getPlayer();
        
        if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;
        
        TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			p.setFlying(false);
			e.setCancelled(true);			
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SCOUT)) 
			{	
				p.setFlying(false);
				e.setCancelled(true);
				return;
			}
		if(cooldown.containsKey(p.getUniqueId())) {
			if(cooldown.get(p.getUniqueId()) == 0) {
				if(p.getGameMode() != GameMode.CREATIVE) {
					//Double Jump code
					p.setFlying(false);
					e.setCancelled(true);
			       	
				    Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0,2,0));
				    if(!b.getType().equals(Material.AIR)){
				       
				     	Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
				       	Vector forward = p.getLocation().getDirection().multiply(0.3);
				        Vector jump = p.getLocation().getDirection().multiply(0.05).setY(1);
				        v.add(forward).add(jump);
				        p.setVelocity(v);     
				        
				        cooldown.put(p.getUniqueId(), 1);
				    }
				}
			} else if(cooldown.get(p.getUniqueId()) == 1){
				p.setFlying(false);
				e.setCancelled(true);
				return;
			}
		} else {
			if(p.getGameMode() != GameMode.CREATIVE) {
				//Double Jump code
				p.setFlying(false);
				e.setCancelled(true);
		       	
			    Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0,2,0));
			    if(!b.getType().equals(Material.AIR)){
			       
			     	Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
			       	Vector forward = p.getLocation().getDirection().multiply(0.3);
			        Vector jump = p.getLocation().getDirection().multiply(0.07).setY(1);
			        v.add(forward).add(jump);
			        p.setVelocity(v);    
			        
			        cooldown.put(p.getUniqueId(), 1);
			    }
			}
		}
    }
}
