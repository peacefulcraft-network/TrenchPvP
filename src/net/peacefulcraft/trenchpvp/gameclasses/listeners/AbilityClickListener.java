package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class AbilityClickListener implements Listener {
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent ev) {
		
		//Ignore left clicks
		if(!(ev.getAction().equals(Action.RIGHT_CLICK_AIR) || ev.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {return;}
		
		//Ignore clicks not from Trench players
		TrenchPlayer t = TeamManager.findTrenchPlayer(ev.getPlayer());
		if(t == null) { return; }
		
		//Ignore clicks from players that don't have classes
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		
		if(ev.getAction().equals(Action.PHYSICAL)) { return; }
		
		t.getKit().getAbilityManager().abilityExecuteLoop(TrenchAbilityType.PLAYER_INTERACT, ev);
		
		
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEntityEvent ev) {

		//Ignore clicks not from Trench players
		TrenchPlayer t = TeamManager.findTrenchPlayer(ev.getPlayer());
		if(t == null) { return; }
		
		//Ignore clicks from players that don't have classes
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		
		//Execute ability loop
		t.getKit().getAbilityManager().abilityExecuteLoop(TrenchAbilityType.PLAYER_INTERACT, ev);
		
	}
	
}
