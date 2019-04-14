package net.peacefulcraft.trenchpvp.gamehande;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class GameManager {
	
	private static boolean gameRunning = false;
		public static boolean isRunning() { return gameRunning; }
		
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
	
}
