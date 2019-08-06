package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.config.ArenaConfig;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeam;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.ArenaTimer;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Endgame;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.SyncStats;

public class TrenchArena {

	private ArenaConfig ac;

	private TrenchScoreboard scoreboard;
		public TrenchScoreboard getScoreboard() { return scoreboard; }
	private Team red;
	private Team blue;
	
	private ArenaTimer scoreboardTimer;
	
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
		
		//Teleport all players to the spawn points and reset their kits
		executeOnAllRedPlayers(
			(TrenchPlayer t)->{
				if(t.getKitType() != TrenchKits.UNASSIGNED) {
					t.equipKit(t.getKit());
					t.getPlayer().teleport(ac.getRed_spawn());
				}
			}
		);
		
		executeOnAllBluePlayers(
			(TrenchPlayer t)->{
				if(t.getKitType() != TrenchKits.UNASSIGNED) {
					t.equipKit(t.getKit());
					t.getPlayer().teleport(ac.getBlue_spawn());
				}
			}
		);
		
		//Reset scoreboard objectives
		scoreboard.resetScores();
		
		//Reset timer
		scoreboardTimer = new ArenaTimer(scoreboard);
		scoreboardTimer.runTaskLater(TrenchPvP.getPluginInstance(), 20);
		(new Endgame(TrenchPvP.getPluginInstance(), this)).runTaskLater(TrenchPvP.getPluginInstance(), 12000);
		
		Announcer.messageAll("A new game has begun!.");
		
	}
	
	public void endGame() {
		
		Announcer.messageAll("Game over! A new game will begin shortly.");
		//TODO Announce winner
		
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
		
		p.setGameMode(GameMode.ADVENTURE);
		
		if(red.getSize() < blue.getSize()) {
			
			joinPlayerToRed(p);
			
		}else {
			
			joinPlayerToBlue(p);			
		}
		
	}
	
	/**
	 * Join a player to the specified team with 
	 * optional overriding of balance checks
	 * @param p: The Player
	 * @param t: The team to join
	 * @param force: Override balance checks
	 */
	public void playerJoin(Player p , TrenchTeam t, boolean force) {
		
		if(TrenchPvP.getTrenchManager().findTrenchPlayer(p) != null) 
			throw new RuntimeException("Command executor is already playing Trench");
		
		p.setGameMode(GameMode.ADVENTURE);
		
		if(t == TrenchTeam.RED) {
			
			if(force) {
				joinPlayerToRed(p);
				return;
			}
			
			//If there is a delta>2 between teamsize, don't allow a force join; just join them to blue
			if(red.getSize() - 2 > blue.getSize()) {
				joinPlayerToBlue(p);
				p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "Sorry, the teams are too imbalanced to join red.");
			}else {
				joinPlayerToRed(p);
			}
			
		}else {
		
			if(force) {
				joinPlayerToBlue(p);
				return;
			}
			
			//If there is a delta>2 between teamsize, don't allow a force join; just join them to blue
			if(red.getSize() - 2 > blue.getSize()) {
				joinPlayerToRed(p);
				p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "Sorry, the teams are too imbalanced to join blue.");
			}else {
				joinPlayerToBlue(p);
			}
			
		}
		
	}
	
		/**
		 * Player join logic for red team
		 * @param p: The Player to join to red
		 */
		private void joinPlayerToRed(Player p) {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, this, TrenchTeam.RED);
			blue.addEntry(p.getName());
			scoreboard.registerBluePlayer();
			bluePlayers.put(p.getUniqueId(), t);
			
			//Teleport to class selection
			p.teleport(ac.getBlue_class_spawn());
			p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined Red team!");
			TrenchPvP.getKitMenu().menuOpen(p);
			
		}
		
		/**
		 * Player join logic for blue team
		 * @param p: The Player to join to blue
		 */
		private void joinPlayerToBlue(Player p) {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, this, TrenchTeam.BLUE);
			blue.addEntry(p.getName());
			scoreboard.registerBluePlayer();
			bluePlayers.put(p.getUniqueId(), t);
			
			//Teleport to class selection
			p.teleport(ac.getBlue_class_spawn());
			p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
			TrenchPvP.getKitMenu().menuOpen(p);
			
		}
	
	/**
	 * Quits player from the game
	 * @param t: The TrenchPlayer
	 */
	public void playerLeave(Player p) {
		
		TrenchPlayer t = findTrenchPlayer(p);
		if(t == null)
			return;
		
		TrenchPvP.getTrenchManager().unregisterPlayer(t);
		
		if(t.getPlayerTeam() == TrenchTeam.RED) {
			redPlayers.remove(t.getPlayer().getUniqueId());
			red.removeEntry(t.getPlayer().getName());
			scoreboard.unregisterRedPlayer();
		}else {
			bluePlayers.remove(t.getPlayer().getUniqueId());
			red.removeEntry(t.getPlayer().getName());
			scoreboard.unregisterBluePlayer();
		}
		
		t.dequipKits();
		t.getPlayer().teleport(ac.getQuit_spawn());
		t.getPlayer().sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You've left Trench!");
	}

	/**
	 * Teleport Player to arena spectator area
	 * @param p
	 */
	public void teleportToSpectatorArea(Player p) {
		p.teleport(ac.getSpectator_spawn());
	}
	
	/**
	 * Teleport TrenchPlayer to quit area for arena
	 * @param t: The TrenchPlayer
	 */
	public void teleportToQuitArea(TrenchPlayer t) {
		t.getPlayer().teleport(ac.getQuit_spawn());
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
