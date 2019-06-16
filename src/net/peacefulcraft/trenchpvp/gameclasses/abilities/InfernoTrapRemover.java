package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;

public class InfernoTrapRemover extends TrenchAbility{

	private TrenchPyro k;
	
	public InfernoTrapRemover(TrenchPyro k) {
		super(k.getTrenchPlayer(), 0);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		//We need to do this whenever the pyro dies
		return true;
		
	}

	@Override
	public void triggerAbility(Event ev) {
		
		removeInfernoTraps();
		
	}
	
	public void removeInfernoTraps() {
		for(Location loc : k.getInfernoTraps()) {
			if(loc.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
				loc.getBlock().setType(Material.AIR);
			}
		}
		k.resetInfernoTraps();
	}
	
}
