package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.HeavyStat;

public class AbsoluteDefense extends TrenchAbility{

	private TrenchKit k;
	
	private final int EFFECT_TIME = 300;//Potion effect time in ticks. 20 per second
	
	public AbsoluteDefense(TrenchKit k) {
		//Set cooldown time to 20 seconds
		super(k.getTrenchPlayer(), 30000);
		
		this.k = k;
	}
	
	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbility#abilitySignature()
	 */
	@Override
	public boolean abilitySignature(Event ev) {
		Player p = k.getTrenchPlayer().getPlayer();
		
		//Checks item in main hand is Armadillo Shell
		if(!(p.getInventory().getItemInMainHand().getType() == Material.SHULKER_SHELL)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Absolute Defense"))) { return false; }
		
		return true;	
	}

	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbility#triggerAbility()
	 */
	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, EFFECT_TIME, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, EFFECT_TIME, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, EFFECT_TIME, 5));
		
		TrenchPvP.getStatTracker().track(p.getUniqueId(), HeavyStat.heavy_absolute_defense_usage, 1);
	}

}
