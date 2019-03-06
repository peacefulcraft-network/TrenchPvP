package net.peacefulcraft.trenchpvp.commands;

import java.util.Hashtable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class tppSet implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {	return false; }
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("blueSpawn")) {
		
			blueSpawn(player);
			return true;
		
		}else if(command.getName().equalsIgnoreCase("blueClassSpawn")){
		
			blueClassSpawn(player);
			return true;
		
		}else if(command.getName().equalsIgnoreCase("redSpawn")){
		
			redSpawn(player);
			return true;
		
		}else if(command.getName().equalsIgnoreCase("redClassSpawn")){
			
			redClassSpawn(player);
			return true;
			
		}else if(command.getName().equalsIgnoreCase("quitSpawn")){
			
			quitSpawn(player);
			return true;
			
		}else {
			
			return false;
		
		}
		
	}
	
	private void blueSpawn(Player player) {
		Hashtable<String, Object> loc = new Hashtable<String, Object>();
		loc.put("world", player.getLocation().getWorld().getName());
		loc.put("x", "" + player.getLocation().getX());
		loc.put("y", "" + player.getLocation().getY());
		loc.put("z", "" + player.getLocation().getZ());
		TrenchPvP.getTrenchCFG().setBlue_spawn(	loc );
	}
	
	private void blueClassSpawn(Player player) {
		Hashtable<String, Object> loc = new Hashtable<String, Object>();
		loc.put("world", player.getLocation().getWorld().getName());
		loc.put("x", "" + player.getLocation().getX());
		loc.put("y", "" + player.getLocation().getY());
		loc.put("z", "" + player.getLocation().getZ());
		TrenchPvP.getTrenchCFG().setBlue_class_spawn( loc );
	}
	
	private void redSpawn(Player player) {
		Hashtable<String, Object> loc = new Hashtable<String, Object>();
		loc.put("world", player.getLocation().getWorld().getName());
		loc.put("x", "" + player.getLocation().getX());
		loc.put("y", "" + player.getLocation().getY());
		loc.put("z", "" + player.getLocation().getZ());
		TrenchPvP.getTrenchCFG().setRed_spawn(	loc );
	}
	
	private void redClassSpawn(Player player) {
		Hashtable<String, Object> loc = new Hashtable<String, Object>();
		loc.put("world", player.getLocation().getWorld().getName());
		loc.put("x", "" + player.getLocation().getX());
		loc.put("y", "" + player.getLocation().getY());
		loc.put("z", "" + player.getLocation().getZ());
		TrenchPvP.getTrenchCFG().setRed_class_spawn( loc );
	}
	
	private void quitSpawn(Player player) {
		Hashtable<String, Object> loc = new Hashtable<String, Object>();
		loc.put("world", player.getLocation().getWorld().getName());
		loc.put("x", "" + player.getLocation().getX());
		loc.put("y", "" + player.getLocation().getY());
		loc.put("z", "" + player.getLocation().getZ());
		TrenchPvP.getTrenchCFG().setQuit_spawn(	loc );
	}

}
