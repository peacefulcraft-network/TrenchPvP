package net.peacefulcraft.trenchpvp.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class ArenaConfig {

	private FileConfiguration c;
	
	private String arenaName;
	
	private Map<String, Object> quit_spawn;
	private Map<String, Object> red_spawn;
	private Map<String, Object> blue_spawn;
	private Map<String, Object> red_class_spawn;
	private Map<String, Object> blue_class_spawn;
	private Map<String, Object> spectator_spawn;
	
	/**
	 * Create new arena config with name
	 * @param arenaName: name of arena and subsequent config file
	 */
	public ArenaConfig(String arenaName) {
		this.arenaName = arenaName;
		
		try {
			saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
			TrenchPvP.logErrors("Erorr saving arena " + arenaName);
		}
	}
	
		private void saveConfig() throws IOException{
			FileConfiguration c = new YamlConfiguration();
			c.save("arena/" + arenaName + ".yml");
		}
	
	/**
	 * Delete this arena config
	 */
	public void deleteArenaConfig() {
		deleteArenaConfig(arenaName);
	}
		
		/**
		 * Internal delete function. Prevents outsider from deleting any config
		 * @param arenaName
		 */
		private void deleteArenaConfig(String arenaName) {
			File target = new File(TrenchPvP.getPluginInstance().getDataFolder(), "arenas/" + arenaName + ".yml");
			target.delete();
		}
	
	/**
	 * Load arena from config file
	 * @param c FileConfig resource to get config values from
	 */
	public ArenaConfig(FileConfiguration c) {
		
		arenaName = c.getString("arena.name");
		
		quit_spawn = c.getConfigurationSection("arena.spawns.quit").getValues(false);
		red_spawn = c.getConfigurationSection("arena.spawns.red").getValues(false);
		blue_spawn = c.getConfigurationSection("arena.spawns.blue").getValues(false);
		red_class_spawn = c.getConfigurationSection("arena.spawns.red_class").getValues(false);
		blue_class_spawn = c.getConfigurationSection("arena.spawns.blue_class").getValues(false);
		spectator_spawn = c.getConfigurationSection("arena.spawns.spectator").getValues(false);
	}
	
	/*
	 * Various getters for all the Trench settings
	 */
	
	/**
	 * @return Name of the Trench Arena this config is for
	 */
	public String getArenaName() {
		return arenaName;
	}
	
	/**
	 * Change trench arena name. (Will delete and save a new config file)
	 * @param arenaName: New arena name
	 */
	public boolean setArenaName(String arenaName){
		//Save for later deletion
		String oldArena = this.arenaName;
		
		//Update arena name
		this.arenaName = arenaName;
		c.set("arena.name", arenaName);
		
		try {
			//attempt to save renamed config
			saveConfig();
		} catch (IOException e) {
			//Abort and notify upstream that it didn't work
			this.arenaName = oldArena;
			e.printStackTrace();
			return false;
		}
		
		//Remove old config once new one is saved
		deleteArenaConfig(oldArena);
		return true;
	}
	
	/**
	 * @return Map of quit spawn location coordinates
	 */
	public Location getQuit_spawn() {
		return new Location(
					TrenchPvP.getPluginInstance().getServer().getWorld(
						(String) quit_spawn.get("world")
					), 
					(Integer) quit_spawn.get("x"), 
					(Integer) quit_spawn.get("y"), 
					(Integer) quit_spawn.get("z")
				);
	}

	/**
	 * @param Map with quit spawn coordinates, (x, y, z, world)
	 */
	public void setQuit_spawn(Map<String, Object> quit_spawn) {
		this.quit_spawn = quit_spawn;
		c.set("arena.spawns.quit", quit_spawn);
	}

	/**
	 * @return Map with red player spawn coordinates
	 */
	public Location getRed_spawn() {
		return new Location(
				TrenchPvP.getPluginInstance().getServer().getWorld(
					(String) red_spawn.get("world")
				), 
				(Integer) red_spawn.get("x"), 
				(Integer) red_spawn.get("y"), 
				(Integer) red_spawn.get("z")
			);
	}

	/**
	 * @param Map with red player spawn coordinates, (x, y, z, world)
	 */
	public void setRed_spawn(Map<String, Object> red_spawn) {
		this.red_spawn = red_spawn;
		c.set("arena.spawns.red", red_spawn);
	}

	/**
	 * @return Map with blue player spawn coordinates
	 */
	public Location getBlue_spawn() {
		return new Location(
				TrenchPvP.getPluginInstance().getServer().getWorld(
					(String) blue_spawn.get("world")
				), 
				(Integer) blue_spawn.get("x"), 
				(Integer) blue_spawn.get("y"), 
				(Integer) blue_spawn.get("z")
			);
	}

	/**
	 * @param Map with blue player spawn coordinates, (x, y, z, world)
	 */
	public void setBlue_spawn(Map<String, Object> blue_spawn) {
		this.blue_spawn = blue_spawn;
		c.set("arena.spawns.blue", blue_spawn);
	}

	/**
	 * @return Map with red player class selection spawn coordinates
	 */
	public Location getRed_class_spawn() {
		return new Location(
				TrenchPvP.getPluginInstance().getServer().getWorld(
					(String) red_class_spawn.get("world")
				), 
				(Integer) red_class_spawn.get("x"), 
				(Integer) red_class_spawn.get("y"), 
				(Integer) red_class_spawn.get("z")
			);
	}

	/**
	 * @param Map with red class selection spawn coordinates, (x, y, z, world)
	 */
	public void setRed_class_spawn(Map<String, Object> red_class_spawn) {
		this.red_class_spawn = red_class_spawn;
		c.set("arena.spawns.red_class", red_class_spawn);
	}

	/**
	 * @return Map with blue player class selection spawn coordinates
	 */
	public Location getBlue_class_spawn() {
		return new Location(
				TrenchPvP.getPluginInstance().getServer().getWorld(
					(String) blue_class_spawn.get("world")
				), 
				(Integer) blue_class_spawn.get("x"), 
				(Integer) blue_class_spawn.get("y"), 
				(Integer) blue_class_spawn.get("z")
			);
	}

	/**
	 * @param Map with blue class selection spawn coordinates, (x, y, z, world)
	 */
	public void setBlue_class_spawn(Map<String, Object> blue_class_spawn) {
		this.blue_class_spawn = blue_class_spawn;
		c.set("arena.spawns.blue_class", blue_class_spawn);
	}

	/**
	 * @return Map with spectator spawn coordinates
	 */
	public Location getSpectator_spawn() {
		return new Location(
				TrenchPvP.getPluginInstance().getServer().getWorld(
					(String) spectator_spawn.get("world")
				), 
				(Integer) spectator_spawn.get("x"), 
				(Integer) spectator_spawn.get("y"), 
				(Integer) spectator_spawn.get("z")
			);
	}

	/**
	 * @param Map with spectator spawn coordinates, (x, y, z, world)
	 */
	public void setSpectator_spawn(Map<String, Object> spectator_spawn) {
		this.spectator_spawn = spectator_spawn;
		c.set("arena.spawns.spectator", spectator_spawn);
	}
	
	/**
	 * Generate a Map object that can be saved as a telelport location config
	 * @param x
	 * @param y 
	 * @param z
	 * @param w
	 * @return A map which can be passed to a ArenaConfig.save... function
	 */
	public static Map<String, Object> constructTrenchCordMap(int x, int y, int z, World w){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("z", z);
		map.put("world", w.getName());
		return map;
	}
	
	/**
	 * Log arena settings from config should it exist.
	 * If arena doesn't exist, return null
	 * @param arenaName 
	 * @return FileConfiguration of arena, or null of arena doesn't exist
	 */
	public static FileConfiguration getArenaFileConfig(String arenaName) {
		FileConfiguration fc = new YamlConfiguration();
		try {
			fc.load("arenas/" + arenaName + ".yml");
			return fc;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
