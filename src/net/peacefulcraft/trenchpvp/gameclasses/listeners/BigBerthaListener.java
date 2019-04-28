package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.DemoStat;

public class BigBerthaListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private HashMap<UUID, ArrayList<Location>> bombCord = new HashMap<UUID,ArrayList<Location>>();
	private ArrayList<Location> bombs = new ArrayList<Location>();
	private final int COOLDOWN_TIME = 25;
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.TNT)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Big Bertha's Embrace"))) return;
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.DEMOMAN)) return;
		
		if(cooldown.containsKey(p.getUniqueId())) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				
				abilityAction(p);
				p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
				
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		} else {
			
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			abilityAction(p);
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
			
		}
	}
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	private void abilityAction(Player p) {
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);//Gets block with 4 block range
		if (lookingBlock != null && lookingBlock.getType().isBlock() && lookingBlock.getType() != Material.TNT && lookingBlock.getType() != Material.AIR) {
			Block upBlock = lookingBlock.getRelative(BlockFace.UP);
			if (upBlock != null && upBlock.getType() == Material.AIR) {
				bombCord.put(p.getUniqueId(), bombs);
				
				upBlock.setType(Material.TNT); //Places Big Bertha
				bombCord.get(p.getUniqueId()).add(upBlock.getLocation());
				p.sendMessage(ChatColor.RED + "Fuse is lit!");
				
				TrenchPvP.getStatTracker().track(p.getUniqueId(), DemoStat.demoman_bethas_placed, 1);
				
				ItemStack bomb = p.getInventory().getItem(1); //Copies bomb stack and clears
				p.getInventory().clear(1);
				
				BukkitScheduler scheduler = Bukkit.getServer().getScheduler();//Delayed explosion; 5 seconds
	            scheduler.scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
	                public void run() {
	                	ArrayList<Location> bombs = bombCord.get(p.getUniqueId());
	                	
	                	for(Location temp : bombs) {
	                		if(temp.getBlock().getType() == Material.TNT) {
	                			temp.getBlock().setType(Material.AIR);
	                			Fireball f = p.launchProjectile(Fireball.class);
	                			Vector v =  new Vector(0, -1, 0);
	                			f.setVelocity(v);
	                			f.setIsIncendiary(false);
	                			f.setYield(3);
	                			//temp.getWorld().createExplosion(temp.getX(), temp.getY(), temp.getZ(), 8.0f, false, false);
	                		}
	                	}
	                }
	            }, 100);
	            BukkitScheduler scheduler2 = Bukkit.getServer().getScheduler();//Delayed item return; 24 seconds
	            scheduler2.scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
	                public void run() {
	                	p.getInventory().setItem(1, bomb);
	                }
	            }, 300);
	            
			}
		}
	}
}
