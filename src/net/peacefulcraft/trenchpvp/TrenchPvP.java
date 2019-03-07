package net.peacefulcraft.trenchpvp;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.trenchpvp.commands.tppDebug;
import net.peacefulcraft.trenchpvp.commands.tppGetGameState;
import net.peacefulcraft.trenchpvp.commands.tppToggle;
import net.peacefulcraft.trenchpvp.commands.trJoin;
import net.peacefulcraft.trenchpvp.commands.trLeave;
import net.peacefulcraft.trenchpvp.gameclasses.specials.RightClickMediGun;
import net.peacefulcraft.trenchpvp.gameclasses.specials.joinGameSign;
import net.peacefulcraft.trenchpvp.gameclasses.specials.QuitGameListen;
import net.peacefulcraft.trenchpvp.gamehande.TrenchScoreboard;
//asfdasdfs
public class TrenchPvP extends JavaPlugin{
	//Prefix for all plugin -> player messages
	public static final String CMD_PREFIX = ChatColor.DARK_RED + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";
	
	public static boolean gameRunning = false;
	
	private static TrenchPvP main;
	private static TrenchConfig config;
	//Handle scoreboard events to run through console
	public static final TrenchScoreboard TEAMS = new TrenchScoreboard();
	
	public TrenchPvP(){
		main = this;
		config = new TrenchConfig(getConfig());
	}
	
	public void onEnable(){
		//Generate default config, if none exists
		this.saveDefaultConfig();
		
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
		this.getCommand("tppToggle").setExecutor(new tppToggle());
		this.getCommand("tppGetGameState").setExecutor(new tppGetGameState());
		this.getCommand("trjoin").setExecutor(new trJoin());
		this.getCommand("trleave").setExecutor(new trLeave());
		this.getCommand("tppdebug").setExecutor(new tppDebug());;
	}
	
	private void loadEventListners(){
		getServer().getPluginManager().registerEvents(new joinGameSign(), this);
		getServer().getPluginManager().registerEvents(new QuitGameListen(), this);
	
		if(this.getConfig().getBoolean("classes.medic")) {
			getServer().getPluginManager().registerEvents(new RightClickMediGun(), this);
		}
	}
	
	public static TrenchPvP getPluginInstance(){
		return main;
	}
	
	public static TrenchConfig getTrenchCFG() {
		return config;
	}
}