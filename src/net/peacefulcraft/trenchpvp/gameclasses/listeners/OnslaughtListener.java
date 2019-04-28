package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SoldierStat;

public class OnslaughtListener implements Listener
{
	private HashMap<UUID, Integer> killCount = new HashMap<UUID, Integer>();
	@EventHandler
	private void onslaughtEvent(PlayerDeathEvent e) {
		
		if( !(e.getEntity() instanceof Player)) { return; }
		if( !(e.getEntity().getKiller() instanceof Player) ) { return; }
		
		String killed = e.getEntity().getName();
		Player p = e.getEntity().getKiller();
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.SOLDIER)) return;
		
		int itemIndex = p.getInventory().first(Material.REDSTONE);
		if(itemIndex >= 0) {
			ItemStack redstone = p.getInventory().getItem(itemIndex);
			if(killCount.containsKey(p.getUniqueId())) {
				int x = killCount.get(p.getUniqueId());
				x += 1;
				killCount.put(p.getUniqueId(), x);
				onslaughtEffects(p);
				redstone.setAmount(redstone.getAmount() + 1);
				p.sendMessage(ChatColor.RED + "The Blood of " + killed + "Has Been Added to Your Strength");
			} else {
				killCount.put(p.getUniqueId(), 1);
				onslaughtEffects(p);
				p.sendMessage(ChatColor.RED + "The Blood of " + killed + "Has Been Added to Your Strength");
			}
				
		}
	}
	/*
	 * Detects soldier class death 
	 * Removes soldier from killcount HashMap
	 */
	@EventHandler
	private void soldierDeathEvent(PlayerDeathEvent e) {
		Player p = e.getEntity();
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		} catch(RuntimeException x) {
			return;
		}
		if(!(t.getKitType() == TrenchKits.SOLDIER)) return;
		
		if(killCount.containsKey(p.getUniqueId())) {
			killCount.remove(p.getUniqueId());
			onslaughtTracking(p);
		}
	}
	/*
	 * Gives soldier alternating, increasing power level effects
	 * based on killstreak
	 */
	private void onslaughtEffects(Player p) {
		int kills = killCount.get(p.getUniqueId());
		for(int i = 0; i < kills; i++) {
			if(i % 2 == 0) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 1+i));
				if(kills >= 5) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, i-4));
				}
			} else {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 1+i));
			}
		}
	}
	/*
	 * Compares and tracks highest onslaught value
	 */
	private void onslaughtTracking(Player p) {
		if(killCount.containsKey(p.getUniqueId())) {
			int streak = killCount.get(p.getUniqueId());
			if(TrenchPvP.getStatTracker().check(p.getUniqueId(), SoldierStat.soldier_highest_onslaught) == true) {
				if(streak >= TrenchPvP.getStatTracker().getValue(p.getUniqueId(), SoldierStat.soldier_highest_onslaught)) {
					TrenchPvP.getStatTracker().track(p.getUniqueId(), SoldierStat.soldier_highest_onslaught, streak);
				}
			}
		}
	}
}
