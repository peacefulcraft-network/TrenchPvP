package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class WindsongBlade extends TrenchAbility{

	private TrenchKit k;
	
	public WindsongBlade(TrenchKit k) {
		super(k.getTrenchPlayer(), -1, "Windsong Blade");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
	
		try {
			
			PlayerMoveEvent e = (PlayerMoveEvent) ev;
			TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
			
			//if(!(t.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_SWORD)) { return false; }
			//if(!(t.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Windsong Blade"))) { return false; }
		
		}catch(ClassCastException ex) {
			
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return false;
			
		}
		
		return true;
		
	}

	@Override
	public void triggerAbility(Event ev) {
		
		try {
			
			PlayerMoveEvent e = (PlayerMoveEvent) ev;
			Player p = e.getPlayer();
					
			if((p.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD)){
				if((p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Windsong Blade"))){
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 3));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2));
				}
			}else{
				p.removePotionEffect(PotionEffectType.FAST_DIGGING);
				p.removePotionEffect(PotionEffectType.SPEED);
			}
			
		}catch(ClassCastException ex) {
			
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return;
			
		}
		
	}

}
