package net.peacefulcraft.trenchpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.trenchpvp.commands.tppDebug;
import net.peacefulcraft.trenchpvp.commands.tppGetGameState;
import net.peacefulcraft.trenchpvp.commands.tppToggle;
import net.peacefulcraft.trenchpvp.commands.trJoin;
import net.peacefulcraft.trenchpvp.commands.trLeave;
import net.peacefulcraft.trenchpvp.gamehande.TrenchScoreboard;
import net.peacefulcraft.trenchpvp.gamehande.events.GameClassChange;
import net.peacefulcraft.trenchpvp.gamehande.events.RightClickMediGun;
import net.peacefulcraft.trenchpvp.gamehande.events.joinGameSign;
import net.peacefulcraft.trenchpvp.gamehande.events.leaveGameListen;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchPvP extends JavaPlugin{
	//Prefix for all plugin -> player messages
	public static final String CMD_PREFIX = ChatColor.DARK_RED + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";
	public static final Location WORLD_SPAWN= new Location(Bukkit.getWorld("Trench"), -36.5, 37.5, -66.5);
	
	public static final Location BLUE_SPAWN = new Location(Bukkit.getWorld("Trench"), TrenchTeam.getBlueSpawn()[0], TrenchTeam.getBlueSpawn()[1], TrenchTeam.getBlueSpawn()[2]);
	public static final Location RED_SPAWN = new Location(Bukkit.getWorld("Trench"), TrenchTeam.getRedSpawn()[0], TrenchTeam.getRedSpawn()[1], TrenchTeam.getRedSpawn()[2]);
	public static boolean gameRunning = false;
	
	private static TrenchPvP main;
	//Handle scoreboard events to run through console
	public static final TrenchScoreboard TEAMS = new TrenchScoreboard();
	
	public TrenchPvP(){
		main = this;
	}
	
	public void onEnable(){
	//Load all plugin commands from ~.commands.CommandLoader.java
		this.loadCommands();
		this.loadEventListners();
		
		TrenchPvP.gameRunning = true;
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been enabled!");
	}
	
	public void onDisable(){
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been disabled!");
	}
	
	private void loadCommands(){
		getCommand("tppToggle").setExecutor(new tppToggle());
		getCommand("tppGetGameState").setExecutor(new tppGetGameState());
		getCommand("trjoin").setExecutor(new trJoin());
		getCommand("trleave").setExecutor(new trLeave());
		getCommand("tppdebug").setExecutor(new tppDebug());;
	}
	
	private void loadEventListners(){
		getServer().getPluginManager().registerEvents(new joinGameSign(), this);
		getServer().getPluginManager().registerEvents(new leaveGameListen(), this);
		getServer().getPluginManager().registerEvents(new GameClassChange(), this);
		getServer().getPluginManager().registerEvents(new RightClickMediGun(), this);
	}
	
	public static TrenchPvP getPluginInstance(){
		return main;
	}
}