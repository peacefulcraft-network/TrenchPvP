package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.gamehandle.GameManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;

public class PvPController implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		
		//We only want PvP Events
		if( !(e.getEntity() instanceof Player) ){
			return;
		}
		
		if( !(e.getDamager() instanceof Player) ) {
			return;
		}
		
		if(TeamManager.findTrenchPlayer((Player) e.getEntity()) == null || TeamManager.findTrenchPlayer((Player) e.getDamager()) == null){
			return;
		}
		//GameManager doesn't allow PvP
		if(!GameManager.isPvPAllowed()) {
			e.setCancelled(true);
			return;
		}
		
	}
	
}
