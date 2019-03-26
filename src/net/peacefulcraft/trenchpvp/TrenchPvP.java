package net.peacefulcraft.trenchpvp;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.trenchpvp.commands.tppDebug;
import net.peacefulcraft.trenchpvp.commands.tppGetGameState;
import net.peacefulcraft.trenchpvp.commands.tppToggle;
import net.peacefulcraft.trenchpvp.commands.trJoin;
import net.peacefulcraft.trenchpvp.commands.trLeave;
import net.peacefulcraft.trenchpvp.gameclasses.specials.ArmaClickListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.BigBerthaListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.DeepCutListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.GrenadeLauncherListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.DenseClickListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.DoubleJumpListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.FlamethrowerListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.HiddenBladeListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.InfernoTrapListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.PowerShotListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.MediGunListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.OnslaughtListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.SlimSlicerListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.SniperRifleListener;
import net.peacefulcraft.trenchpvp.gameclasses.specials.SpeedShotListener;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.specials.KitSignListener;
import net.peacefulcraft.trenchpvp.gamehandle.specials.QuitGameListen;
import net.peacefulcraft.trenchpvp.gamehandle.specials.joinGameSign;
//asfdasdfs
public class TrenchPvP extends JavaPlugin{
	//Prefix for all plugin -> player messages
	public static final String CMD_PREFIX = ChatColor.DARK_RED + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";

	public static boolean gameRunning = false;
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

		TrenchPvP.gameRunning = true;
		teamManager = new TeamManager();
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
		//gamehandle.special
		getServer().getPluginManager().registerEvents(new joinGameSign(), this);
		getServer().getPluginManager().registerEvents(new KitSignListener(), this);
		getServer().getPluginManager().registerEvents(new QuitGameListen(), this);

		//gameclasses.special
		getServer().getPluginManager().registerEvents(new MediGunListener(), this);
		getServer().getPluginManager().registerEvents(new ArmaClickListener(), this);
		getServer().getPluginManager().registerEvents(new DenseClickListener(), this);
		getServer().getPluginManager().registerEvents(new HiddenBladeListener(), this);
		getServer().getPluginManager().registerEvents(new SpeedShotListener(), this);
		getServer().getPluginManager().registerEvents(new DoubleJumpListener(), this);
		getServer().getPluginManager().registerEvents(new SlimSlicerListener(), this);
		getServer().getPluginManager().registerEvents(new FlamethrowerListener(), this);
		getServer().getPluginManager().registerEvents(new InfernoTrapListener(), this);
		getServer().getPluginManager().registerEvents(new SniperRifleListener(), this);
		getServer().getPluginManager().registerEvents(new PowerShotListener(), this);
		getServer().getPluginManager().registerEvents(new GrenadeLauncherListener(), this);
		getServer().getPluginManager().registerEvents(new BigBerthaListener(), this);
		getServer().getPluginManager().registerEvents(new OnslaughtListener(), this);
		getServer().getPluginManager().registerEvents(new DeepCutListener(), this);

		if(this.getConfig().getBoolean("classes.medic")) {
			getServer().getPluginManager().registerEvents(new MediGunListener(), this);
		}
	}

}
