package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.GameManager;

public class tppToggle implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			if(command.getName().equalsIgnoreCase("tppToggle")){
				Player player = (Player) sender;
				if(player.hasPermission("tpp.admin.tppToggle")){
					if(GameManager.isRunning()){
						GameManager.setGameState(false);
						player.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.WHITE + "Trench PvP is now " + ChatColor.RED + "disabled!");
						return true;
					}
					else{
						GameManager.setGameState(true);;
						player.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.WHITE + "Trench PvP is now " + ChatColor.GREEN + "enabled!");
						return true;
					}
				}else{
					player.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
					return true;
				}
			}else{return false;}
		}else{
			System.out.println("@" + sender +" Command only executable by player");
			return true;
		}
	}
}
