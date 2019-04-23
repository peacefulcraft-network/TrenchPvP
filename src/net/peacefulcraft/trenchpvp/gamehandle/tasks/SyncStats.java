package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class SyncStats extends BukkitRunnable{

	private static Connection mysql = null;
	private HashMap<UUID, HashMap<String, Integer>> stats;
	
	public void commitStats(HashMap<UUID, HashMap<String, Integer>> stats) {
		this.stats = stats;
	}
	
	@Override
	public void run() {
		
		PreparedStatement stmt_exists, stmt_insert, stmt_update;
		try {
			
			stmt_exists = mysql.prepareStatement("SELECT `uuid` FROM `gamestats` WHERE `uuid`=? LIMIT 1");
			stmt_insert = mysql.prepareStatement("INSERT INTO `gamestats` VALUES ('?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?','?')");
			stmt_update = mysql.prepareStatement("UPDATE `gamestats` SET ? = ? WHERE `uuid`=?");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		for(UUID uuid : stats.keySet()) {
			
			try {
				
				stmt_exists.setString(1, uuid.toString());
				ResultSet res = stmt_exists.executeQuery();
				
				if(res.getFetchSize() == 0) {
					
					
					
				}else {
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			stmt_exists.close();
			stmt_insert.close();
			stmt_update.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void onEnable() {
		String ip = TrenchPvP.getTrenchCFG().getDb_ip();
		String name = TrenchPvP.getTrenchCFG().getDb_name();
		String username = TrenchPvP.getTrenchCFG().getDb_user();
		String password = TrenchPvP.getTrenchCFG().getDb_password();
		String dbUrl = "jdbc:mysql://" + ip + ":3306/" + name;
		
		try {
			mysql = DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			
		}
	}
	
	public static void onDisable() {
		try {
			if(mysql != null && mysql.isClosed() != true) {
				mysql.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
