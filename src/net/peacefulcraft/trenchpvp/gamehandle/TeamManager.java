package net.peacefulcraft.trenchpvp.gamehande;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class TeamManager {
	
	private static HashMap<UUID, TrenchPlayer> players;
		public static Set<UUID> getPlayers(){return players.keySet();}
		
	private static ScoreboardManager sbm;
	private static Scoreboard sb;
	private static Team red;
	private static Team blue;
	
	public TeamManager() {
		players = new HashMap<UUID, TrenchPlayer>();
		sbm = Bukkit.getScoreboardManager();
		sb = sbm.getMainScoreboard();

		if(sb.getTeam("Red") != null) {
			sb.getTeam("Red").unregister();
		}
		
		if(sb.getTeam("Blue") != null) {
			sb.getTeam("Blue").unregister();
		}
		
		red = sb.registerNewTeam("Red");
			red.setAllowFriendlyFire(false);
			red.setColor(ChatColor.RED);
		
		blue = sb.registerNewTeam("Blue");
			blue.setAllowFriendlyFire(false);
			blue.setColor(ChatColor.BLUE);
		System.out.println("Teams Initialized");
	}
	
	public TrenchPlayer joinTeam(Player p) {
		
		if(findTrenchPlayer(p) != null) 
			throw new RuntimeException("Command executor is already playing Trench");
		
		if(red.getSize() < blue.getSize()) {
			
			//Add to red team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeams.RED);
			red.addEntry(p.getName());
			players.put(p.getUniqueId(), t);
			return t;
			
		}else {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeams.BLUE);
			blue.addEntry(p.getName());
			players.put(p.getUniqueId(), t);
			return t;
		}
		
	}
	
	public void leaveTeam(Player p) {
		
		TrenchPlayer t = findTrenchPlayer(p.getPlayer());
		if(t == null) {
			throw new RuntimeException("Command executor is not playing Trench");
		}
		
		if(t.getPlayerTeam() == TrenchTeams.RED)
			red.removeEntry(p.getName());
		else
			blue.removeEntry(p.getName());
		
		players.remove(p.getUniqueId());
		
	}
	
	public static TrenchPlayer findTrenchPlayer(Player p) {
		return players.get(p.getUniqueId());
	}
	
	public static void ExecuteOnAllPlayers( PlayerWideExecutor ex) {
		for(Entry<UUID, TrenchPlayer> entry : players.entrySet()) {
			ex.execute(entry.getValue());
		}
	}
	
	public static interface PlayerWideExecutor {
		void execute(TrenchPlayer t);

	}
	
}
