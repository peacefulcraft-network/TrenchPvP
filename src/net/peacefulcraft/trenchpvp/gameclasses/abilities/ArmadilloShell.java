package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.HeavyStat;

public class ArmadilloShell extends TrenchAbility{

	private TrenchKit k;
	
	public ArmadilloShell(TrenchKit k) {
		//Set cooldown time to 20 seconds
		super(k.getTrenchPlayer(), 20000);
		
		this.k = k;
	}
	
	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbility#abilitySignature()
	 */
	@Override
	public boolean abilitySignature() {
		Player p = k.getTrenchPlayer().getPlayer();
		//Checks item in main hand is Armadillo Shell
		if(!(p.getInventory().getItemInMainHand().getType() == Material.SHULKER_SHELL)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Armadillo Shell"))) { return false; }
				
		return canUseAbility();	
	}

	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbility#triggerAbility()
	 */
	@Override
	public void triggerAbility() {
		
		markAbilityUsed();
		
		Player p = k.getTrenchPlayer().getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 140, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 140, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 140, 5));
		
		TrenchPvP.getStatTracker().track(p.getUniqueId(), HeavyStat.heavy_armadillo_usage, 1);
	}

}
