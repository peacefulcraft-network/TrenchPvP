package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.DemoStat;

public class BigBertha extends TrenchAbility{

	private TrenchKit k;
	
	public BigBertha(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.TNT)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Big Bertha's Embrace"))) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);//Gets block with 4 block range
		if (lookingBlock != null && lookingBlock.getType().isBlock() && lookingBlock.getType() != Material.TNT && lookingBlock.getType() != Material.AIR) {
			Block upBlock = lookingBlock.getRelative(BlockFace.UP);
			if (upBlock != null && upBlock.getType() == Material.AIR) {
				
				upBlock.setType(Material.TNT); //Places Big Bertha
				p.sendMessage(ChatColor.RED + "Fuse is lit!");
				
				TrenchPvP.getStatTracker().track(p.getUniqueId(), DemoStat.demoman_berthas_placed, 1);
				
				int itemIndex = p.getInventory().first(Material.TNT);
				
				ItemStack bomb = p.getInventory().getItem(itemIndex); //Copies bomb stack to give back later and clears
				p.getInventory().clear(itemIndex);
				
				//Schedule explosion for 5 seconds from now
				(new BerthaBomb(upBlock.getLocation())).runTaskLater(TrenchPvP.getPluginInstance(), 100);
	            
				//Schedule giving player another Bertha 25 seconds from now
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
	                public void run() {
	                	p.getInventory().setItem(itemIndex, bomb);
	                }
	            }, 300);
	            
			}
		
		}
	
	}
	
	/**
	 * Actual bomb that will explode when scheduled and given location
	 */
	private class BerthaBomb extends BukkitRunnable{

		private Location bomb;
		
		public BerthaBomb(Location bomb) {
			this.bomb = bomb;
		}
		
		@Override
		public void run() {
			
			//Delete the TNT block
			bomb.getBlock().setType(Material.AIR);
			
			//Spawn a fireball that (theoretically) explodes instantly
			Fireball f = bomb.getWorld().spawn(bomb, Fireball.class);
			f.setDirection(new Vector(0, -1, 0) );
			f.setIsIncendiary(false);
			f.setYield(3);
			
		}
	
	}

}
