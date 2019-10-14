package net.peacefulcraft.trenchpvp.config;

import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class TrenchConfig {

	private FileConfiguration c;
	
	//Teleport Points
	private Map<String, Object> quit_spawn;
	private Map<String, Object> red_spawn;
	private Map<String, Object> blue_spawn;
	private Map<String, Object> red_class_spawn;
	private Map<String, Object> blue_class_spawn;
	private Map<String, Object> spectator_spawn;
	
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
		
		quit_spawn = c.getConfigurationSection("trenchspawns.quit").getValues(false);
		red_spawn = c.getConfigurationSection("trenchspawns.red").getValues(false);
		blue_spawn = c.getConfigurationSection("trenchspawns.blue").getValues(false);
		red_class_spawn = c.getConfigurationSection("trenchspawns.red_class").getValues(false);
		blue_class_spawn = c.getConfigurationSection("trenchspawns.blue_class").getValues(false);
		spectator_spawn = c.getConfigurationSection("trenchspawns.spectator").getValues(false);
	
		db_ip = (String) c.getString("database.ip");
		db_name = (String) c.getString("database.name");
		db_user = (String) c.getString("database.username");
		db_password = (String) c.getString("database.password");
		
	}

	/*
	 * Various getters for all the Trench settings
	 */
	
	public Map<String, Object> getQuit_spawn() {
		return quit_spawn;
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

}
