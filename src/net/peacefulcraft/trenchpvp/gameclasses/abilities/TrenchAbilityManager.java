package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.Event;

public class TrenchAbilityManager{

	private HashMap<TrenchAbilityType, ArrayList<TrenchAbility>> abilities = new HashMap<TrenchAbilityType, ArrayList<TrenchAbility>>();
	
	public void registerAbility(TrenchAbilityType type, TrenchAbility ability) throws IllegalStateException{
		
		if(abilities.get(type) == null) {
			abilities.put(type, new ArrayList<TrenchAbility>());
			abilities.get(type).add(ability);
			return;
		}
		
		for(TrenchAbility ta : abilities.get(type)) {
			
			if(ta.getClass().toString().equals(ability.getClass().toString())) {
				throw new IllegalStateException("TrenchAbility child " + ability.getClass() + " is already registered with this executor");	
			}
			
			abilities.get(type).add(ability);			
			break;
		}
		
	}
	
	public void abilityExecuteLoop(TrenchAbilityType type, Event ev) {
	
		if(abilities.get(type) == null) {
			return;
		}
		
		for(TrenchAbility ability : abilities.get(type)) {
			
			if(ability.abilitySignature(ev) && ability.canUseAbility()) {
				
				ability.execute(ev);
				ability.markAbilityUsed();
				
			}
			
		}
		
	}
	
}
