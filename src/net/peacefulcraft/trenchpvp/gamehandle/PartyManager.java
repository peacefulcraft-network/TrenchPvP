package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.HashMap;

import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class PartyManager
{
	private HashMap<String, TrenchParty> parties = new HashMap<String, TrenchParty>();
		public HashMap<String, TrenchParty> getListParties() { return parties; }
		
	private HashMap<String, Integer> listPartiesToDelete;
		public HashMap<String, Integer> getPartiesToDelete() {return listPartiesToDelete;}
		
	public PartyManager() {
		parties = new HashMap<String, TrenchParty>();
	}
	
	public TrenchParty loadParty(String name) {
		TrenchParty ret = getParty(name);
		if(ret != null) {
			getListParties().put(name.toLowerCase(), ret);
		}
		return ret;
	}
	
	public void unloadParty(String name) {
		getListParties().remove(name.toLowerCase());
	}
	
	public boolean reloadParty(String name) {
		if(getListParties().containsKey(name.toLowerCase())) {
			//TrenchParty party = (TrenchParty) //TODO:Getting party from file
			//getListParties().put(name.toLowerCase(), party);
			
			return true;
		}
		return false;
	}
	
	public TrenchParty getParty(String name) {
		TrenchParty ret = null;
		if(name != null && !name.isEmpty()) {
			ret = getListParties().get(name.toLowerCase());
			if(ret == null) {
				//TODO: Get party from database ??
			}
		}
		return ret;
	}
	
}
