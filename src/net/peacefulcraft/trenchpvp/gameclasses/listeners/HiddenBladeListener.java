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

public class HiddenBladeListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 16;
	
	@EventHandler
	private void onrightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		//Checks item in main hand is Shell
		if(!(p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Hidden Blade"))) return;
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.SPY)) return;
	
		if(cooldown.containsKey(p.getUniqueId()))
		{
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true)
			{
				abilityEffects(p); 
				p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
			}
			else if(canUseAgain(p) == false)
			{
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		}
		else
		{
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			abilityEffects(p);
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		}
		
	}
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	private void abilityEffects(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3)); 
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 160, 1));
	}
	@EventHandler
	private void spyDamage(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		Entity victim = e.getEntity();
		if(damager instanceof Player && victim instanceof Player) {
			Player spy = (Player)damager;
			TrenchPlayer t = TeamManager.findTrenchPlayer(spy);
			if(t == null) { return; }

			if(!(t.getKitType() == TrenchKits.SPY)) return;
			
			if(spy.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				e.setCancelled(true);
			}
		}
	}
}
