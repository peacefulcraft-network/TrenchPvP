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
			
		if(!(command.getName().equalsIgnoreCase("tppDebug"))) { return false; }
		
		Player p = (Player) sender;
		
		if(!(p.isOp())) { return false; }
		
		p.sendMessage("Trench is currently running: " + GameManager.isRunning());
		p.sendMessage("There are currently " + TeamManager.getPlayers().size() + " playing TrenchPvP.");
		
		
		//Print all blue players
		p.sendMessage("Blue:");
		TeamManager.ExecuteOnAllPlayers(
				(TrenchPlayer t)->{
					if(t.getPlayerTeam() == TrenchTeams.BLUE)
						p.sendMessage(p.getPlayer().getDisplayName() + ":" + t.getKitType());
				}
			);		
	
		//Print all red players
		p.sendMessage("Red:");
		TeamManager.ExecuteOnAllPlayers(
			(TrenchPlayer t)->{
				if(t.getPlayerTeam() == TrenchTeams.RED)
					p.sendMessage(p.getPlayer().getDisplayName() + ":" + t.getKitType());
			}
		);			
		
		return true;

	}

}
