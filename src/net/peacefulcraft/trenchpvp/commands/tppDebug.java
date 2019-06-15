package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.GameManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;

public class tppDebug implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("tppDebug")){
			Player user = (Player) sender;
			user.sendMessage("Trench is currently running: " + GameManager.isRunning());
			user.sendMessage("There are currently " + TeamManager.getPlayers().size() + " playing TrenchPvP.");
			
			
			//Print all blue players
			user.sendMessage("Blue:");
			TeamManager.ExecuteOnAllPlayers(
					(TrenchPlayer t)->{
						if(t.getPlayerTeam() == TrenchTeams.BLUE)
							user.sendMessage(user.getPlayer().getDisplayName() + ":" + t.getKitType());
					}
				);		
		
			//Print all red players
			user.sendMessage("Red:");
			TeamManager.ExecuteOnAllPlayers(
				(TrenchPlayer t)->{
					if(t.getPlayerTeam() == TrenchTeams.RED)
						user.sendMessage(user.getPlayer().getDisplayName() + ":" + t.getKitType());
				}
			);			
			
			return true;
	
		}
		
		return false;
	
	}

}
