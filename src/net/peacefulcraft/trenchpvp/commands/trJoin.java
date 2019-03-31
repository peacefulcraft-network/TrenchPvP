package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class trJoin implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
		if(command.getName().equalsIgnoreCase("trjoin")){
				
			if(sender instanceof Player){
			
			Player p = (Player) sender;
			
			try {
				TrenchPlayer t = TeamManager.findTrenchPlayer(p);
				sender.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are already on a Trench team! Type /trleave to leave.");
				return false;
			}catch(RuntimeException e) {
				//RuntimeException good, means user is not on a team
			}
			
			if(p.hasPermission("tpp.player")){
				
				TrenchPlayer t = TrenchPvP.getTeamManager().joinTeam(p);
				if(t.getPlayerTeam() == TrenchTeams.BLUE) {
					p.teleport(Teleports.getBlueClassSpawn());
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
				}else {
					p.teleport(Teleports.getRedClassSpawn());
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " team!");
				}

				
				return true;
			
			}else{
			
				p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
				return false;
			
			}
		
			}else{
				
				System.out.println("@" + sender + " Command only executable by players");
				return false;
			
			}
				
		}else{return false;}
	}
	
}
