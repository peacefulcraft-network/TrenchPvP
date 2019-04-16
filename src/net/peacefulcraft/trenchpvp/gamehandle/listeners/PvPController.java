package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.gamehande.GameManager;

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
		
		//GameManager doesn't allow PvP
		if(!GameManager.isPvPAllowed()) {
			e.setCancelled(true);
			return;
		}
		
	}
	
}
