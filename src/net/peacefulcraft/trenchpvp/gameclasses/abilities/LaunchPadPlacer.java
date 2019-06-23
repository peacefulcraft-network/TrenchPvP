package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class LaunchPadPlacer extends TrenchAbility
{
	private TrenchKit k;
	
	public LaunchPadPlacer(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Launch Pad"))) return false;
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);	
		if(lookingBlock != null && lookingBlock.getType().isBlock() && lookingBlock.getType() != Material.HEAVY_WEIGHTED_PRESSURE_PLATE && lookingBlock.getType() != Material.AIR) {
			Block upBlock = lookingBlock.getRelative(BlockFace.UP);
			if(upBlock != null && upBlock.getType() == Material.AIR) {
				
				upBlock.setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
				
				int itemIndex = p.getInventory().first(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
				ItemStack pad = p.getInventory().getItem(itemIndex);
				p.getInventory().clear(itemIndex);
				
				(new LaunchPad(upBlock.getLocation())).runTaskLater(TrenchPvP.getPluginInstance(), 300);
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
					public void run() {
						TrenchPlayer t = TeamManager.findTrenchPlayer(p);
						if(!(t.getKitType() == TrenchKits.ADRENALINE_JUNKIE)) {return;}
						
						p.getInventory().setItem(itemIndex, pad);
					}
				}, 300);
			}
		}
	}
	
	private class LaunchPad extends BukkitRunnable {
		private Location pad;
		
		public LaunchPad(Location pad) {
			this.pad = pad;
		}
		
		@Override
		public void run() {
			pad.getBlock().setType(Material.AIR);
		}
	}
}
