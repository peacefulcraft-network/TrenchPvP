package net.peacefulcraft.trenchpvp.gameclasses.specials;





import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class DoubleJumpListener implements Listener
{
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		p.setAllowFlight(true);
		p.setFlying(false);
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
		
        if(p.getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
            Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0,2,0));
            if(!b.getType().equals(Material.AIR)){
       
                Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
                Vector forward = p.getLocation().getDirection().multiply(0.3);
                Vector jump = p.getLocation().getDirection().multiply(0.07).setY(1);
                v.add(forward).add(jump);
                p.setVelocity(v);
            	
            }
        }
    }
}
