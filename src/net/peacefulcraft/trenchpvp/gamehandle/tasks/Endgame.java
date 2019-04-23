package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.gamehande.GameManager;

public class Endgame extends BukkitRunnable{

	private final JavaPlugin plugin;
	
	public Endgame(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		plugin.getServer().getConsoleSender().sendMessage("[TPvP] Executing Start Game Task");
		
		SyncStats sync = new SyncStats();
		sync.commitStats(null);//TODO: GIVE IT STATS
		sync.runTaskAsynchronously(plugin);
		
		GameManager.endGame();		
	}

}
