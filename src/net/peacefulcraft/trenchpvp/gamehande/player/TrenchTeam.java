package net.peacefulcraft.trenchpvp.gamehande.player;

import org.bukkit.Location;

//Add get spawns from config later
public enum TrenchTeam {
	BLUE, RED;

	public static int blueCount;
	public static int redCount;
	public static TrenchPlayer[] trenchPlayers = new TrenchPlayer[24];
	/*TODO: Cast checking*/
	public static Location getBlueSpawn(){
		return Teleports.getBlueSpawn();
	}
	
	public static Location getRedSpawn(){
		return Teleports.getRedSpawn();
	}
}

