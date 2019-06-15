package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class WitherbringerListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 25;
	private boolean abilityCase = false;
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Witherbringer"))) return;
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.SOLDIER)) return;
		
		if(cooldown.containsKey(p.getUniqueId())) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				//Ability method here
				abilityCase = true;
				
				p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
			} else if(canUseAgain(p) == false) {
				abilityCase = false;
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			//Ability method here
			abilityCase = true;
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		}
	}
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	@EventHandler
	private void abilityAction(EntityDamageByEntityEvent e) {
		if(abilityCase == false) return;
		Entity damager = e.getDamager();
		Entity victim = e.getEntity();
		if(damager instanceof Player && victim instanceof Player) {
			Player soldier = (Player)damager;
			if(!(soldier.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) return;
			if(!(soldier.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Witherbringer"))) return;
			
			Player vic = (Player)victim;
			vic.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
		}
		abilityCase = true; //Quick fix to continuous wither damage occurrence. Change?
	}
}
