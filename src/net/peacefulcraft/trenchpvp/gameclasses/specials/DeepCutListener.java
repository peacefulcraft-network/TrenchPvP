package net.peacefulcraft.trenchpvp.gameclasses.specials;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class DeepCutListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 25;
	private boolean abilityCase = false;
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Deep Cut"))) return;
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
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
	private void abilityAction(EntityDamageEvent e) {
		if(abilityCase == false) return;
		Entity entity = e.getEntity();
		if(entity instanceof Player) {
			Player victim = (Player)entity;
			victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
			victim.sendMessage("You Are Bleeding!");
		}
	}
}
