package net.peacefulcraft.trenchpvp;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.trenchpvp.commands.tppSet;
import net.peacefulcraft.trenchpvp.commands.trJoin;
import net.peacefulcraft.trenchpvp.commands.trLeave;
import net.peacefulcraft.trenchpvp.config.ArenaConfig;
import net.peacefulcraft.trenchpvp.config.TrenchConfig;
import net.peacefulcraft.trenchpvp.config.YAMLFileFilter;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.PhantomArrow;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityClickListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityEntityDamageEntityListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerDeathListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerInteractEntity;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerMoveListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerToggleFlight;
import net.peacefulcraft.trenchpvp.gamehandle.PartyManager;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchArena;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchManager;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ArrowImpactListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.BlockIgnitionTimer;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ChangeClassSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ItemDropListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ItemSwitchListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.JoinGameListen;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.JoinGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.LaunchPadUse;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.LeaveGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.PlayerRespawning;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.QuitGameListen;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.StartGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.TNTIgnition;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.SyncStats;
import net.peacefulcraft.trenchpvp.menu.listeners.KitMenu;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.listeners.ConsumeListener;
import net.peacefulcraft.trenchpvp.stats.listeners.DamageListener;
import net.peacefulcraft.trenchpvp.stats.listeners.FriendlykillListener;
import net.peacefulcraft.trenchpvp.stats.listeners.KillListener;
import net.peacefulcraft.trenchpvp.stats.listeners.KillStreakListener;

public class TrenchPvP extends JavaPlugin{
	//Prefix for all plugin -> player messages
	public static final String CMD_PREFIX = ChatColor.DARK_RED + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";

	private static TrenchManager trenchManager;
		public static TrenchManager getTrenchManager() {return trenchManager;}

	private static TrenchPvP main;
		public static TrenchPvP getPluginInstance(){return main;}

	private static TrenchConfig config;
		public static TrenchConfig getTrenchCFG() {return config;}

	private static StatTracker tracker;
		public static StatTracker getStatTracker() {return tracker;}

	private static KitMenu kitMenu;
		public static KitMenu getKitMenu() { return kitMenu; }
		
	private static PartyManager partyManager;
		public static PartyManager getPartyManager() {return partyManager;}
		
	public TrenchPvP(){
		main = this;
		config = new TrenchConfig(getConfig());
	}

	public void onEnable(){
		//Generate default config, if none exists
		this.saveDefaultConfig();

		//Load all plugin commands from ~.commands.CommandLoader.java
		this.loadCommands();
		
		//Load all plugin event listeners
		this.loadEventListners();

		//Initialize game resources
		trenchManager = new TrenchManager();
		tracker = new StatTracker();
		partyManager = new PartyManager();

		SyncStats.onEnable();
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been enabled!");

		//Trigger game start
		//TODO: NEW START SEQUENCE
	}

	public void onDisable(){
		this.saveConfig();		
		//SyncStats.onDisable();
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been disabled!");
	}
	
	/**
	 * Register all commands with the server
	 */
	private void loadCommands(){
		this.getCommand("trjoin").setExecutor(new trJoin());
		this.getCommand("trleave").setExecutor(new trLeave());
		this.getCommand("tppSet").setExecutor(new tppSet());
	}
	
	/**
	 * Register all event listeners with the server
	 */
	private void loadEventListners(){
		//gamehandle.listeners
		getServer().getPluginManager().registerEvents(new JoinGameSign(), this);
		getServer().getPluginManager().registerEvents(new LeaveGameSign(), this);
		getServer().getPluginManager().registerEvents(new StartGameSign(), this);
		getServer().getPluginManager().registerEvents(new ChangeClassSign(), this);
		getServer().getPluginManager().registerEvents(new JoinGameListen(), this);
		getServer().getPluginManager().registerEvents(new BlockIgnitionTimer(), this);
		getServer().getPluginManager().registerEvents(new QuitGameListen(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawning(), this);
		getServer().getPluginManager().registerEvents(new ItemListener(), this);
		getServer().getPluginManager().registerEvents(new PvPController(), this);
		getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
		getServer().getPluginManager().registerEvents(new ArrowImpactListener(), this);
		getServer().getPluginManager().registerEvents(new TNTIgnition(), this);
		getServer().getPluginManager().registerEvents(new ItemSwitchListener(), this);

		//new gameclasses.listeners
		getServer().getPluginManager().registerEvents(new AbilityClickListener(), this);
		getServer().getPluginManager().registerEvents(new AbilityEntityDamageEntityListener(), this);
		getServer().getPluginManager().registerEvents(new AbilityPlayerDeathListener(), this);
		getServer().getPluginManager().registerEvents(new AbilityPlayerInteractEntity(), this);
		getServer().getPluginManager().registerEvents(new AbilityPlayerMoveListener(), this);
		getServer().getPluginManager().registerEvents(new AbilityPlayerToggleFlight(), this);
		
		getServer().getPluginManager().registerEvents(new LaunchPadUse(), this);
		getServer().getPluginManager().registerEvents(new PhantomArrow(), this);

		//Stat listeners
		getServer().getPluginManager().registerEvents(new ConsumeListener(), this);
		getServer().getPluginManager().registerEvents(new DamageListener(), this);
		getServer().getPluginManager().registerEvents(new FriendlykillListener(), this);
		getServer().getPluginManager().registerEvents(new KillListener(), this);
		getServer().getPluginManager().registerEvents(new KillStreakListener(), this);

		//Menu listeners
		kitMenu = new KitMenu();
		getServer().getPluginManager().registerEvents(kitMenu, this);
		
		//Load / register the arenas to the map pool
		loadArenas();
	}

		private void loadArenas(){
			File arenaDataDir = new File(this.getDataFolder().toPath() + "/arenas");
			String[] arenaFileNames = arenaDataDir.list(new YAMLFileFilter());
			for(String arenaName : arenaFileNames) {
				trenchManager.registerArena(
					new TrenchArena(new ArenaConfig(arenaName))
				);
			}
		}
	
	/**
	 * Record warning with logger
	 * @param msg: The warning message to log
	 */
	public static void logWarning(String msg) {
		getPluginInstance().getServer().getLogger().log(Level.WARNING, "[Trench]" + msg);
	}
	
	/**
	 * Record error message with logger
	 * @param msg: The error to log
	 */
	public static void logErrors(String msg) {
		getPluginInstance().getServer().getLogger().log(Level.SEVERE, "[Trench]" + msg);
	}

}
