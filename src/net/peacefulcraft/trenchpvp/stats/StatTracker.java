package net.peacefulcraft.trenchpvp.stats;

import java.util.HashMap;
import java.util.UUID;

public class StatTracker
{
	private HashMap<UUID, HashMap<TrenchStat, Integer>> statMap = new HashMap<UUID, HashMap<TrenchStat, Integer>>();
		public HashMap<UUID, HashMap<TrenchStat, Integer>> getStatData(){ return statMap; }
		public void clearStats() { statMap = new HashMap<UUID, HashMap<TrenchStat,Integer>>(); }
		
	public StatTracker() {

	}
	public interface TrenchStat {}
	
	public void track(UUID id, TrenchStat stat, int value) {
		if(statMap.containsKey(id)) {
			HashMap<TrenchStat, Integer> temp = statMap.get(id);
			if(temp.containsKey(stat)) {
				temp.put(stat, temp.get(stat) + value);
				statMap.put(id, temp);
			} else if(!(temp.containsKey(stat))) {
				temp.put(stat, value);
				statMap.put(id, temp);
			}
		} else if(!(statMap.containsKey(id))) {
			HashMap<TrenchStat, Integer> temp = new HashMap<TrenchStat, Integer>();
			temp.put(stat, value);
			statMap.put(id, temp);
		}
	}
	public boolean check(UUID id, TrenchStat stat) {
		boolean check = true;
		if(statMap.containsKey(id)) {
			HashMap<TrenchStat, Integer> temp = statMap.get(id);
			if(temp.containsKey(stat)) {
				check = true;
			} else {
				check = false;
			}
		}
		return check;
	}
	public void resetEntry(UUID id, TrenchStat stat) {
		HashMap<TrenchStat, Integer> temp = statMap.get(id);
		temp.put(stat, 0);
		statMap.put(id, temp);
	}
	public int getValue(UUID id, TrenchStat stat) {
		HashMap<TrenchStat, Integer> temp = statMap.get(id);
		int value = temp.get(stat);
		return value;
	}
}
