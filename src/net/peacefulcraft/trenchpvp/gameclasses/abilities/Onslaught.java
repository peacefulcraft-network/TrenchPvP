package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class Onslaught extends TrenchAbility{

	private TrenchKit k;
	private int killCount = 0;	
	
	public Onslaught(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		try{
			
			PlayerDeathEvent e = (PlayerDeathEvent) ev;
			TrenchPlayer trenchKiller = TeamManager.findTrenchPlayer(e.getEntity().getKiller());
			
			if(!(trenchKiller.getKitType() == TrenchKits.SOLDIER)) { return false; }
			
			return true;
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Matching Onsal event. Incompatible event loop " + ev.getClass());
			return false;
		}
		
	}

	@Override
	public void triggerAbility(Event ev) {
		
		try{
		
			PlayerDeathEvent e = (PlayerDeathEvent) ev;
			TrenchPlayer trenchKiller = TeamManager.findTrenchPlayer(e.getEntity().getKiller());
			TrenchPlayer trenchDead = TeamManager.findTrenchPlayer(e.getEntity());
			
			//Relevant entity is the killer, add buffs
			if(trenchKiller == k.getTrenchPlayer()) {
				Player killer = e.getEntity().getKiller();
				String killedName = e.getEntity().getName();
				int itemIndex = killer.getInventory().first(Material.REDSTONE);
				if(itemIndex >= 0) {
					ItemStack redstone = killer.getInventory().getItem(itemIndex);
					killCount++;
					
					redstone.setAmount(redstone.getAmount() + 1);
					killer.getInventory().setItem(itemIndex, redstone);
					killer.sendMessage(ChatColor.RED + "The Blood of " + killedName + "Has Been Added to Your Strength");					
					
					int damageBuff = killCount / 3;								//Every third kill
					int healthBuff = (damageBuff > 6)? killCount - 6 : 0;		//Every kill after 6th kill
					
					if(damageBuff > 0)
						killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, damageBuff));
					
					if(healthBuff > 0)
						killer.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, healthBuff));
				
					}
				
					return;
				}
			
				//If relevant entity is the dead player, reset kill count
				if(trenchDead == k.getTrenchPlayer()) {
					killCount = 0;
					return;
				}
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Matching Onslaught event. Incompatible event loop " + ev.getClass());
		}
		
		return;
	}

}
