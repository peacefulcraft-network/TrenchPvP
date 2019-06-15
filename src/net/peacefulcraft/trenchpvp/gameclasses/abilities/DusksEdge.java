package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class DusksEdge extends TrenchAbility{

	private TrenchKit k;
	
	public DusksEdge(TrenchKit k) {
		super(k.getTrenchPlayer(), 15000);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		Player p = k.getTrenchPlayer().getPlayer();
		//Checks item in main hand is Dense Axe
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dusk's Edge"))) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 80, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 80, 3));
		
	}

}
