package net.peacefulcraft.trenchpvp.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.config.ArenaConfig;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.arena.TrenchArena;

public class tra implements CommandExecutor, TabCompleter{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if( !(sender instanceof Player) ) { 
			Announcer.messagePlayer(sender, "This command requires location data and therefore can only be used by players");
			return true; 
		}
		Player p = (Player) sender;
		
		if(! p.hasPermission("trenchpvp.admin.arena.config")) { 
			Announcer.messageDeniedPermission(p);
			return true;
		}
		
		if(!command.getName().equalsIgnoreCase("tra")) { return true; }
		
		if(! (args.length > 0)) {
			Announcer.messagePlayer(sender, "Please specify an administrative context. tra [ status | arena ]");
			return true; 
		}
		
		switch(args[0]) {
		case "arena":
			
			if(! (args.length > 1)) {
				Announcer.messagePlayer(sender, "Please specify an arena name or list. tra arena [ create | list | name ]");
				return true; 
			}
			
			if(args[1].equalsIgnoreCase("list")) {
				arena_listArenas(p);
				return true;
				
			}else if(args[1].equalsIgnoreCase("create")) {
				if(! (args.length > 2)) {
					Announcer.messagePlayer(p, "Please specify an anrea name. tra arena create [ name ]");
					return true;
				}
				
				String arenaName = args[2];
				if(arena_checkArenaNameValid(arenaName)) {
					Announcer.messagePlayer(p, "An arena with name " + arenaName + " already exists.");
					return true;
				}//else, arena does not exist and we should create it
				
				//TODO:This only prints errors to console if it doesn't work which is not super useful for players
				TrenchPvP.getTrenchManager().registerArena(
					new TrenchArena(
						new ArenaConfig(arenaName)
					)
				);
				
				Announcer.messagePlayer(p, "Arena " + arenaName + " succuesfuly created!");
				return true;
				
				
			}//else, assume it's an arena name
			
			String arenaName;
			if(arena_checkArenaNameValid(args[1])) {
				arenaName = args[1];
			}else {
				Announcer.messagePlayer(p, "Please specify a valid arena name.");
				arena_listArenas(p);
				return true;
			}
			TrenchArena arena = getArena(arenaName);
			
			if(! (args.length > 2)) {
				Announcer.messagePlayer(sender, "Please specify a property action. tra arena " + arenaName + " [ get | set | save]");
				return true; 
			}
			
			switch(args[2]) {
			case "save":
				
				//TODO: If this fails the console errors, but this will tell the player everything is fine
				arena.saveArenaConfig();
				Announcer.messagePlayer(sender, "Saved arena " + arenaName);
				
			break; case "set":
				
				if(! (args.length > 3)) {
					Announcer.messagePlayer(sender, "Please specify an arena property to set tra arena " + arenaName + " set [ blueSpawn | blueClass | redSpawn | redClass | spectatorSpawn ]");
					return true;
				}
			
				switch(args[3]) {
				case "blueSpawn":
					arena.setBlueSpawn(p.getLocation());
					Announcer.messagePlayer(sender, "Blue team spawn set to your location.");
					
				break; case "blueClass":
					arena.setBlueClassSpawn(p.getLocation());
					Announcer.messagePlayer(sender, "Blue team class selection room set to your location.");
				
				break; case "redSpawn":
					arena.setRedSpawn(p.getLocation());
					Announcer.messagePlayer(sender, "Red team spawn set to your location.");
					
				break; case "redClass":
					arena.setRedClassSpawn(p.getLocation());
					Announcer.messagePlayer(sender, "Red team class selection room set to your location.");
				
				break; case "spectatorSpawn":
					arena.setSpectatorSpawn(p.getLocation());
					Announcer.messagePlayer(sender, "Spectator spawn set to your location");
				
				break; case "active":
				
					if(! (args.length > 4)) {
						Announcer.messagePlayer(sender, "Please specify an arena state. tra arena " + arenaName + " set active [ yes | no ]");
						return true;
					}
					
					if(args[4].equalsIgnoreCase("yes") || args[4].equalsIgnoreCase("true") || args[4].equalsIgnoreCase("enabled")) {
						TrenchPvP.getTrenchManager().activateArena(arena);
						Announcer.messagePlayer(sender, "Arena " + arenaName + " enabled.");
						
					}else {
						if(TrenchPvP.getTrenchManager().deactivateArena(arena)) {
							Announcer.messagePlayer(sender, "Arena " + arenaName + " disabled.");
						
						}else {
							Announcer.messagePlayer(sender, "Unable to disable an arena with an active game. Cycle the map and try again.");
							
						}
					}
					
				break; default:
					Announcer.messagePlayer(sender, "Please specify an arena property to set tra arena " + arenaName + " set [ active | blueSpawn | blueClass | redSpawn | redClass | spectatorSpawn ]");
				
				}
				
			break; case "get":
				if(! (args.length > 3)) {
					Announcer.messagePlayer(sender, "Please specify an arena property to set tra arena " + arenaName + " set [ blueSpawn | blueClass | redSpawn | redClass | spectatorSpawn ]");
					return true;
				}
			
				switch(args[3]) {
				case "blueSpawn":
					p.teleport(arena.getBlueSpawn());
					Announcer.messagePlayer(sender, "Blue team's spawn location.");
					
				break; case "blueClass":
					p.teleport(arena.getBlueClassSpawn());
					Announcer.messagePlayer(sender, "Blue team's class selection room.");
				
				break; case "redSpawn":
					p.teleport(arena.getRedSpawn());
					Announcer.messagePlayer(sender, "Red team's spawn location.");
					
				break; case "redClass":
					p.teleport(arena.getRedClassSpawn());
					Announcer.messagePlayer(sender, "Red team's class selection room.");
				
				break; case "spectatorSpawn":
					p.teleport(arena.getSpectatorSpawn());
					Announcer.messagePlayer(sender, "Spectator spawn.");
				
				break; default:
					Announcer.messagePlayer(sender, "Please specify an arena property to get tra arena " + arenaName + " get [ blueSpawn | blueClass | redSpawn | redClass | spectatorSpawn ]");
				
				}
			
			break; default:
				Announcer.messagePlayer(sender, "Please specify a valid property action. tra arena [ set | get ]");
				return true;
			}
			
		break; case "status":
			
			status_globalStatus(p);
			
		break; default:
			
			Announcer.messagePlayer(sender, "Please specify a valid administrative context. tra [ status | arena ]");
			return true;
		
		}
		
		return true;
	}
	
	/**
	 * Sends argument suggestions to the user while they type out the command
	 */
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

		if(! (command.getName().equalsIgnoreCase("tra"))) { return null; }
		if(! (sender instanceof Player) ) { return null; }
		
		List<String> opts = new ArrayList<String>();
		
		if(args.length > 0 && args[0].trim().equalsIgnoreCase("arena")) {

			if(args.length > 1) {
				
				if(args[1].equalsIgnoreCase("create")) {
					return null;	// Return nothing, valid command
					
				}else if(args[1].equalsIgnoreCase("list")){
					return null;	// Return nothing, valid command
					
				}else if(arena_checkArenaNameValid(args[1])) {
					
					if(args.length > 2) {
						
						if(args[2].equalsIgnoreCase("get") || args[2].equalsIgnoreCase("set")) {
							
							opts.add("blueSpawn");
							opts.add("blueClass");
							opts.add("redSpawn");
							opts.add("redClass");
							opts.add("spectatorSpawn");
							return opts;

						}else if(args[2].equalsIgnoreCase("save")){
							return null;	// Return nothing, valid command
							
						}else {
							
							opts.add("get");
							opts.add("set");
							opts.add("save");
							return opts;
							
						}
						
					}
					
					// Return nothing until the user starts another argument
					return null;
					
				}else {
					opts.add("create");
					opts.add("list");
					opts.addAll(arena_nameSuggestions());
					return opts;
				}
				
			}
			
			// Return nothing until the user starts another argument
			return null;
			
		}else {
			opts.add("arena");
			opts.add("status");
			return opts;
		}
	}
	
	/**
	 * Send global trench plugin status to the supplied player
	 * @param p
	 */
	private void status_globalStatus(Player p) {
		Announcer.messagePlayer(p, "Current arena " + TrenchPvP.getTrenchManager().getCurrentArena());
	}
	
	/**
	 * Send the list of arenas, split between configured and active, to the supplied player
	 * @param p
	 */
	private void arena_listArenas(Player p) {
		Announcer.messagePlayer(p, "Configured Arenas:");
		for(TrenchArena ta: TrenchPvP.getTrenchManager().getConfiguredArenas()) {
			Announcer.messagePlayer(p, ta.getArenaName());
		}
		
		Announcer.messagePlayer(p, "Active Arenas:");
		for(TrenchArena ta: TrenchPvP.getTrenchManager().getActiveArenas()) {
			Announcer.messagePlayer(p, ta.getArenaName());
		}
	}
	
	/**
	 * Check if an arena with the supplied name exist
	 * @param arenaName
	 * @return
	 */
	private boolean arena_checkArenaNameValid(String arenaName) {
		for(TrenchArena ta: TrenchPvP.getTrenchManager().getConfiguredArenas()) {
			if(arenaName.equalsIgnoreCase(ta.getArenaName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the TrenchArena object by the arena's configured name
	 * @param name
	 * @return
	 */
	private TrenchArena getArena(String name) {
		for(TrenchArena ta: TrenchPvP.getTrenchManager().getConfiguredArenas()) {
			if(ta.getArenaName().equalsIgnoreCase(name)) { return ta; }
		}
		return null;
	}

	private List<String> arena_nameSuggestions(){
		List<String> opts = new ArrayList<String>();
		
		for(TrenchArena ta: TrenchPvP.getTrenchManager().getConfiguredArenas()) {
			opts.add(ta.getArenaName());
		}
		
		for(TrenchArena ta: TrenchPvP.getTrenchManager().getActiveArenas()) {
			opts.add(ta.getArenaName());
		}
		
		return opts;
	}
}
