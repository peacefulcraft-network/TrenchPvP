package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

/**
 * User command for leaving Trench
 */
public class trLeave implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("trleave")) {
			
			if(sender instanceof Player) {
				
				TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) sender);
				if(t == null) {
					Announcer.messagePlayer(sender, "You are not playing TrenchPvP.");
					return true;
				}
				
				TrenchPvP.getTrenchManager().getCurrentArena().playerLeave((Player) sender);
				Announcer.messagePlayer(sender, "You've left TrenchPvP.");
				return true;
				
			}else {
				
				sender.sendMessage("Hey, silly! Only player's can join Trench.");
				return false;
				
			}
			
		}
		return false;
	}
}
