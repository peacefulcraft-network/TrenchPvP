package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class PorkChop extends TrenchAbility
{
	private TrenchKit k;
	
	public PorkChop(TrenchKit k)
	{
		super(k.getTrenchPlayer(), 30000, "Pork Chop");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.PORKCHOP)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Pork CHOP"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		//TODO
	}

}
