package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class DusksEdge extends TrenchAbility{

	private TrenchKit k;
	private final int EFFECT_TIME = 200;//Potion effect time in ticks. 20 per second
	
	public DusksEdge(TrenchKit k) {
		super(k.getTrenchPlayer(), 25000, "Dusks Edge");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		Player p = k.getTrenchPlayer().getPlayer();
		//Checks item in main hand is Dense Axe
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dusk's Edge"))) return false;
		PlayerInteractEvent e = (PlayerInteractEvent) ev;
		if(!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {return false;}
		
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, EFFECT_TIME, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, EFFECT_TIME, 3));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, EFFECT_TIME, 3));
		
	}

}
