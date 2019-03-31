package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.Teleports;

public class trLeave implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("trleave")) {
			
			if(sender instanceof Player) {
				
				Player p = (Player) sender;
				
				if(p.hasPermission("tpp.player")) {
					
					TrenchPvP.getTeamManager().leaveTeam(p);
					p.teleport(Teleports.getQuitSpawn());
					sender.sendMessage("You've left Trench!");
					return true;
					
				}else {
					
					sender.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
					return false;
					
				}
				
			}else {
				
				sender.sendMessage("Hey, silly! Only player's can join Trench.");
				return false;
				
			}
			
		}
		return false;
	}
}
