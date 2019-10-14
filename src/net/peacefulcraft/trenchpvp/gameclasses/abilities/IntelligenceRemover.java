package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.event.Event;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSpy;

public class IntelligenceRemover extends TrenchAbility
{
	private TrenchSpy k;
	
	public IntelligenceRemover(TrenchSpy k) {
		super(k.getTrenchPlayer(), -1, "Critical Intelligenve");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		//We need to do this whenever the spy dies
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		k.resetIntelligence();
	}
}
