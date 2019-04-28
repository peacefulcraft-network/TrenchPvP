package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.GameManager;

public class Endgame extends BukkitRunnable{

	private final JavaPlugin plugin;
	
	public Endgame(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		plugin.getServer().getConsoleSender().sendMessage("[TPvP] Executing End Game Tasks");
		
		GameManager.endGame();		
	}

}
