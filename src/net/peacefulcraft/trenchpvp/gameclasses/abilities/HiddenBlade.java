package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class HiddenBlade extends TrenchAbility{
	
	private final int EFFECT_TIME = 200;
	
	public HiddenBlade(TrenchKit k) {
		super(k.getTrenchPlayer(), 16000, "Hidden Blade");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		try {
			
			PlayerInteractEvent e = (PlayerInteractEvent) ev;
			Player p = e.getPlayer();
			//Checks item in main hand is Shell
			if(!(p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD)) return false;
			if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Hidden Blade"))) return false;
			if(!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {return false;}
			
			return true;
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Triggering " + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return false;
		}

	}

	@Override
	public void triggerAbility(Event ev) {
		
		try {			
			PlayerInteractEvent e = (PlayerInteractEvent) ev;
			Player p = e.getPlayer();
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, EFFECT_TIME - 40, 3)); 
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, EFFECT_TIME, 2));
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for 16 seconds.");
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
				public void run() {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, EFFECT_TIME, 3));
				}
			}, EFFECT_TIME);
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Triggering " + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return;
		}
		
	}

}
