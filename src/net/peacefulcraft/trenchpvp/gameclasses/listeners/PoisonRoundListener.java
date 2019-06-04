package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitScheduler;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SniperStat;

public class PoisonRoundListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 12;

	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		//Checks item in main hand is Dense Axe
		if(!(p.getInventory().getItemInMainHand().getType() == Material.ARROW)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Bullets"))) return;
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.SNIPER)) return;
		
		if(cooldown.containsKey(id)) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				
				ammoSwitch(p);
				
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			
			ammoSwitch(p);
			
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		}
	}
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	private void ammoSwitch(Player p) {
		int itemIndex = p.getInventory().first(Material.ARROW);
		if(itemIndex >= 0) {
			ItemStack arrows = p.getInventory().getItem(itemIndex);
			int arrowAmount = arrows.getAmount();
			
			ItemStack tipped = new ItemStack(Material.TIPPED_ARROW, arrowAmount);
			PotionMeta pMeta = (PotionMeta) tipped.getItemMeta();
			pMeta.setBasePotionData(new PotionData(PotionType.POISON));//Creating tipped arrows
			pMeta.setDisplayName("Poison Bullets");
			tipped.setItemMeta(pMeta);
			
			p.getInventory().setItem(itemIndex, tipped);
			
			TrenchPvP.getStatTracker().track(p.getUniqueId(), SniperStat.sniper_poison_upgrades, 1);
			
			//Delay arrow return switch
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
                //@Override
                public void run() {
                	ItemStack t = p.getInventory().getItem(itemIndex);
                	arrows.setAmount(t.getAmount());
                	p.getInventory().setItem(itemIndex, arrows);
                }
            }, 100);
		}
	}
}
