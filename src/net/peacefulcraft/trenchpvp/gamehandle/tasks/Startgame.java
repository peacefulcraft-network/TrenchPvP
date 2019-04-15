package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.gamehande.GameManager;

public class Startgame extends BukkitRunnable{

	private JavaPlugin plugin;
	
	public Startgame(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		plugin.getServer().getConsoleSender().sendMessage("[TPvP] Executing Start Game Task");
		GameManager.startGame();		
	}

}
