package net.peacefulcraft.trenchpvp.commands;

import java.util.Hashtable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;

/**
 * Admin setup comamnd used to set teleport points in the arena
 */
public class tppSet implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return true;
		/*
		 * if(!(sender instanceof Player)) { return false; } Player player = (Player)
		 * sender;
		 * 
		 * if(!player.isOp()) { return false; }
		 * 
		 * if(command.getName().equalsIgnoreCase("tppSet")) {
		 * 
		 * if(args.length == 0) { player.sendMessage("Invalid Argument"); return true; }
		 * 
		 * if(args[0].equalsIgnoreCase("blueSpawn")) {
		 * 
		 * blueSpawn(player); Announcer.messagePlayer(player,
		 * "Blue Game Spawn set to your location"); return true;
		 * 
		 * }else if(args[0].equalsIgnoreCase("blueClassSpawn")){
		 * 
		 * blueClassSpawn(player); Announcer.messagePlayer(player,
		 * "Blue Class Room set to your location"); return true;
		 * 
		 * }else if(args[0].equalsIgnoreCase("redSpawn")){
		 * 
		 * redSpawn(player); Announcer.messagePlayer(player,
		 * "Red Game Spawn set to your location"); return true;
		 * 
		 * }else if(args[0].equalsIgnoreCase("redClassSpawn")){
		 * 
		 * redClassSpawn(player); Announcer.messagePlayer(player,
		 * "Red Class Room set to your location"); return true;
		 * 
		 * }else if(args[0].equalsIgnoreCase("spectatorSpawn")) {
		 * 
		 * spectatorSpawn(player); Announcer.messagePlayer(player,
		 * "Spectator Spawn set to your location"); return true;
		 * 
		 * }else if(args[0].equalsIgnoreCase("quitSpawn")){
		 * 
		 * quitSpawn(player); Announcer.messagePlayer(player,
		 * "Quit Spawn set to your location"); return true;
		 * 
		 * }else {
		 * 
		 * Announcer.messagePlayer(player, "Invalid Argument"); return true;
		 * 
		 * }
		 * 
		 * }else {
		 * 
		 * return false;
		 * 
		 * }
		 */
	}
	
	/*
	 * private void blueSpawn(Player player) { Hashtable<String, Object> loc = new
	 * Hashtable<String, Object>(); loc.put("world",
	 * player.getLocation().getWorld().getName()); loc.put("x", "" +
	 * player.getLocation().getX()); loc.put("y", "" + player.getLocation().getY());
	 * loc.put("z", "" + player.getLocation().getZ());
	 * TrenchPvP.getTrenchCFG().setBlue_spawn( loc ); }
	 * 
	 * private void blueClassSpawn(Player player) { Hashtable<String, Object> loc =
	 * new Hashtable<String, Object>(); loc.put("world",
	 * player.getLocation().getWorld().getName()); loc.put("x", "" +
	 * player.getLocation().getX()); loc.put("y", "" + player.getLocation().getY());
	 * loc.put("z", "" + player.getLocation().getZ());
	 * TrenchPvP.getTrenchCFG().setBlue_class_spawn( loc ); }
	 * 
	 * private void redSpawn(Player player) { Hashtable<String, Object> loc = new
	 * Hashtable<String, Object>(); loc.put("world",
	 * player.getLocation().getWorld().getName()); loc.put("x", "" +
	 * player.getLocation().getX()); loc.put("y", "" + player.getLocation().getY());
	 * loc.put("z", "" + player.getLocation().getZ());
	 * TrenchPvP.getTrenchCFG().setRed_spawn( loc ); }
	 * 
	 * private void redClassSpawn(Player player) { Hashtable<String, Object> loc =
	 * new Hashtable<String, Object>(); loc.put("world",
	 * player.getLocation().getWorld().getName()); loc.put("x", "" +
	 * player.getLocation().getX()); loc.put("y", "" + player.getLocation().getY());
	 * loc.put("z", "" + player.getLocation().getZ());
	 * TrenchPvP.getTrenchCFG().setRed_class_spawn( loc ); }
	 * 
	 * private void spectatorSpawn(Player player) { Hashtable<String, Object> loc =
	 * new Hashtable<String, Object>(); loc.put("world",
	 * player.getLocation().getWorld().getName()); loc.put("x", "" +
	 * player.getLocation().getX()); loc.put("y", "" + player.getLocation().getY());
	 * loc.put("z", "" + player.getLocation().getZ());
	 * TrenchPvP.getTrenchCFG().setSpectator_spawn(loc); }
	 * 
	 * private void quitSpawn(Player player) { Hashtable<String, Object> loc = new
	 * Hashtable<String, Object>(); loc.put("world",
	 * player.getLocation().getWorld().getName()); loc.put("x", "" +
	 * player.getLocation().getX()); loc.put("y", "" + player.getLocation().getY());
	 * loc.put("z", "" + player.getLocation().getZ());
	 * TrenchPvP.getTrenchCFG().setQuit_spawn( loc ); }
	 */

}
