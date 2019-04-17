package net.peacefulcraft.trenchpvp;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.trenchpvp.commands.tppDebug;
import net.peacefulcraft.trenchpvp.commands.tppGetGameState;
import net.peacefulcraft.trenchpvp.commands.tppSet;
import net.peacefulcraft.trenchpvp.commands.tppToggle;
import net.peacefulcraft.trenchpvp.commands.trJoin;
import net.peacefulcraft.trenchpvp.commands.trLeave;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.ArmaClickListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.BigBerthaListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.DeepCutListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.DenseClickListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.DoubleJumpListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.FlamethrowerListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.GrenadeLauncherListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.HiddenBladeListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.InfernoTrapListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.MediGunListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.OnslaughtListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.PowerShotListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.SlimSlicerListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.PoisonRoundListener;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.SpeedShotListener;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.KitSignListener;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.PvPController;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.QuitGameListen;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.joinGameSign;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.respawningTeleport;
import net.peacefulcraft.trenchpvp.gamehandle.tasks.Startgame;
//asfdasdfs
public class TrenchPvP extends JavaPlugin{
	//Prefix for all plugin -> player messages
	public static final String CMD_PREFIX = ChatColor.DARK_RED + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";

	private static TeamManager teamManager;
		public static TeamManager getTeamManager() {return teamManager;}

	private static TrenchPvP main;
		public static TrenchPvP getPluginInstance(){return main;}

	private static TrenchConfig config;
		public static TrenchConfig getTrenchCFG() {return config;}

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

		teamManager = new TeamManager();
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been enabled!");
		
		//Trigger game start
		(new Startgame(this)).runTask(this);
	}

	public void onDisable(){
		this.saveConfig();
		this.getLogger().info("[TPP]Trench PvP Alpha 0.1 has been disabled!");
	}

	private void loadCommands(){
		this.getCommand("tppToggle").setExecutor(new tppToggle());
		this.getCommand("tppGetGameState").setExecutor(new tppGetGameState());
		this.getCommand("trjoin").setExecutor(new trJoin());
		this.getCommand("trleave").setExecutor(new trLeave());
		this.getCommand("tppdebug").setExecutor(new tppDebug());;
		this.getCommand("tppSet").setExecutor(new tppSet());
	}

	private void loadEventListners(){
		//gamehandle.listeners
		getServer().getPluginManager().registerEvents(new joinGameSign(), this);
		getServer().getPluginManager().registerEvents(new KitSignListener(), this);
		getServer().getPluginManager().registerEvents(new QuitGameListen(), this);
		getServer().getPluginManager().registerEvents(new respawningTeleport(), this);
		getServer().getPluginManager().registerEvents(new PvPController(), this);
		
		//gameclasses.listeners
		getServer().getPluginManager().registerEvents(new MediGunListener(), this);
		getServer().getPluginManager().registerEvents(new ArmaClickListener(), this);
		getServer().getPluginManager().registerEvents(new DenseClickListener(), this);
		getServer().getPluginManager().registerEvents(new HiddenBladeListener(), this);
		getServer().getPluginManager().registerEvents(new SpeedShotListener(), this);
		getServer().getPluginManager().registerEvents(new DoubleJumpListener(), this);
		getServer().getPluginManager().registerEvents(new SlimSlicerListener(), this);
		getServer().getPluginManager().registerEvents(new FlamethrowerListener(), this);
		getServer().getPluginManager().registerEvents(new InfernoTrapListener(), this);
		getServer().getPluginManager().registerEvents(new PoisonRoundListener(), this);
		getServer().getPluginManager().registerEvents(new PowerShotListener(), this);
		getServer().getPluginManager().registerEvents(new GrenadeLauncherListener(), this);
		getServer().getPluginManager().registerEvents(new BigBerthaListener(), this);
		getServer().getPluginManager().registerEvents(new OnslaughtListener(), this);
		getServer().getPluginManager().registerEvents(new DeepCutListener(), this);
	}

}
