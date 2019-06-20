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

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class LaunchPadPlace extends TrenchAbility
{
	private TrenchKit k;
	
	public LaunchPadPlace(TrenchKit k) {
		super(k.getTrenchPlayer(), 0, "Launch Pad");
		
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
			if(upBlock != null && upBlock.getType() == Material.AIR && upBlock.getType() != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
				int itemIndex = p.getInventory().first(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
				if(itemIndex >= 0) {
					ItemStack pad = p.getInventory().getItem(itemIndex);
					
					//TODO: Add stat tracker
					
					upBlock.setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);//Places launch pad
						
					Location padLoc = upBlock.getLocation();
					p.getInventory().clear(itemIndex);
					p.sendMessage(ChatColor.RED + "Launch Pad Has Been Placed. Jump Away!");
					
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
						public void run() {
							TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		                	if(!(t.getKitType() == TrenchKits.ADRENALINE_JUNKIE)) {return;}
		                	
		                	padLoc.getBlock().setType(Material.AIR);
		                	p.getInventory().setItem(itemIndex, pad);
						}
					}, 300);
				}
			}
		}
	}
	
	
}
