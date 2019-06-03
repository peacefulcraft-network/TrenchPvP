package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class AbilityClickListener implements Listener {
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		
		//Ignore clicks not from Trench players
		TrenchPlayer t = TeamManager.findTrenchPlayer(e.getPlayer());
		if(t == null) { return; }
		
		//Ignore clicks from players that are classes
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		
		t.getKit().executeClickAbilities();
		
	}
	
}
