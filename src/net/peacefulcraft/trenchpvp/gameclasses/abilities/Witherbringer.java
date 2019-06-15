package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class Witherbringer extends TrenchAbility{

	private TrenchKit k;
	private WitherbringerBuddy witherEnforcer;
	
	public Witherbringer(TrenchKit k, WitherbringerBuddy witherEnforcer) {
		super(k.getTrenchPlayer(), 25000);
		
		this.k = k;
		this.witherEnforcer = witherEnforcer;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Witherbringer"))) return false;

		PlayerInteractEvent e = (PlayerInteractEvent) ev;
		if(!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		//Make sure all the people we kill get withered
		witherEnforcer.enableEnforce();		
		
	}
	
}
