package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SniperStat;

public class PowerShotListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 15;

	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		//Checks item in main hand is Dense Axe
		if(!(p.getInventory().getItemInMainHand().getType() == Material.PISTON)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Power Shot"))) return;
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SNIPER)) return;
		
		if(cooldown.containsKey(id)) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				
				bowSwitch(p);
				
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			
			bowSwitch(p);
			
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		}
	}
	public boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	private void bowSwitch(Player p) {
		int itemIndex = p.getInventory().first(Material.BOW);
		if(itemIndex >= 0) {
			ItemStack rifle = p.getInventory().getItem(itemIndex);
			
			ItemStack shotR = new ItemStack(Material.BOW);
			shotR.addEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
			shotR.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
			
			ItemMeta meta = shotR.getItemMeta();
			meta.setDisplayName("Component Rifle Mk.IV");
			shotR.setItemMeta(meta);
			
			p.getInventory().setItem(itemIndex, shotR);
			
			TrenchPvP.getStatTracker().track(p.getUniqueId(), SniperStat.sniper_power_shot_upgrades, 1);
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
                //@Override
                public void run() {
                	p.getInventory().setItem(itemIndex, rifle);
                }
            }, 100);
		}
	}
}
