package net.peacefulcraft.trenchpvp.gamehandle.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchScoreboard;

/**
 * A mostly accurate game timer.
 * It will desync if there is heavy TPS loss
 * TODO: Have this run Async and trigger a sync task to update timer for maximum accuracy
 */
public class ArenaTimer extends BukkitRunnable{

	private long mSecRemaining;
		public long getMSecRemaining() { return mSecRemaining; }
		public int getMinutesRemaining() { return (int) (mSecRemaining / 60000); }
		public int getRoundedSecondsRemaining() { return (int) (mSecRemaining / 1000) % 60; }
	
	private TrenchScoreboard target;
	
	public ArenaTimer(TrenchScoreboard target) {
		this.target = target;
		mSecRemaining = 600000;
	}
	
	@Override
	public void run() {
		mSecRemaining-= 1000L;
		target.setTimerTime(String.format("%02d:%02d", getMinutesRemaining(), getRoundedSecondsRemaining()));
		
		//Schedule next decrement if time is left
		if(mSecRemaining < 1000)
			this.cancel();
	}

}
