package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class AbilityPlayerMoveListener implements Listener{

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		
		//Trench players should be in adventure mode
		if(ev.getPlayer().getGameMode() != GameMode.ADVENTURE) { return; }
		
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(ev.getPlayer());
		if(t == null) { return; }
		
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		
		t.getKit().getAbilityManager().abilityExecuteLoop(TrenchAbilityType.PLAYER_MOVE, ev);
	}
	
}
