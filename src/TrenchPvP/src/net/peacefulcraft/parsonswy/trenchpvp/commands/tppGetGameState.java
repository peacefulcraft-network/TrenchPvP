package net.peacefulcraft.parsonswy.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.parsonswy.trenchpvp.TrenchPvP;

public class tppGetGameState implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			if(command.getName().equalsIgnoreCase("tppGetGameState")){
				Player player = (Player) sender;
				if(player.hasPermission("tpp.admin.tppGetGameState")){
					player.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.WHITE + " Trench Running State is Currently Set to " + TrenchPvP.gameRunning);
					return true;
				}else{
					player.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
					return true;
				}
			}else{return false;}
		}else{
			System.out.println("@" + sender + " Command only executable by players");
			return true;
		}
	}

}
