package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

/**
 * User command for joining Trench
 */
public class trJoin implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
		if(command.getName().equalsIgnoreCase("trjoin")){
				
			if(sender instanceof Player){
			
				TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) sender);
				if(t == null) {
					TrenchPvP.getTrenchManager().getCurrentArena().playerJoin((Player) sender); 
					return true;
				}else {
					Announcer.messagePlayer(sender, "You are already playing TrenchPvP.");
					return true;
				}
				
			}else{
				
				System.out.println("@" + sender + " Command only executable by players");
				return false;
			
			}
				
		}else{return false;}
	}
	
}
