package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class CatastrophicRapier extends TrenchAbility
{
	private TrenchKit k;
	
	public CatastrophicRapier(TrenchKit k) {
		super(k.getTrenchPlayer(), 40, "Catastrophic Rapier");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Catastrophic Rapier"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
		Vector forward = p.getLocation().getDirection().multiply(2);
		v.add(forward);
		
		p.setVelocity(v);
	}
}
