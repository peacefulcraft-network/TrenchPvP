package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class PyroGodBellWalk extends TrenchAbility
{ 
	private TrenchKit k;
	
	private boolean enforce = false;
		public void enableEnforce() { enforce = true; }
		public void disableEnforce() { enforce = false; }
	
		public PyroGodBellWalk(TrenchKit k) {
			super(k.getTrenchPlayer(), 0, "Bell of The Pyro God");
			
			this.k = k;
		}
	@Override
	public boolean abilitySignature(Event ev)
	{
		if(!enforce) {return false; }
		
		return true;
	}
	@Override
	public void triggerAbility(Event ev)	
	{
		try {
			PlayerMoveEvent e = (PlayerMoveEvent) ev;
			
			Player p = e.getPlayer();
			
			Block block = p.getLocation().getBlock().getRelative(BlockFace.UP);
			block.setType(Material.FIRE);
			
			ArrayList<Block> blockList = new ArrayList<Block>();
			
			blockList.add(block.getRelative(BlockFace.NORTH));
			blockList.add(block.getRelative(BlockFace.NORTH_EAST));
			blockList.add(block.getRelative(BlockFace.EAST));
			blockList.add(block.getRelative(BlockFace.SOUTH_EAST));
			blockList.add(block.getRelative(BlockFace.SOUTH));
			blockList.add(block.getRelative(BlockFace.SOUTH_WEST));
			blockList.add(block.getRelative(BlockFace.WEST));
			blockList.add(block.getRelative(BlockFace.NORTH_WEST));
			
			for(Block b : blockList) {
				if(b.getType().equals(Material.AIR)) {
					b.setType(Material.FIRE);
				} else if(b.getRelative(BlockFace.UP).getType().equals(Material.AIR)) {
					b.getRelative(BlockFace.UP).setType(Material.FIRE);
				}
			}
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Triggering Witherbringer event. Incompatible event loop " + ev.getClass());
			return;
		}
	}
}
