package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;

public class TeamManager {
	
	private static HashMap<UUID, TrenchPlayer> players;
		public static Set<UUID> getPlayers(){return players.keySet();}

	private static TrenchScoreboard scoreboard;
	public static TrenchScoreboard getScoreboard() { return scoreboard; }
	
	
	
	public TeamManager() {
		players = new HashMap<UUID, TrenchPlayer>();
	}
	
	public TrenchPlayer joinTeam(Player p) {
		
		Team red = scoreboard.getRedTeam();
		Team blue = scoreboard.getBlueTeam();
		
		if(findTrenchPlayer(p) != null) 
			throw new RuntimeException("Command executor is already playing Trench");
		
		if(red.getSize() < blue.getSize()) {
			
			//Add to red team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeams.RED);
			red.addEntry(p.getName());
			scoreboard.registerRedPlayer();
			players.put(p.getUniqueId(), t);
			return t;
			
		}else {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeams.BLUE);
			blue.addEntry(p.getName());
			scoreboard.registerBluePlayer();
			players.put(p.getUniqueId(), t);
			return t;
		}
		
	}
	
	public void leaveTeam(Player p) {
		
		TrenchPlayer t = findTrenchPlayer(p.getPlayer());
		if(t == null) {
			throw new RuntimeException("Command executor is not playing Trench");
		}
		
		if(t.getPlayerTeam() == TrenchTeams.RED) {
			Team red = scoreboard.getRedTeam();
			red.removeEntry(p.getName());
			scoreboard.unregisterRedPlayer();
		}else {
			Team blue = scoreboard.getBlueTeam();
			blue.removeEntry(p.getName());
			scoreboard.unregisterBluePlayer();
		}
		
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
