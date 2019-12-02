package net.peacefulcraft.trenchpvp.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchTeam;

/**
 * User command for joining Trench
 */
public class trjoin implements CommandExecutor, TabCompleter{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
		if(command.getName().equalsIgnoreCase("trjoin")){
				
			if(sender instanceof Player){
			
				TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) sender);
				if(t != null) {
					Announcer.messagePlayer(sender, "You are already playing TrenchPvP.");
					return true;
				}
				
				if(args.length > 0 && sender.hasPermission("trenchpvp.prem.teamjoin")) {
						
					if(args[0].equalsIgnoreCase("blue")) {
						TrenchPvP.getTrenchManager().getCurrentArena().playerJoin((Player) sender, TrenchTeam.BLUE, false);
						
					}else if(args[0].equalsIgnoreCase("red")) {
						TrenchPvP.getTrenchManager().getCurrentArena().playerJoin((Player) sender, TrenchTeam.RED, false);
						
					}else if(args[0].trim().length() > 0){
						Announcer.messagePlayer(sender, "Invalid team supplied. trjoin [ blue | red ]");
						
					}
					
					return true;
					
				}else {
					TrenchPvP.getTrenchManager().getCurrentArena().playerJoin((Player) sender); 
					return true;
				}
				
			}else{
				System.out.println("@" + sender + " Command only executable by players");
				return false;
			
			}
				
		}else{
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
	
		if(! (sender instanceof Player)) { return null; }
		if(! (command.getName().equalsIgnoreCase("trjoin"))) { return null; }
		
		List<String> opts = new ArrayList<String>();
		
		if(args.length > 0 && sender.hasPermission("trenchpvp.perm.teamjoin")) {
			
			opts.add("blue");
			opts.add("red");
			
		}
		
		return opts;
		
	}
	
}
