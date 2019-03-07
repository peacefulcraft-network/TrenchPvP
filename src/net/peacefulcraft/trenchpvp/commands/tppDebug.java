package net.peacefulcraft.trenchpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class tppDebug implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("tppDebug")){
			Player user = (Player) sender;
			user.sendMessage("Trench is currently running: " + TrenchPvP.gameRunning);
			user.sendMessage("There are currently " + TrenchTeam.trenchPlayers.length + " playing TrenchPvP.");
			user.sendMessage("Blue:");
			for(TrenchPlayer index: TrenchTeam.trenchPlayers){
				if(index != null){
					if(index.getPlayerTeam() == TrenchTeam.BLUE)
						user.sendMessage(index.getPlayer().getDisplayName() + ":" +index.getPlayerClass());
				}	
			}
			user.sendMessage("Red:");
			for(TrenchPlayer index: TrenchTeam.trenchPlayers){
				if(index != null){
					if(index.getPlayerTeam() == TrenchTeam.RED)
						user.sendMessage(index.getPlayer().getDisplayName() + ":" +index.getPlayerClass());
				}	
			}
			return true;
		}
		return false;
	}

}
