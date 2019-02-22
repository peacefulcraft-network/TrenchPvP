package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TrenchCueHandle;

public class trLeave implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			if(command.getName().equalsIgnoreCase("trleave")){
				Player user = (Player) sender;
				if(user.hasPermission("tpp.player")){
					TrenchCueHandle.leaveTeam(user);
					return true;
				}else{
					user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
					return true;
				}
			}else{return false;}
		}else{
			System.out.println("@" + sender + " Command only executable by players");
			return true;
		}
	}
}
