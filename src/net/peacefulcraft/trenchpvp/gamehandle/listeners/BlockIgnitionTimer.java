package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import java.util.PriorityQueue;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class BlockIgnitionTimer extends BukkitRunnable implements Listener{

	public static PriorityQueue<BlockTimer> flamingBlocks = new PriorityQueue<BlockTimer>();
	
	public BlockIgnitionTimer() {
		this.runTaskTimer(TrenchPvP.getPluginInstance(), 20, Long.MAX_VALUE);
	}
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent e) {
		flamingBlocks.add(new BlockTimer(e.getBlock(), System.currentTimeMillis()+10000));
	}
	
	public class BlockTimer implements Comparable<BlockTimer>{
		private Block b;
			public Block getBlock(){ return b; }
		private Long time;
			public Long getTime() { return time; }
	
		public BlockTimer(Block b, Long time) {
			this.b = b;
			this.time = time;
		}
		
		@Override
		public int compareTo(BlockTimer arg0) {
			if(time > arg0.getTime())
				return 1;
			else 
				return -1;
		}
	}

	@Override
	public void run() {

		int i = 0;
		while(!flamingBlocks.isEmpty() && i < 50) {
			
			if(flamingBlocks.peek().getTime() > System.currentTimeMillis()) {
				break;
			}
			flamingBlocks.remove().getBlock().setType(Material.AIR);
			i++;
		}
	}
}
