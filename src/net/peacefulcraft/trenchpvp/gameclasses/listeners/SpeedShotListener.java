package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SpyStat;

public class SpeedShotListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 16;
	
	@EventHandler
	private void onrightClick(PlayerInteractEntityEvent e)
	{
		Player p = e.getPlayer();
		//Checks item in main hand is 
		if(!(p.getInventory().getItemInMainHand().getType() == Material.CHORUS_FLOWER)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Speed Shot"))) return;
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SPY)) return;
		
		if(cooldown.containsKey(p.getUniqueId())) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				abilityEffects(e);
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			abilityEffects(e);
		}
	}
	private boolean canUseAgain(Player player) {
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
	}
	private void abilityEffects(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		TrenchPlayer spy, target;
		try {
			spy = TeamManager.findTrenchPlayer(e.getPlayer());
			target = TeamManager.findTrenchPlayer((Player)e.getRightClicked());
		} catch (RuntimeException x) {
			return;
		}
		if(!(spy.getPlayerTeam() == target.getPlayerTeam())) return;
		target.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 140, 4));
		p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		
		TrenchPvP.getStatTracker().track(p.getUniqueId(), SpyStat.spy_speed_shot_usage, 1);
	}
}
