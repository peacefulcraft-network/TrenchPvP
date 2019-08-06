package net.peacefulcraft.trenchpvp.gamehandle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * Scoreboard Objectives for Trench
 * - The object instance lives in TeamManager
 */
public class TrenchScoreboard {
	
	private Team red;
		public Team getRedTeam() { return red; }
	private Team blue;
		public Team getBlueTeam() { return blue; }
	
	private Scoreboard sb;
	private Objective trenchStats;
	private String time = "10:00";
	/**
	 * Reset / Create Scoreboard Teams and Objectives
	 */
	public TrenchScoreboard() {
		

		ScoreboardManager smb = Bukkit.getScoreboardManager();
		sb = smb.getMainScoreboard(); 
		
		if(sb.getTeam("Red") != null) {
			sb.getTeam("Red").unregister();
		}
		
		if(sb.getTeam("Blue") != null) {
			sb.getTeam("Blue").unregister();
		}
		
		red = sb.registerNewTeam("Red");
			red.setAllowFriendlyFire(false);
			red.setColor(ChatColor.RED);
			red.setCanSeeFriendlyInvisibles(true);
		
		blue = sb.registerNewTeam("Blue");
			blue.setAllowFriendlyFire(false);
			blue.setColor(ChatColor.BLUE);
			blue.setCanSeeFriendlyInvisibles(true);
		
		if(sb.getObjective("trenchStats") != null) {
			sb.getObjective("trenchStats").unregister();
		}
			
		trenchStats = sb.registerNewObjective("trenchStats", "dummy", 
			ChatColor.GREEN + "[" + ChatColor.GOLD + "Trench" + ChatColor.GREEN + "]"
		);
		trenchStats.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		resetScores();
		
		System.out.println("Teams Initialized");
		
	}
	
	/**
	 * Set all scores to 0 for round end
	 */
	public void resetScores() {
		
		trenchStats.getScore(ChatColor.RED + "Players:").setScore(red.getSize());
		trenchStats.getScore(ChatColor.RED + "Kills:").setScore(0);
		
		trenchStats.getScore(ChatColor.BLUE + "Players:").setScore(blue.getSize());
		trenchStats.getScore(ChatColor.BLUE + "Kills:").setScore(0);

	}
	
	/**
	 * Add one count to "redPlayers"
	 */
	public void registerRedPlayer() {
		
		trenchStats.getScore(ChatColor.RED + "Players:").setScore(
			trenchStats.getScore(ChatColor.RED + "Players:").getScore() + 1
		);
	}
	
	/**
	 * Remove one count from "redPlayers"
	 */
	public void unregisterRedPlayer() {
		
		trenchStats.getScore(ChatColor.RED + "Players:").setScore(
			trenchStats.getScore(ChatColor.RED + "Players:").getScore() - 1
		);
	}
	
	/**
	 * Add one count to "bluePlayers"
	 */
	public void registerBluePlayer() {
		
		trenchStats.getScore(ChatColor.BLUE + "Players:").setScore(
			trenchStats.getScore(ChatColor.BLUE + "Players:").getScore() + 1
		);
	
	}
	
	/**
	 * Remove one count from "bluePlayers"
	 */
	public void unregisterBluePlayer() {
		
		trenchStats.getScore(ChatColor.BLUE + "Players:").setScore(
			trenchStats.getScore(ChatColor.BLUE + "Players:").getScore() - 1
		);
		
	}
	
	/**
	 * Add one count to "redKills"
	 */
	public void registerRedKill() {
		
		trenchStats.getScore(ChatColor.RED + "Kills:").setScore(
			trenchStats.getScore(ChatColor.RED + "Kills:").getScore() + 1
		);
		
	}
	
	/**
	 * Add one count to "blueKills"
	 */
	public void registerBlueKill() {
		
		trenchStats.getScore(ChatColor.BLUE + "Kills:").setScore(
			trenchStats.getScore(ChatColor.BLUE + "Kills:").getScore() + 1
		);
		
	}
	
	/**
	 * Change (decrement) timer on scoreboard
	 * @param time: Time as a string (0:00)
	 */
	public void setTimerTime(String time) {
		
		sb.resetScores(this.time);
		trenchStats.getScore(time).setScore(15);
		this.time = time;
		
	}
	
}
