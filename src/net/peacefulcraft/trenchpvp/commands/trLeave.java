package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehande.GameManager;

public class trLeave implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("trleave")) {
			
			if(sender instanceof Player) {
				
				return GameManager.quitPlayer((Player) sender);
				
			}else {
				
				sender.sendMessage("Hey, silly! Only player's can join Trench.");
				return false;
				
			}
			
		}
		return false;
	}
}
