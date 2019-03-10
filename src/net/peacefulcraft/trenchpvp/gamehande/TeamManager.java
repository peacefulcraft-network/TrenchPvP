package net.peacefulcraft.trenchpvp.gamehande;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TeamManager {
	
	private static ArrayList<TrenchPlayer> players;
		public static ArrayList<TrenchPlayer> getPlayers(){return players;}
	
	private static ScoreboardManager sbm;
	private static Scoreboard sb;
	private static Team red;
	private static Team blue;
	
	public TeamManager() {
		players = new ArrayList<TrenchPlayer>();
		sbm = Bukkit.getScoreboardManager();
		sb = sbm.getNewScoreboard();
		
		red = sb.registerNewTeam("Red");
			red.setAllowFriendlyFire(false);
		
		blue = sb.registerNewTeam("Blue");
			blue.setAllowFriendlyFire(false);
	}
	
	public TrenchPlayer joinTeam(Player p) {
		
		if(getArrayPos(p) != -1) 
			throw new RuntimeException("Command executor is already playing Trench");
		
		if(red.getSize() < blue.getSize()) {
			
			//Add to red team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeam.RED);
			red.addEntry(p.getName());
			players.add(t);
			return t;
			
		}else {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeam.BLUE);
			blue.addEntry(p.getName());
			players.add(t);
			return t;
		}
		
	}
	
	public void leaveTeam(Player p) {
		
		int target = getArrayPos(p);
		if(target == -1) {
			throw new RuntimeException("Command executor is not playing Trench");
		}
		
		if(players.get(target).getPlayerTeam() == TrenchTeam.RED)
			red.removeEntry(p.getName());
		else
			blue.removeEntry(p.getName());
		
		players.remove(target);
		
	}
	
	public static TrenchPlayer findTrenchPlayer(Player p) {
		
		int target = getArrayPos(p);
		if(target == -1)
			throw new RuntimeException("Target is not playing Trench");
		
		return players.get(target);
		
	}
	
		private static int getArrayPos(Player p) {
			
			if(players == null) {
				throw new NullPointerException("TeamManager not initialized");
			}
			
			for(int i=0; i < players.size(); i++) {
				if(players.get(i).getPlayer() == p) {
					return i;
				}
			}
			
			return -1;
		}
	
}
