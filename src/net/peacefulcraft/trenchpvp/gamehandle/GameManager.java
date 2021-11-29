package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchUndefined;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.CombatLoggerListener;
import net.peacefulcraft.trenchpvp.gamehandle.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Endgame;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Startgame;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.SyncBounties;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.SyncStats;

public class GameManager {
	
	private static boolean gameRunning = true;
		public static boolean isRunning() { return gameRunning; }
		public static void setGameState(boolean running) { gameRunning = running; } 
		
	private static boolean allowPvP = true;
		public static boolean isPvPAllowed() { return allowPvP; }
	
	//Game tasks for general cleanup that should be run synchronously pre/post round
	public static List<BukkitRunnable> preGameTasks = new LinkedList<BukkitRunnable>();
	public static List<BukkitRunnable> postGameTasks = new LinkedList<BukkitRunnable>();
		public static void registerPreGameTask(BukkitRunnable task) { preGameTasks.add(task); }
		public static void registerPostGameTask(BukkitRunnable task) { postGameTasks.add(task); }
	
	public static boolean joinPlayer(Player p) {
		
			if(GameManager.isRunning()) {
							
				TrenchPlayer t = TeamManager.findTrenchPlayer(p);
				if(t != null) {
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are already on a Trench team! Type /trleave to leave.");
					return true;
				}
				
				t = TrenchPvP.getTeamManager().joinTeam(p);
				t.equipKit(new TrenchUndefined(t));
				if(t.getPlayerTeam() == TrenchTeams.BLUE) {
					p.teleport(Teleports.getBlueClassSpawn());
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
				}else {
					p.teleport(Teleports.getRedClassSpawn());
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " team!");
				}

				TrenchPvP.getKitMenu().menuOpen(p);
				
				return true;

			}else {
				
				p.sendMessage(Announcer.getTrenchPrefix() + ChatColor.RED + " Trench is no running right now. Please try again later.");
				return false;
				
			}
		
	}	
	
	public static boolean quitPlayer(Player p) {
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) {
			return false;
		}
			
		t.dequipKits();
		TrenchPvP.getTeamManager().leaveTeam(p);
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(Teleports.getQuitSpawn());
		p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You've left Trench!");
		return true;			
		
	}
	
	public static void startGame() {
		
		TeamManager.ExecuteOnAllPlayers(
			(TrenchPlayer t)->{
				
				t.equipKit(t.getKit());
				if(t.getPlayerTeam() == TrenchTeams.RED) {
					t.getPlayer().teleport(Teleports.getRedSpawn());
				}else {
					t.getPlayer().teleport(Teleports.getBlueSpawn());
				}
				
			}
		);
		
		allowPvP = true;
		
		//Execute registered start game tasks
		for(BukkitRunnable task : preGameTasks) {
			task.runTask(TrenchPvP.getPluginInstance());
		}
		
		//Schedule Endgame proceedings for 10 minutes from now (in ticks)
		(new Endgame(TrenchPvP.getPluginInstance())).runTaskLater(TrenchPvP.getPluginInstance(), 20 * 60 * 10);
		
	}
	
	// We should somehow pass all the player stats for the game back to that object / thread
	// And comitt it async'ly to the DB
	public static void endGame() {
		
		allowPvP = false;
		Announcer.messageAll("Game over! A new game will begin shortly.");
		//TODO Announce winner
		
		SyncStats sync = new SyncStats();
		sync.commitStats(TrenchPvP.getStatTracker().getStatData());
		sync.runTaskAsynchronously(TrenchPvP.getPluginInstance());
		
		SyncBounties bounties = new SyncBounties();
		bounties.commitBounties(TrenchPvP.getStatTracker().getBounties());
		bounties.runTaskAsynchronously(TrenchPvP.getPluginInstance());
		
		TrenchPvP.getStatTracker().clearStats();
		
		//Execute registered post game tasks
		for(BukkitRunnable task : postGameTasks) {
			task.runTask(TrenchPvP.getPluginInstance());
		}

		//Clearing all combat logs
		CombatLoggerListener.clearAll();
		
		//Schedule new game for 10 seconds from now (in ticks)
		(new Startgame(TrenchPvP.getPluginInstance())).runTaskLater(TrenchPvP.getPluginInstance(), 200);
		
	}
	
	public static void closeGame() {
		
		allowPvP = false;
		Announcer.messageAll("Game over! Trench is going down for maintenenace.");
		
		TeamManager.ExecuteOnAllPlayers(
			(TrenchPlayer t)->{
				
				GameManager.quitPlayer(t.getPlayer());
				
			}
		);
		
		//SyncStats sync = new SyncStats();
		//sync.commitStats(TrenchPvP.getStatTracker().getStatData());
		//TrenchPvP.getStatTracker().clearStats();
		//sync.runTaskAsynchronously(TrenchPvP.getPluginInstance());
		
		//Execute registered post game tasks
		//for(BukkitRunnable task : postGameTasks) {
		//	task.runTask(TrenchPvP.getPluginInstance());
		//}
		
		
	}
}
