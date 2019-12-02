package net.peacefulcraft.trenchpvp.config;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class TrenchConfig {

	private FileConfiguration c;
	
	//Teleport Points
	private Map<String, Object> server_spawn;
	
	//Database Information
	private String db_ip = "";
	private String db_name = "";
	private String db_user = "";
	private String db_password = "";
	
	/**
	 * Loads all settings values from disk
	 * @param c Configuration file resource
	 */
	public TrenchConfig(FileConfiguration c) {
		
		this.c = c;
		
		server_spawn = c.getConfigurationSection("trenchspawns.server_spawn").getValues(false);
	
		db_ip = (String) c.getString("database.ip");
		db_name = (String) c.getString("database.name");
		db_user = (String) c.getString("database.username");
		db_password = (String) c.getString("database.password");
		
	}

	/*
	 * Various getters for all the Trench settings
	*/
	
	public Location getServer_Spawn() {
		return new Location(
				TrenchPvP.getPluginInstance().getServer().getWorld((String) server_spawn.get("world")),
				Double.valueOf(server_spawn.get("x").toString()),
				Double.valueOf(server_spawn.get("y").toString()),
				Double.valueOf(server_spawn.get("z").toString())
		);
	}
	
	public String getDb_ip() {
		return db_ip;
	}
	
	public String getDb_name() {
		return db_name;
	}
	
	public String getDb_user() {
		return db_user;
	}
	
	public String getDb_password() {
		return db_password;
	}
	
	public static Map<String, Object> locToMap(Location loc){
		Map<String, Object> cords = new HashMap<String, Object>();
		
		cords.put("x", loc.getBlockX());
		cords.put("y", loc.getBlockY());
		cords.put("z", loc.getBlockZ());
		cords.put("world", loc.getWorld().getName());
		
		return cords;
	}

}
