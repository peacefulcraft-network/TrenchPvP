package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class JoinGameListen implements Listener {

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		e.getPlayer().teleport(TrenchPvP.getTrenchManager().getCurrentArena().getSpectatorSpawn());
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(new ItemStack[4]);
	}
	
}
