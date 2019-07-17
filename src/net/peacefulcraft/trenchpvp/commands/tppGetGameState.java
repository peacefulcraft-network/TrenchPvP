package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.GameManager;

/**
 * Admin command used to see if Trench is running
 */
public class tppGetGameState implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { return false; }
		
		if(!command.getName().equalsIgnoreCase("tppGetGameState")) { return false; }
		
		Player player = (Player) sender;
		
		if(!(player.isOp())) { return false; }
		
		if(player.hasPermission("tpp.admin.tppGetGameState")){
			Announcer.messagePlayer(player, "Trench Running State is Currently Set to " + GameManager.isRunning());
			return true;
		}else{
			Announcer.messagePlayer(player, "You do not have access to this command");
			return true;
		}
	}

}
