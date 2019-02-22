package net.peacefulcraft.trenchpvp.gameclasses;

import org.bukkit.block.Block;

public class StickyLauncher {
	private int lastFire;//Tick of last shot fired
	private Block[] boomBlocks;
	
	public void launchExplos(){
		
	}
	
	public void explodeExplos(){
		
	}
}

/*Timing  - Every 15 ticks allow shot
 * Fire projectile, get impact location, change block @ location to type pressure plate
 * -On right click w/ weapon cause explosion
 * --Store location of all blocks in array of blocks for spawning explosion
 */
