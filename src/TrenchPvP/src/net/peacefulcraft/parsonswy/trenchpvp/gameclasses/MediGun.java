package net.peacefulcraft.parsonswy.trenchpvp.gameclasses;

import net.minecraft.server.v1_8_R1.MinecraftServer;

public class MediGun {
	private int lastFire;
	
	public void updateFire(){
		lastFire = MinecraftServer.currentTick;
	}
	
	public int getFire(){
		return lastFire;
	}
}
