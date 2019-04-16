package net.peacefulcraft.trenchpvp.gamehande;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager.PlayerWideExecutor;
import net.peacefulcraft.trenchpvp.gamehande.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Endgame;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Startgame;

public class GameManager {
	
	private static boolean gameRunning = true;
		public static boolean isRunning() { return gameRunning; }
		public static void setGameState(boolean running) { gameRunning = running; } 
		
	private static boolean allowPvP = true;
		public static boolean isPvPAllowed() { return allowPvP; }
		
	public static boolean joinPlayer(Player p) {
		
		if(p.hasPermission("tpp.player")){
			
			if(GameManager.isRunning()) {
							
				try {
					
					TrenchPlayer t = TeamManager.findTrenchPlayer(p);
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are already on a Trench team! Type /trleave to leave.");
					return false;
					
				}catch(RuntimeException e) {
					//RuntimeException fine, means user is not on a team
				}
				
				TrenchPlayer t = TrenchPvP.getTeamManager().joinTeam(p);
				t.dequipKits();
				if(t.getPlayerTeam() == TrenchTeams.BLUE) {
					p.teleport(Teleports.getBlueClassSpawn());
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
				}else {
					p.teleport(Teleports.getRedClassSpawn());
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " team!");
				}

				
				return true;

			}else {
				
				p.sendMessage(Announcer.getTrenchPrefix() + ChatColor.RED + " Trench is no running right now. Please try again later.");
				return false;
				
			}
	
		}else{
			
			p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
			return false;
		
		}
		
	}	
	
	public static boolean quitPlayer(Player p) {
		
		if(p.hasPermission("tpp.player")) {
			
			TrenchPvP.getTeamManager().leaveTeam(p);
			TeamManager.findTrenchPlayer(p).dequipKits();
			p.setGameMode(GameMode.ADVENTURE);
			p.teleport(Teleports.getQuitSpawn());
			p.sendMessage("You've left Trench!");
			return true;
			
		}else {
			
			p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
			return false;
			
		}
		
	}
	
	public static void kickPlayer(TrenchPlayer t, String reason) {
		Announcer.messagePlayer(t.getPlayer(), reason);
		t.getPlayer().teleport(Teleports.getQuitSpawn());
	}
	
	public static void startGame() {
		
		TeamManager.ExecuteOnAllPlayers(
			(TrenchPlayer t)->{
				
				if(t.getPlayerTeam() == TrenchTeams.RED) {
					t.getPlayer().teleport(Teleports.getRedSpawn());
				}else {
					t.getPlayer().teleport(Teleports.getBlueSpawn());
				}
				
			}
		);
		
		allowPvP = true;
		
		//Schedule Endgame proceedings for 10 minutes from now (in ticks)
		(new Endgame(TrenchPvP.getPluginInstance())).runTaskLater(TrenchPvP.getPluginInstance(), 20 * 60 * 10);
		
	}
	
	//TODO: This will be called by gamehandle.taks.endGame which is Async
	// We should somehow pass all the player stats for the game back to that object / thread
	// And comitt it async'ly to the DB
	public static void endGame() {
		
		allowPvP = false;
		Announcer.messageAll("Game over! A new game will begin shortly.");
		//TODO Announce winner
		//TODO Send player's their stats
		
		
		//Schedule new game for 10 seconds from now (in ticks)
		(new Startgame(TrenchPvP.getPluginInstance())).runTaskLater(TrenchPvP.getPluginInstance(), 200);
		
	}
}
