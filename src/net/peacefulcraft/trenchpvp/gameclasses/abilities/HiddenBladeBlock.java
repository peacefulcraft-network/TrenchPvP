package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class HiddenBladeBlock extends TrenchAbility
{
	public HiddenBladeBlock(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ev;
		Entity damager = e.getDamager();
		Entity victim = e.getEntity();
		
		if(damager instanceof Player && victim instanceof Player) {
			return true;
		}
		return false;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ev;
		Entity damager = e.getDamager();
		Entity victim = e.getEntity();
		
		if(damager instanceof Player && victim instanceof Player) {
			Player spy = (Player) damager;
			if(spy.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				e.setCancelled(true);
			}
		}
		
	}
	
	

}
