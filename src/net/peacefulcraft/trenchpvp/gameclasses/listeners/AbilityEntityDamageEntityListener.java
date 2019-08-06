package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class AbilityEntityDamageEntityListener implements Listener{

	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent ev) {
		
		//Only fire if damager and entity are Players
		if(!(ev.getDamager() instanceof Player)) { return; }
		if(!(ev.getEntity() instanceof Player)) { return; }
		
		//Make sure both players are in Trench
		if(TrenchPvP.getTrenchManager().findTrenchPlayer((Player) ev.getEntity()) == null) { return; }
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) ev.getDamager());
		if(t == null) { return; }
		
		//Ignore damage from players that don't have a class
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		
		t.getKit().getAbilityManager().abilityExecuteLoop(TrenchAbilityType.ENTITY_DAMAGE_ENTITY, ev);
		
	}
	
}
