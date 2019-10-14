package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker.TrenchStat;

public class SyncBounties extends BukkitRunnable{

	private static Connection mysql = null;
	private HashMap<TrenchPlayer, Double> bounties;
	
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
		
		PreparedStatement stmt;
		try {
			
			stmt = mysql.prepareStatement("INSERT INTO `bounties` (`uuid`,`payout`) VALUES(?,?)"
										+ "	ON DUPLICATE KEY UPDATE `payout`=`payout` + ?");

						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		for(TrenchPlayer t : bounties.keySet()) {
			
			UUID uuid = t.getPlayer().getUniqueId();
			
			try {
				
				stmt.setString(1, uuid.toString().replace("-", ""));
				stmt.setDouble(2, bounties.get(t));
				stmt.setDouble(3, bounties.get(t));
				System.out.println(stmt.toString());
				stmt.execute();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}

		try {
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//We just need to terminate the database connection
		onDisable();
	}

	@SuppressWarnings("unchecked")
	public void commitBounties(HashMap<TrenchPlayer, Double> bounties) {
		this.bounties = (HashMap<TrenchPlayer, Double>) bounties.clone();
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
