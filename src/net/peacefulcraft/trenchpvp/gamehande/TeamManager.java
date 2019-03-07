package net.peacefulcraft.trenchpvp.gamehande;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TeamManager {
	
	private static ArrayList<TrenchPlayer> players = new ArrayList<TrenchPlayer>();
	
	//[0]:red, [1]:blue
	private static int[] teamcounts = {0,0};
	
	public static TrenchPlayer joinTeam(Player p) {
		
		if(getArrayPos(p) != -1) 
			throw new RuntimeException("Command executor is already playing Trench");
		
		if(teamcounts[0] < teamcounts[1]) {
			
			//Add to red team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeam.RED, TrenchClass.UNASSIGNED);
			teamcounts[0]++;
			players.add(t);
			return t;
			
		}else {
			
			//Add to blue team
			TrenchPlayer t = new TrenchPlayer(p, TrenchTeam.BLUE, TrenchClass.UNASSIGNED);
			teamcounts[1]++;
			players.add(t);
			return t;
		}
		
	}
	
	public static void leaveTeam(Player p) {
		
		int target = getArrayPos(p);
		if(target == -1) {
			throw new RuntimeException("Command executor is not playing Trench");
		}
		
		if(players.get(target).getPlayerTeam() == TrenchTeam.RED)
			teamcounts[0]--;
		else
			teamcounts[1]--;
		
		players.remove(target);
		
	}
	
	public static TrenchPlayer findTrenchPlayer(Player p) {
		
		int target = getArrayPos(p);
		if(target == -1)
			throw new RuntimeException("Target is not playing Trench");
		
		return players.get(target);
		
	}
	
		private static int getArrayPos(Player p) {
			
			for(int i=0; i < players.size(); i++) {
				if(players.get(i) == p) {
					return i;
				}
			}
			
			return -1;
		}
	
}
