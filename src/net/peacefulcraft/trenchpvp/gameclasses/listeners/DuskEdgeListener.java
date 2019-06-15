package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class DuskEdgeListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 15;

	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		//Checks item in main hand is Dense Axe
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dusk's Edge"))) return;

		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }

		if(!(t.getKitType() == TrenchKits.HEAVY)) return;

		if(cooldown.containsKey(p.getUniqueId()))
		{
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 80, 3));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 80, 1));
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
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 80, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 80, 1));
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		}

	}
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}

}
