package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker.TrenchStat;

public class SyncStats extends BukkitRunnable{

	private static Connection mysql = null;
	private HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>> stats;
	
	@SuppressWarnings("unchecked")
	public void commitStats(HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>> stats) {
		this.stats = (HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>>) stats.clone();
	}
	
	@Override
	public void run() {
		
		//Mkae sure we have a fresh database connection
		//Lingering old ones have probably gone away.
		try {
			if(mysql != null && !mysql.isClosed()) {
				onDisable();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Reconnect to database
		onEnable();
		
		PreparedStatement stmt_exists, stmt_insert;
		try {
			
			stmt_exists = mysql.prepareStatement("SELECT `uuid` FROM `gamestats` WHERE `uuid`=? LIMIT 1");
			stmt_insert = mysql.prepareStatement("INSERT INTO `gamestats` (`uuid`,`username`) VALUES (?,?)");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		for(TrenchPlayer t : stats.keySet()) {
			
			UUID uuid = t.getPlayer().getUniqueId();
			
			try {

				stmt_exists.setString(1, uuid.toString().replace("-", ""));
				ResultSet res = stmt_exists.executeQuery();
				
				if(!res.next()) {
					
					stmt_insert.setString(1, uuid.toString().replace("-", ""));
					stmt_insert.setString(2, t.getPlayer().getName());
					stmt_insert.execute();
					res = stmt_insert.getResultSet();
					
				}
				
				//Construct pairs
				String query = "UPDATE `gamestats` SET ";
				HashMap<TrenchStat, Integer> userStats = stats.get(t);
				ArrayList<Integer> values = new ArrayList<Integer>();
				for(TrenchStat stat : userStats.keySet()) {
					
					values.add(userStats.get(stat));
					query += "`" + stat + "`=`" + stat + "` + ?, ";
					
				}
				query += " `username`=? WHERE `uuid`=? LIMIT 1";
				PreparedStatement stmt_update = mysql.prepareStatement(query);
				
				//Bind the pairs
				for(int i=1; i<=values.size(); i++) {
					stmt_update.setInt(i, values.get(i-1));
				}
				//Bind username for update in case player changed their username
				stmt_update.setString(values.size()+1, t.getPlayer().getName());
				
				//Bind UUID as key
				stmt_update.setString(values.size()+2, uuid.toString().replace("-", ""));
				
				//Debug and execute
				System.out.println(stmt_update.toString());
				stmt_update.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			stmt_exists.close();
			stmt_insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//We just need to terminate the database connection
		onDisable();
		
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
			e.printStackTrace();
		}
	}
	
	public static void onDisable() {
		try {
			if(mysql != null && mysql.isClosed() != true) {
				mysql.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
