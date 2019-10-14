package net.peacefulcraft.trenchpvp.stats;

import java.util.HashMap;

import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;

public class StatTracker
{
	private HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>> statMap = new HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>>();
		public HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>> getStatData(){ return statMap; }
	
	private HashMap<TrenchPlayer, Double> bounties = new HashMap<TrenchPlayer, Double>();
		public HashMap<TrenchPlayer, Double> getBounties(){ return bounties; }
	
	public void clearStats() { 
		statMap = new HashMap<TrenchPlayer, HashMap<TrenchStat,Integer>>(); 
		bounties = new HashMap<TrenchPlayer, Double>();
	}
	
	public StatTracker() {

	}
	public interface TrenchStat {}
	
	public void track(TrenchPlayer t, TrenchStat stat, int value) {
		if(statMap.containsKey(t)) {
			HashMap<TrenchStat, Integer> temp = statMap.get(t);
			if(temp.containsKey(stat)) {
				temp.put(stat, temp.get(stat) + value);
				statMap.put(t, temp);
			} else if(!(temp.containsKey(stat))) {
				temp.put(stat, value);
				statMap.put(t, temp);
			}
			
		} else {
			HashMap<TrenchStat, Integer> temp = new HashMap<TrenchStat, Integer>();
			temp.put(stat, value);
			statMap.put(t, temp);
		}
		
		if((stat instanceof GeneralStat) && stat == GeneralStat.player_kills) {
			if(bounties.containsKey(t)) {
				bounties.put(t, bounties.get(t) + 2.5);
			}else {
				bounties.put(t, 2.5);
			}
		}
	}
	
	public boolean check(TrenchPlayer t, TrenchStat stat) {
		boolean check = true;
		if(statMap.containsKey(t)) {
			HashMap<TrenchStat, Integer> temp = statMap.get(t);
			if(temp.containsKey(stat)) {
				check = true;
			} else {
				check = false;
			}
		}
		return check;
	}
	public void resetEntry(TrenchPlayer t, TrenchStat stat) {
		HashMap<TrenchStat, Integer> temp = statMap.get(t);
		temp.put(stat, 0);
		statMap.put(t, temp);
	}
	public int getValue(TrenchPlayer t, TrenchStat stat) {
		HashMap<TrenchStat, Integer> temp = statMap.get(t);
		if(!(temp.containsKey(stat))) { return 0; }
		
		int value = temp.get(stat);
		return value;
	}
}
