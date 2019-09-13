package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchElementalist;

public class TerraWall extends TrenchAbility
{
	private TrenchElementalist k;
	
	public TerraWall(TrenchElementalist k) {
		super(k.getTrenchPlayer(), 25000, "Terra Wall");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();

		if(!(p.getInventory().getItemInMainHand().getType() == Material.EMERALD_ORE)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Terra Wall"))) { return false; }
		if(p.getTargetBlock((Set<Material>) null, 4).getType().equals(Material.AIR)) {return false;}
		
		return true;	
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);
		Block upBlock = lookingBlock.getRelative(BlockFace.UP);
		if(upBlock != null && upBlock.getType().equals(Material.AIR)) {
			for(int y=0; y <= 6; y++) {
				Block replace = upBlock.getRelative(BlockFace.UP, y);
				
				for(int i=-1; i <= 1; i+=2) {
					Block side = replace.getRelative(getSideDirections(p), i);
					if(side.getType().equals(Material.AIR) && side != null) {
						side.setType(Material.STONE);
						k.getTerraWalls().add(side.getLocation());
					} 
				}
				
				if(replace.getType().equals(Material.AIR) && replace != null) {
					replace.setType(Material.STONE);
					k.getTerraWalls().add(replace.getLocation()); //Adding block locations to wall list
				} else {
					break;
				}
			}
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
			public void run() {
				p.sendMessage(ChatColor.RED + "Terra Wall has crumbled!");
				for(Location loc : k.getTerraWalls()) {
					if(loc.getBlock().getType().equals(Material.STONE)) {
						loc.getBlock().setType(Material.AIR);
					}
				}
			}
		}, 200);
	}
	
	private BlockFace getSideDirections(Player p) {
		double rot = (p.getLocation().getYaw() - 90) % 360;
		if(rot < 0 ) {
			rot += 360.0;
		}
		if(0 <= rot && rot < 45.0) {
			return BlockFace.NORTH; //Facing North
		} else if(45.0 <= rot && rot < 135.0) {
			return BlockFace.EAST; //Facing East
		} else if(135.0 <= rot && rot < 225.0) {
			return BlockFace.NORTH; //Facing South
		} else if(225.0 <= rot && rot < 315.0) {
			return BlockFace.EAST; //Facing West
		} else if(315.0 <= rot && rot < 360.0) {
			return BlockFace.NORTH; //Facing North
		} else {
			return null;
		}
	}
}
