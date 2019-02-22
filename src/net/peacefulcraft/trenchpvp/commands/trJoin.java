package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TrenchCueHandle;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class trJoin implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			if(command.getName().equalsIgnoreCase("trjoin")){
				Player user = (Player) sender;
				if(TrenchPlayer.findTrenchPlayer(user) == -1){
					if(user.hasPermission("tpp.player")){
						TrenchCueHandle.joinTeam(user);//Join game / tp player to spawn for class choosing
						if(TrenchTeam.trenchPlayers[TrenchPlayer.findTrenchPlayer(user)].getPlayerTeam() == TrenchTeam.BLUE)
							user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
						else
							user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " team!");
						return true;
					}else{
						user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
						return true;
					}
				}else{user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are already on a Trench team! Type /trleave to leave."); 
					return true;}
			}else{return false;}
		}else{
			System.out.println("@" + sender + " Command only executable by players");
			return true;
		}
	}
}
