package net.peacefulcraft.trenchpvp;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.trenchpvp.commands.tppDebug;
import net.peacefulcraft.trenchpvp.commands.tppGetGameState;
import net.peacefulcraft.trenchpvp.commands.tppSet;
import net.peacefulcraft.trenchpvp.commands.tppToggle;
import net.peacefulcraft.trenchpvp.commands.trJoin;
import net.peacefulcraft.trenchpvp.commands.trLeave;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.PhantomArrow;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityClickListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityEntityDamageEntityListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerDeathListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerInteractEntity;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerMoveListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.AbilityPlayerToggleFlight;
import net.peacefulcraft.trenchpvp.gamehandle.GameManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ArrowImpactListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.BlockIgnitionTimer;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ChangeClassSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.CombatLoggerListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ItemDropListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.ItemSwitchListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.JoinGameListen;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.JoinGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.LaunchPadUse;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.LeaveGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.PlayerRespawning;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.PvPController;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.QuitGameListen;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.StartGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.TNTIgnition;
import net.peacefulcraft.trenchpvp.gamehandle.regions.Region;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Startgame;
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

	private static TeamManager teamManager;
		public static TeamManager getTeamManager() {return teamManager;}

	private static TrenchPvP main;
		public static TrenchPvP getPluginInstance(){return main;}

	private static TrenchConfig config;
		public static TrenchConfig getTrenchCFG() {return config;}

	private static StatTracker tracker;
		public static StatTracker getStatTracker() {return tracker;}

	private static KitMenu kitMenu;
		public static KitMenu getKitMenu() { return kitMenu; }

	/**Probably can remove or switch to own class. Storing all temporary instances of regions in the game currently */
	private static HashMap<String, Region> regions;
		public static HashMap<String,Region> getRegions() { return regions; }
		
	public TrenchPvP(){
		main = this;
		config = new TrenchConfig(getConfig());

		regions = new HashMap<>();
	}

	public void onEnable(){
		//Generate default config, if none exists
		this.saveDefaultConfig();

		//Load all plugin commands from ~.commands.CommandLoader.java
		this.loadCommands();
		
		//Load all plugin event listeners
		this.loadEventListners();

		//Initialize game resources
		teamManager = new TeamManager();
		tracker = new StatTracker();
		SyncStats.onEnable();
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been enabled!");

		//Trigger game start
		(new Startgame(this)).runTask(this);
	}

	public void onDisable(){
		this.saveConfig();
		//End game
		GameManager.closeGame();
		
		//SyncStats.onDisable();
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been disabled!");
	}
	
	/**
	 * Register all commands with the server
	 */
	private void loadCommands(){
		this.getCommand("tppToggle").setExecutor(new tppToggle());
		this.getCommand("tppGetGameState").setExecutor(new tppGetGameState());
		this.getCommand("trjoin").setExecutor(new trJoin());
		this.getCommand("trleave").setExecutor(new trLeave());
		this.getCommand("tppdebug").setExecutor(new tppDebug());;
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
		getServer().getPluginManager().registerEvents(new PvPController(), this);
		getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
		getServer().getPluginManager().registerEvents(new ArrowImpactListener(), this);
		getServer().getPluginManager().registerEvents(new TNTIgnition(), this);
		getServer().getPluginManager().registerEvents(new ItemSwitchListener(), this);
		getServer().getPluginManager().registerEvents(new CombatLoggerListener(), this);

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
