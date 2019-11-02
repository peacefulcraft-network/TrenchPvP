package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.arena.MapCycle;
import net.peacefulcraft.trenchpvp.gamehandle.arena.TrenchArena;

/**
 * Handles game cycle on server:
 * - Games in different arenas
 * - Moving players between arenas
 */
public class TrenchManager {

	// This is for enabled maps
	private MapCycle mapCycle;
		public TrenchArena getCurrentArena() { return mapCycle.getCurrentMap(); }
		public MapCycle getActiveArenas() { return mapCycle; }
		
	// This is for all the maps that are configured, even if they are not finished / active
	private ArrayList<TrenchArena> configuredArenas;
		public ArrayList<TrenchArena> getConfiguredArenas(){ return configuredArenas; }
		
	private HashMap<UUID, TrenchPlayer> players;
		public TrenchPlayer findTrenchPlayer(Player p) { return players.get(p.getUniqueId()); }
		public void registerPlayer(TrenchPlayer t) { players.put(t.getPlayer().getUniqueId(), t); }
		public void unregisterPlayer(TrenchPlayer t) { players.remove(t.getPlayer().getUniqueId()); }
		
	public TrenchManager() {
		mapCycle = new MapCycle();
		configuredArenas = new ArrayList<TrenchArena>();
		players = new HashMap<UUID, TrenchPlayer>();
	}
	
	/**
	 * Register a TrenchArena. Add it to the map cycle if active:true
	 * @param arena
	 */
	public void registerArena(TrenchArena arena) {
		configuredArenas.add(arena);
		if(arena.isArenaActive()) {
			activateArena(arena);
		}
	}
	
	/**
	 * Add new TrenchArena into map cycle
	 * @param arena
	 */
	public void activateArena(TrenchArena arena) {
		mapCycle.addArena(arena);
	}
	
	/**
	 * Remove a TrenchArena from the active MapCycle
	 * @param arena
	 */
	public void deactivateArena(TrenchArena arena) {
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
