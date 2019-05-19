package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import java.util.PriorityQueue;

import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class BlockIgnitionTimer implements Listener{

	public static PriorityQueue<BlockTimer> flamingBlocks;
	
	public class BlockTimer implements Comparable<Long>{
		private Block b;
			public Block getBlock(){ return b; }
		private Long time;
			public Long getTime() { return time; }
	
		public BlockTimer(Block b, Long time) {
			this.b = b;
			this.time = time;
		}
		
		@Override
		public int compareTo(Long arg0) {
			return time.compareTo(arg0);
		}
	}
	
	public void onBlockIgnite(BlockIgniteEvent e) {
		flamingBlocks.add(new BlockTimer(e.getBlock(), System.currentTimeMillis()));
	}
	
}
