package net.peacefulcraft.trenchpvp;

import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class TrenchConfig {

	private FileConfiguration c;
	
	private Map<String, Object> quit_spawn;
	private Map<String, Object> red_spawn;
	private Map<String, Object> blue_spawn;
	private Map<String, Object> red_class_spawn;
	private Map<String, Object> blue_class_spawn;
	private Map<String, Object> spectator_spawn;
	
	private String db_ip = "";
	private String db_name = "";
	private String db_user = "";
	private String db_password = "";
	
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

	public Map<String, Object> getQuit_spawn() {
		return quit_spawn;
	}

	public void setQuit_spawn(Map<String, Object> quit_spawn) {
		this.quit_spawn = quit_spawn;
		c.set("trenchspawns.quit", quit_spawn);
	}

	public Map<String, Object> getRed_spawn() {
		return red_spawn;
	}

	public void setRed_spawn(Map<String, Object> red_spawn) {
		this.red_spawn = red_spawn;
		c.set("trenchspawns.red", red_spawn);
	}

	public Map<String, Object> getBlue_spawn() {
		return blue_spawn;
	}

	public void setBlue_spawn(Map<String, Object> blue_spawn) {
		this.blue_spawn = blue_spawn;
		c.set("trenchspawns.blue", blue_spawn);
	}

	public Map<String, Object> getRed_class_spawn() {
		return red_class_spawn;
	}

	public void setRed_class_spawn(Map<String, Object> red_class_spawn) {
		this.red_class_spawn = red_class_spawn;
		c.set("trenchspawns.red_class", red_class_spawn);
	}

	public Map<String, Object> getBlue_class_spawn() {
		return blue_class_spawn;
	}

	public void setBlue_class_spawn(Map<String, Object> blue_class_spawn) {
		this.blue_class_spawn = blue_class_spawn;
		c.set("trenchspawns.blue_class", blue_class_spawn);
	}

	public Map<String, Object> getSpectator_spawn() {
		return spectator_spawn;
	}

	public void setSpectator_spawn(Map<String, Object> spectator_spawn) {
		this.spectator_spawn = spectator_spawn;
		c.set("trenchspawns.spectator", spectator_spawn);
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
