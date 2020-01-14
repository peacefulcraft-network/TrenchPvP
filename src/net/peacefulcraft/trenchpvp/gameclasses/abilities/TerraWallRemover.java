package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchElementalist;

public class TerraWallRemover extends TrenchAbility 
{
	private TrenchElementalist k;
	
	public TerraWallRemover(TrenchElementalist k) {
		super(k.getTrenchPlayer(), -1, "Terra Wall");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		//We need it to always activate on death
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		removeTerraWalls();
	}
	
	public void removeTerraWalls() {
		for(Location loc : k.getTerraWalls()) {
			loc.getBlock().setType(Material.AIR);
		}
		k.resetTerraWalls();
	}
	
	
}
