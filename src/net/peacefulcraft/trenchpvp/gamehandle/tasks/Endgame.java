package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.gamehandle.arena.TrenchArena;

public class Endgame extends BukkitRunnable{

	private final JavaPlugin plugin;
	private TrenchArena arena;
	
	public Endgame(JavaPlugin plugin, TrenchArena arena) {
		this.plugin = plugin;
		this.arena = arena;
	}
	
	@Override
	public void run() {
		plugin.getServer().getConsoleSender().sendMessage("[TPvP] Executing End Game Tasks");
		arena.endGame();
	}

}
