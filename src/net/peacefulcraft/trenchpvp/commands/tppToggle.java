package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.GameManager;

/**
 * Admin command used to enable / disable Trench
 */
public class tppToggle implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { return false; }
		if(!(command.getName().equalsIgnoreCase("tppToggle"))) { return false; }
		
		Player player = (Player) sender;
		
		if(!(player.isOp())) { return false; }
		
		if(GameManager.isRunning()){
			GameManager.setGameState(false);
			Announcer.messagePlayer(player, "Trench PvP is now " + ChatColor.RED + "disabled!");
			return true;
		}
		else{
			GameManager.setGameState(true);;
			Announcer.messagePlayer(player, "Trench PvP is now " + ChatColor.GREEN + "enabled!");
			return true;
		}
	}
}
