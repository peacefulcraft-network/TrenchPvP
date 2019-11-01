package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class PlayerRespawning implements Listener{

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		
			TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
			if(t == null)
				return;
			
			//Reset kit
			t.equipKit(t.getKit());
			
			//Teleport player
			TrenchPvP.getTrenchManager().getCurrentArena().teleportToSpawn(t); 
			
			return;
			
	}
	
}
