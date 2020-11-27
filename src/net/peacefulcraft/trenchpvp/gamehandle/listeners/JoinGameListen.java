package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.arena.TrenchArena;

public class JoinGameListen implements Listener {

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		TrenchArena currentArena = TrenchPvP.getTrenchManager().getCurrentArena();
		if (currentArena == null) {
			e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		} else{
			e.getPlayer().teleport(TrenchPvP.getTrenchManager().getCurrentArena().getSpectatorSpawn());
		}

		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(new ItemStack[4]);
	}
	
}
