package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.GameManager;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class tppDebug implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("tppDebug")){
			Player user = (Player) sender;
			user.sendMessage("Trench is currently running: " + GameManager.isRunning());
			user.sendMessage("There are currently " + TeamManager.getPlayers().size() + " playing TrenchPvP.");
			user.sendMessage("Blue:");
			for(TrenchPlayer index: TeamManager.getPlayers()){
				if(index != null){
					if(index.getPlayerTeam() == TrenchTeams.BLUE)
						user.sendMessage(index.getPlayer().getDisplayName() + ":" +index.getKitType());
				}	
			}
			user.sendMessage("Red:");
			for(TrenchPlayer index: TeamManager.getPlayers()){
				if(index != null){
					if(index.getPlayerTeam() == TrenchTeams.RED)
						user.sendMessage(index.getPlayer().getDisplayName() + ":" +index.getKitType());
				}	
			}
			return true;
		}
		return false;
	}

}
