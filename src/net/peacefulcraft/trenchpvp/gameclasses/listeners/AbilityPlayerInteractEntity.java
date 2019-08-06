package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class AbilityPlayerInteractEntity implements Listener{

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEntityEvent ev) {
		
		//Only fire if damager and entity are Players
		if(!(ev.getRightClicked() instanceof Player)) { return; }
		
		//Make sure both players are playing Trench
		if(TrenchPvP.getTrenchManager().findTrenchPlayer((Player) ev.getRightClicked()) == null) { return; }
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(ev.getPlayer());
		if(t == null) { return; }
		
		//Ignore damage from players that don't have a class
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		
		t.getKit().getAbilityManager().abilityExecuteLoop(TrenchAbilityType.PLAYER_INTERACT_ENTITY, ev);
		
	}
	
}
