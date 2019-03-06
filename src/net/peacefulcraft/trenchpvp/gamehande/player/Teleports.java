package net.peacefulcraft.trenchpvp.gamehande.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class Teleports {
	
	/**
	 * @return Teleportable location for the blue in-arena spawn
	 */
	public static Location getBlueSpawn(){
		return new Location(
			Bukkit.getWorld((String) TrenchPvP.getTrenchCFG().getBlue_spawn().get("world")),
				(double) TrenchPvP.getTrenchCFG().getBlue_spawn().get("x"),
				(double) TrenchPvP.getTrenchCFG().getBlue_spawn().get("y"),
				(double) TrenchPvP.getTrenchCFG().getBlue_spawn().get("z")
			);
	}
	
	/**
	 * @return Teleportable location for the blue class selection room
	 */
	public static Location getBlueClassSpawn() {
		return new Location(
				Bukkit.getWorld((String) TrenchPvP.getTrenchCFG().getRed_class_spawn().get("world")),
					(double) TrenchPvP.getTrenchCFG().getBlue_class_spawn().get("x"),
					(double) TrenchPvP.getTrenchCFG().getBlue_class_spawn().get("y"),
					(double) TrenchPvP.getTrenchCFG().getBlue_class_spawn().get("z")
			);
	}
	
	/**
	 * @return Teleportable location for the red in-arena spawn
	 */
	public static Location getRedSpawn(){
		return new Location(
			Bukkit.getWorld((String) TrenchPvP.getTrenchCFG().getRed_spawn().get("world")),
				(double) TrenchPvP.getTrenchCFG().getRed_spawn().get("x"),
				(double) TrenchPvP.getTrenchCFG().getRed_spawn().get("y"),
				(double) TrenchPvP.getTrenchCFG().getRed_spawn().get("z")
		);
	}
	
	/**
	 * @return Teleportable location for the red class selection room
	 */
	public static Location getRedClassSpawn() {
		return new Location(
				Bukkit.getWorld((String) TrenchPvP.getTrenchCFG().getRed_class_spawn().get("world")),
					(double) TrenchPvP.getTrenchCFG().getRed_class_spawn().get("x"),
					(double) TrenchPvP.getTrenchCFG().getRed_class_spawn().get("y"),
					(double) TrenchPvP.getTrenchCFG().getRed_class_spawn().get("z")
			);
	}
	
	/**
	 * @return Teleportable location for /tleave target location
	 */
	public static Location getQuitSpawn() {
		return new Location(
			Bukkit.getWorld((String) TrenchPvP.getTrenchCFG().getQuit_spawn().get("world")),
				(double) TrenchPvP.getTrenchCFG().getQuit_spawn().get("x"),
				(double) TrenchPvP.getTrenchCFG().getQuit_spawn().get("y"),
				(double) TrenchPvP.getTrenchCFG().getQuit_spawn().get("z")
		);
	}
	
}
