package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchTeam;

public class PlayerRespawning implements Listener{

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
		if(t == null)
			return;
		
		//Reset kit
		t.equipKit(t.getKit().getKitType());
		
		//Teleport player
		Location spawnLoc = null;
		if(t.getPlayerTeam() == TrenchTeam.BLUE) {
			spawnLoc = TrenchPvP.getTrenchManager().getCurrentArena().getBlueSpawn();
		}else {
			spawnLoc = TrenchPvP.getTrenchManager().getCurrentArena().getRedSpawn();
		}
		e.setRespawnLocation(spawnLoc);
		return;
			
	}
	
}
