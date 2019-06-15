package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class Witherbringer extends TrenchAbility{

	private TrenchKit k;
	
	public Witherbringer(TrenchKit k) {
		super(k.getTrenchPlayer(), 25000);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Witherbringer"))) return false;
		
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
