package net.peacefulcraft.trenchpvp.gamehandle.arena;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.config.ArenaConfig;
import net.peacefulcraft.trenchpvp.config.TrenchConfig;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.PlayerWideExecutor;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchScoreboard;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchTeam;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.ArenaTimer;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Endgame;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.SyncStats;

public class TrenchArena {

	private ArenaConfig ac;
		public String getArenaName() { return ac.getArenaName(); }
		public boolean isArenaActive() { return ac.isArenaActive(); }
		
		public Location getBlueSpawn() { return ac.getBlue_spawn(); }
		public void setBlueSpawn(Location loc) { ac.setBlue_spawn(TrenchConfig.locToMap(loc)); }
		
		public Location getBlueClassSpawn() { return ac.getBlue_class_spawn(); }
		public void setBlueClassSpawn(Location loc) { ac.setBlue_class_spawn(TrenchConfig.locToMap(loc)); }
		
		public Location getRedSpawn() { return ac.getRed_spawn(); }
		public void setRedSpawn(Location loc) { ac.setRed_spawn(TrenchConfig.locToMap(loc)); }
		
		public Location getRedClassSpawn() { return ac.getRed_class_spawn(); }
		public void setRedClassSpawn(Location loc) { ac.setRed_class_spawn(TrenchConfig.locToMap(loc)); }
		
		public Location getSpectatorSpawn() { return ac.getSpectator_spawn(); }
		public void setSpectatorSpawn(Location loc) { ac.setSpectator_spawn(TrenchConfig.locToMap(loc)); }
		
		public boolean isActive() { return ac.isArenaActive(); }
		public void setActive(boolean active) { ac.setArenaActive(active); }
		
		public void saveArenaConfig() { ac.saveAll(); }
		
	private TrenchScoreboard scoreboard;
		public TrenchScoreboard getScoreboard() { return scoreboard; }
	private Team red;
	private Team blue;
	
	private BukkitTask scoreboardTimerTask;
	private BukkitTask endgameTask;
		public BukkitTask getEndgameTask() { return endgameTask; }
	
	private HashMap<UUID, TrenchPlayer> redPlayers;
	private HashMap<UUID, TrenchPlayer> bluePlayers;
		public TrenchPlayer findTrenchPlayer(Player p) {
			if(redPlayers.containsKey(p.getUniqueId())) {
				return redPlayers.get(p.getUniqueId());
			}else if(bluePlayers.containsKey(p.getUniqueId())) {
				return bluePlayers.get(p.getUniqueId());
			}else {
				return null;
			}
		}
	
		public void executeOnAllPlayers(PlayerWideExecutor ex) {
			executeOnAllRedPlayers(ex);
			executeOnAllBluePlayers(ex);
		}
		
		public void executeOnAllRedPlayers(PlayerWideExecutor ex) {
			for(Entry<UUID, TrenchPlayer> entry : redPlayers.entrySet()) {
				ex.execute(entry.getValue());
			}
		}
		
		public void executeOnAllBluePlayers(PlayerWideExecutor ex) {
			for(Entry<UUID, TrenchPlayer> entry : bluePlayers.entrySet()) {
				ex.execute(entry.getValue());
			}
		}

	/**
	 * Create new Trench Arena by name
	 * @param arenaName
	 */
	public TrenchArena(ArenaConfig ac) {
		this.ac = ac;
		
		//TODO: Make scoreboard contextual and only send scoreboard
		//		to players in specific arena. (Can we do this?)
		scoreboard = new TrenchScoreboard();
		red = scoreboard.getRedTeam();
		blue = scoreboard.getBlueTeam();
		
		redPlayers = new HashMap<UUID, TrenchPlayer>();
		bluePlayers = new HashMap<UUID, TrenchPlayer>();
	}
	
	/**
	 * Start the game
	 * - Re-equip all their kits
	 * - Teleport all TrenchPlayers to respective spawn points
	 * - Reset scoreboard objects
	 * - Start timer schedule
	 */
	public void startGame() {
		
		//Reset scoreboard objectives
		scoreboard.resetScores();
		
		//Reset timer
		scoreboardTimerTask = (new ArenaTimer(scoreboard)).runTaskTimer(TrenchPvP.getPluginInstance(), 20, 20);
		endgameTask = (new Endgame(TrenchPvP.getPluginInstance(), this)).runTaskLater(TrenchPvP.getPluginInstance(), 12000);
		
		//Teleport players to their respective locations
		executeOnAllPlayers( (TrenchPlayer t)->{
			if(t.getKitType() != TrenchKits.UNASSIGNED) {
				t.equipKit(t.getKit());
				teleportToSpawn(t);
			}else {
				teleportToClassSelection(t);
			}
		});
		
		Announcer.messageAll("A new game has begun!");
		TrenchPvP.logWarning("Started new game in arena " + getArenaName());
	}
	
	public void endGame() {
		
		// Make sure the timer stops
		BukkitScheduler scheduler = TrenchPvP.getPluginInstance().getServer().getScheduler();
		if(scheduler.isQueued(scoreboardTimerTask.getTaskId()) || scheduler.isCurrentlyRunning(scoreboardTimerTask.getTaskId())) {
			scheduler.cancelTask(scoreboardTimerTask.getTaskId());
		}
		
		Announcer.messageAll("Game over! A new game will begin shortly.");
		TrenchPvP.logWarning("Ended game in arena " + getArenaName());
		//TODO Announce winner
		
		// Async; send stats to the database
		SyncStats sync = new SyncStats();
		sync.commitStats(TrenchPvP.getStatTracker().getStatData());
		TrenchPvP.getStatTracker().clearStats();
		sync.runTaskAsynchronously(TrenchPvP.getPluginInstance());
		
	}
	
	/**
	 * Join a player to a trench team with
	 * automatic team selection
	 * @param p: The Player
	 */
	public void playerJoin(Player p) {
		
		if(TrenchPvP.getTrenchManager().findTrenchPlayer(p) != null) 
			throw new RuntimeException("Command executor is already playing Trench");
		TrenchPlayer t = null;
		
		p.setGameMode(GameMode.ADVENTURE);
		
		if(red.getSize() < blue.getSize()) {
			
			t = joinPlayerToRed(p);
			
		}else {
			
			t = joinPlayerToBlue(p);			
		}
	
		TrenchPvP.getTrenchManager().registerPlayer(t);
	}
	
	/**
	 * Join a player to the specified team with 
	 * optional overriding of balance checks
	 * @param p: The Player
	 * @param t: The team to join
	 * @param force: Override balance checks
	 */
	public void playerJoin(Player p , TrenchTeam tt, boolean force) {
		
		if(TrenchPvP.getTrenchManager().findTrenchPlayer(p) != null) 
			throw new RuntimeException("Command executor is already playing Trench");
		
		p.setGameMode(GameMode.ADVENTURE);
		TrenchPlayer t = null;
		
		if(tt == TrenchTeam.RED) {
			
			if(force) {
				t = joinPlayerToRed(p);
				
			}else {
				//If there is a delta>2 between teamsize, don't allow a force join; just join them to blue
				if(red.getSize() - 2 > blue.getSize()) {
					Announcer.messagePlayer(p, "Sorry, the teams are too imbalanced to join red.");
					t = joinPlayerToBlue(p);
					
				}else {
					t = joinPlayerToRed(p);	
					
				}
				
			}
			
		}else {
		
			if(force) {
				t = joinPlayerToBlue(p);
				
			}else{
				//If there is a delta>2 between teamsize, don't allow a force join; just join them to blue
				if(red.getSize() - 2 > blue.getSize()) {
					Announcer.messagePlayer(p, "Sorry, the teams are too imbalanced to join blue.");
					t = joinPlayerToRed(p);
					
				}else {
					t = joinPlayerToBlue(p);
					
				}
			}
			
		}
		
		TrenchPvP.getTrenchManager().registerPlayer(t);
		
	}
	
		/**
		 * Player join logic for red team
		 * @param p: The Player to join to red
		 */
		private TrenchPlayer joinPlayerToRed(Player p) {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, this, TrenchTeam.RED);
			red.addEntry(p.getName());
			scoreboard.registerBluePlayer();
			redPlayers.put(p.getUniqueId(), t);
			
			//Teleport to class selection
			teleportToClassSelection(t);
			Announcer.messagePlayer(t.getPlayer(), ChatColor.RED + "You have joined Red team!");
			TrenchPvP.getKitMenu().menuOpen(p);
			
			return t;
		}
		
		/**
		 * Player join logic for blue team
		 * @param p: The Player to join to blue
		 */
		private TrenchPlayer joinPlayerToBlue(Player p) {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, this, TrenchTeam.BLUE);
			blue.addEntry(p.getName());
			scoreboard.registerBluePlayer();
			bluePlayers.put(p.getUniqueId(), t);
			
			//Teleport to class selection
			teleportToClassSelection(t);
			Announcer.messagePlayer(t.getPlayer(), "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
			TrenchPvP.getKitMenu().menuOpen(p);
			
			return t;
			
		}
	
	/**
	 * Quits player from the game
	 * @param t: The TrenchPlayer
	 */
	public void playerLeave(Player p) {
		
		TrenchPlayer t = findTrenchPlayer(p);
		if(t == null)
			return;
		
		if(t.getPlayerTeam() == TrenchTeam.RED) {
			redPlayers.remove(t.getPlayer().getUniqueId());
			red.removeEntry(t.getPlayer().getName());
			scoreboard.unregisterRedPlayer();
		}else {
			bluePlayers.remove(t.getPlayer().getUniqueId());
			blue.removeEntry(t.getPlayer().getName());
			scoreboard.unregisterBluePlayer();
		}
		
		t.dequipKits();
		teleportToSpectatorArea(t.getPlayer());
		TrenchPvP.getTrenchManager().unregisterPlayer(t);
	}

	/**
	 * Teleport Player to arena spectator area
	 * @param p
	 */
	public void teleportToSpectatorArea(Player p) {
		p.teleport(ac.getSpectator_spawn());
	}
	
	/**
	 * Teleport TrenchPlayer to respective class selection space
	 * @param t: The TrenchPlayer
	 */
	public void teleportToClassSelection(TrenchPlayer t) {
		if(t.getPlayerTeam() == TrenchTeam.BLUE) {
			t.getPlayer().teleport(ac.getBlue_class_spawn());
		}else {
			t.getPlayer().teleport(ac.getRed_spawn());
		}
	}
	
	/**
	 * Teleport TrenchPlayer to respective class spawn
	 * @param t: The TrenchPlayer
	 */
	public void teleportToSpawn(TrenchPlayer t) {
		if(t.getPlayerTeam() == TrenchTeam.BLUE) {
			t.getPlayer().teleport(ac.getBlue_spawn());
		}else {
			t.getPlayer().teleport(ac.getRed_spawn());
		}
	}
}
