package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class WitherbringerBuddy extends TrenchAbility{

	private TrenchKit k;
	
	private static long ABILITY_DURATION = 10000;
	
	private boolean enforce = false;;
		public void enableEnforce() { 
			enforce = true;
			expiration = System.currentTimeMillis() + ABILITY_DURATION;
		}
		
		public void disableEnforce() { enforce = false; }
	
	private long expiration = System.currentTimeMillis();
		
	public WitherbringerBuddy(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		if(!enforce) { return false; }
		if(System.currentTimeMillis() > expiration) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		try{ 
			//Make sure we can use this event.
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ev;
		
			Player victim = (Player) e.getEntity();
			victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
			victim.sendMessage("You Are Bleeding!");
		
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Triggering Witherbringer event. Incompatible event loop " + ev.getClass());
			return;
		}
		
	}
	
}
