package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
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
		if(arena.isArenaConfigured() && arena.isArenaActive()) {
			activateArena(arena);
		}
	}
	
	/**
	 * Add new TrenchArena into map cycle
	 * @param arena
	 */
	public void activateArena(TrenchArena arena) throws RuntimeException {
		if (arena.isArenaConfigured()) {
			mapCycle.addArena(arena);
			arena.setActive(true);
		} else {
			TrenchPvP.logWarning("Attempted to activate arena " + arena.getArenaName() + " before it was fully configured.");
			throw new RuntimeException("Attempted to activate arena " + arena.getArenaName() + " before it was fully configured.");
		}
	}
	
	/**
	 * Remove a TrenchArena from the active MapCycle
	 * @param arena
	 */
	public boolean deactivateArena(TrenchArena arena) {
		if(mapCycle.getCurrentMap() == arena) {
			TrenchPvP.logErrors(
					"Warning, attempted to unregister arena while game is in session."
					+ "Cycle the map, then attempt to disable the map."
			);
			return false;
		}
		mapCycle.removeArena(arena);
		arena.setActive(false);
		return true;
	}
	
	/**
	 * Start the mapcycle on first configured map
	 */
	public void startMapCycle() {
		if (!mapCycle.hasMaps()) {
			TrenchPvP.logInfo("No maps configured. Not starting map cycle.");
			return;
		}

		mapCycle.getCurrentMap().startGame();
	}
	
	/**
	 * End current game, move map cycle to next arena, start next game
	 */
	public void cycleMap() {
		TrenchArena oldMap = getCurrentArena();
		mapCycle.nextMap();
		TrenchArena newMap = getCurrentArena();
		
		// Kick everyone out of the old arena and force join them to the same teams in the new one
		oldMap.executeOnAllPlayers( (t) -> {
			Player p = t.getPlayer();
			TrenchTeam team = t.getPlayerTeam();
			
			oldMap.playerLeave(p);
			newMap.playerJoin(p, team, true);
		});
		
		// Teleport spectators to new arena lobby
		Bukkit.getServer().getOnlinePlayers().forEach(player -> {
			if (TrenchPvP.getTrenchManager().findTrenchPlayer(player) == null) {
				player.teleport(newMap.getSpectatorSpawn());
			}
		});

		newMap.startGame();
	}

	/**
	 * Joins a player to the currently active Trench arena, assigning them to a random team
	 * @param p Player to join to Trench
	 */
	public void joinPlayerToGame(Player p) throws RuntimeException {
		if (findTrenchPlayer(p) == null) {
			if (mapCycle.getCurrentMap() == null) {
				Announcer.messagePlayer(p, "No Trench games are running right now. Please wait a few minutes; we'll be back soon!");
			} else {
				mapCycle.getCurrentMap().playerJoin(p);
			}
		} else {
			TrenchPvP.logErrors("Attempted to join player " + p.getDisplayName() + " to Trench, but they were already playing.");
			throw new RuntimeException("Player " + p.getDisplayName() + " is already playing Trench and can not join twice");
		}
	}

	/**
	 * Joins a player to the currently active Trench game. Will attempt to place them on the
	 * requested team, so long as each team will still have no more than a 2 player discrepency.
	 * @param p Player to join to Trench
	 * @param team Team preference
	 * @param force Skip the team balance check and join to team preference
	 */
	public void joinPlayerToGame(Player p, TrenchTeam team, boolean force) throws RuntimeException {
		if (findTrenchPlayer(p) == null) {
			if (mapCycle.getCurrentMap() == null) {
				Announcer.messagePlayer(p, "No Trench games are running right now. Please wait a few minutes; we'll be back soon!");
			} else {
				mapCycle.getCurrentMap().playerJoin(p, team, force);
			}
		} else {
			TrenchPvP.logErrors("Attempted to join player " + p.getDisplayName() + " to Trench, but they were already playing.");
			throw new RuntimeException("Player " + p.getDisplayName() + " is already playing Trench and can not join twice");
		}
	}

	public void removePlayerFromGame(Player p) throws RuntimeException {
		if (findTrenchPlayer(p) == null) {
			TrenchPvP.logErrors("Attempted to remove player " + p.getDisplayName() + " from Trench, but they are not playing.");
			throw new RuntimeException("Player " + p.getDisplayName() + " is not playing Trench and can not be removed from the game.");
		} else {
			mapCycle.getCurrentMap().playerLeave(p);
		}
	}
}
