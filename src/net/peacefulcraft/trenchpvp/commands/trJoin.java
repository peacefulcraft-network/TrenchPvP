package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.GameManager;

/**
 * User command for joining Trench
 */
public class trJoin implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
		if(command.getName().equalsIgnoreCase("trjoin")){
				
			if(sender instanceof Player){
			
				return GameManager.joinPlayer((Player) sender);
				
			}else{
				
				System.out.println("@" + sender + " Command only executable by players");
				return false;
			
			}
				
		}else{return false;}
	}
	
}
