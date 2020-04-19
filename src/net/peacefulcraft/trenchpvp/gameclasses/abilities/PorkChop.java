package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
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
		try {
			
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ev;
			
			Player king = (Player) e.getDamager();
			
			if(!(king.getInventory().getItemInMainHand().getType() == Material.PORKCHOP)) { return false; }
			if(!(king.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Pork CHOP"))) { return false; }
			
		} catch(ClassCastException ex) {

			TrenchPvP.logWarning("Error Triggering PorkChop event. Incompatible event loop " + ev.getClass());

			return false;
		}
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		try {
			
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ev;
			
			Player victim = (Player) e.getEntity();
	
			if(victim.getHealth() - 6 <= 0) {
				victim.setHealth(0);
			} else {
				victim.setHealth(victim.getHealth() - 6);	
			}
			
		} catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Triggering PorkChop event. Incompatible event loop " + ev.getClass());
			return;
		}

	}

}
