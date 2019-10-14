package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;

/**
 * Handles game cycle on server:
 * - Games in different arenas
 * - Moving players between arenas
 */
public class TrenchManager {

	private MapCycle mapCycle;
		public TrenchArena getCurrentArena() { return mapCycle.getCurrentMap(); }
	
	private HashMap<UUID, TrenchPlayer> players;
		public TrenchPlayer findTrenchPlayer(Player p) { return players.get(p.getUniqueId()); }
		public void registerPlayer(TrenchPlayer t) { players.put(t.getPlayer().getUniqueId(), t); }
		public void unregisterPlayer(TrenchPlayer t) { players.remove(t.getPlayer().getUniqueId()); }
		
	public TrenchManager() {
		mapCycle = new MapCycle();
		players = new HashMap<UUID, TrenchPlayer>();
	}
	
	/**
	 * Register new TrenchArena into map cycle
	 * @param arena
	 */
	public void registerArena(TrenchArena arena) {
		mapCycle.addArena(arena);
	}
	
	public void removeArena(TrenchArena arena) {
		if(mapCycle.getCurrentMap() == arena) {
			TrenchPvP.logErrors(
					"Warning, attempted to unregister arena while game is in session."
					+ "Cycle the map, then attempt to disable the map."
			);
		}
	}
	
	public void startMapCycle() {
		mapCycle.getCurrentMap().startGame();
	}
}
