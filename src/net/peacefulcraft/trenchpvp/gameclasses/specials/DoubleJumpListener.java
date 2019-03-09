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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class DoubleJumpListener implements Listener
{
	private Set<UUID> players = new HashSet<UUID>();
	
	@EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SCOUT)) return;
		
        p.setAllowFlight(true);
        p.setFlying(false);
    }
   
    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        
        TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SCOUT)) return;
		
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;
		if(p.isFlying()) return;
		
		players.add(p.getUniqueId());
		
		e.setCancelled(true);
        
        p.setAllowFlight(false);
        p.setFlying(false);
       
        p.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5).setY(1));
        p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0f, -5.0f);
       
        p.setFallDistance(100);
    }
    @EventHandler
    public void removePlayer(PlayerQuitEvent e) {
       Player p = e.getPlayer();
       if (players.contains(p.getUniqueId())) players.remove(p.getUniqueId());
       
    }
    
}
