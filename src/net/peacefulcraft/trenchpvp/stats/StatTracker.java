package net.peacefulcraft.trenchpvp.stats;

import java.util.HashMap;
import java.util.UUID;

public class StatTracker
{
	private HashMap<UUID, HashMap<String, Integer>> statMap = new HashMap<UUID, HashMap<String, Integer>>();
	public HashMap<String, Integer> data = new HashMap<String, Integer>();
	
	public StatTracker() {
		
	}
	
	public void insert(HashMap<String, Integer> inner, UUID player) {
		statMap.put(player, inner);
	}
	
	
}
