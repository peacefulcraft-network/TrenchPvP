package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import java.util.PriorityQueue;

import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.BlockIgnitionTimer;
import net.peacefulcraft.trenchpvp.gamehandle.listeners.BlockIgnitionTimer.BlockTimer;

public class FireManager extends BukkitRunnable{

	@Override
	public void run() {
		PriorityQueue<BlockTimer> flamingBlocks = BlockIgnitionTimer.flamingBlocks;
		while(!flamingBlocks.isEmpty() && (flamingBlocks.peek().getTime()-10000 > System.currentTimeMillis())) {
			flamingBlocks.remove().getBlock().breakNaturally();
		}
		
		(new FireManager()).runTaskLater(TrenchPvP.getPluginInstance(),20);
		
	}

}
