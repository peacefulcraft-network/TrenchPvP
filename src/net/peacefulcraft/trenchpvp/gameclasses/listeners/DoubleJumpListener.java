package net.peacefulcraft.trenchpvp.gameclasses.listeners;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class DoubleJumpListener implements Listener
{
	ArrayList<UUID> cooldown = new ArrayList<UUID>();	
	@EventHandler
	public void resetDoubleJump(PlayerMoveEvent e) {
		 Player p = e.getPlayer();
	        
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;
	        
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
			
		if(!(t.getKitType() == TrenchKits.SCOUT)) return;
			
		if(cooldown.contains(p.getUniqueId())) {	
			if(p.isOnGround()) {
				cooldown.remove(p.getUniqueId());
			}
		}
	}
		
    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent e){
        Player p = e.getPlayer();
        
        if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
        	p.setFlying(false);
        	e.setCancelled(true);        	
        	return;
        }

        TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { 
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

		if(cooldown.contains(p.getUniqueId())) {
			p.setFlying(false);
			e.setCancelled(true);
		} else if(!cooldown.contains(p.getUniqueId())) {
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
			}
		}
    }
}
