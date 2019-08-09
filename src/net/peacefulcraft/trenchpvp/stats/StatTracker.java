package net.peacefulcraft.trenchpvp.stats;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class StatTracker
{
	private HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>> statMap = new HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>>();
		public HashMap<TrenchPlayer, HashMap<TrenchStat, Integer>> getStatData(){ return statMap; }
		public void clearStats() { statMap = new HashMap<TrenchPlayer, HashMap<TrenchStat,Integer>>(); }
		
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
		} else if(!(statMap.containsKey(t))) {
			HashMap<TrenchStat, Integer> temp = new HashMap<TrenchStat, Integer>();
			temp.put(stat, value);
			statMap.put(t, temp);
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
