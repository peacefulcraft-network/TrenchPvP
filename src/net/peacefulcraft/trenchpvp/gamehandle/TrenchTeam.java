package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;

public abstract class TrenchTeam {

	/**
	 * Teleport player to trench spawn based on TrenchPlayer.getPlayerTeam()
	 * @param p: TrenchPlayer to teleport
	 */
	public static void teleToSpawn(TrenchPlayer t) {
		Map<String, Object> spawnMap;
		if(t.getPlayerTeam() == TrenchTeams.BLUE) {
		
			spawnMap = TrenchPvP.getTrenchCFG().getBlue_spawn();
		
		} else {
			
			spawnMap = TrenchPvP.getTrenchCFG().getRed_spawn();
		
		}
		
		tele(t.getPlayer(), spawnMap);
		
	}
	
	/**
	 * Teleport player to class selection room based on TrenchPlayer.getPlayerTeam()
	 * @param t: TrenchPlayer to teleport
	 */
	public static void teleToClassSelection(TrenchPlayer t) {
		Map<String, Object> classSelMap;
		if(t.getPlayerTeam() == TrenchTeams.BLUE) {
		
			classSelMap = TrenchPvP.getTrenchCFG().getBlue_class_spawn();
		
		} else {
			
			classSelMap = TrenchPvP.getTrenchCFG().getRed_class_spawn();
		
		}
		
		tele(t.getPlayer(), classSelMap);
	}
	
	/**
	 * Teleport player to spectator area
	 * @param p: Player to teleport
	 */
	public static void teleToSpectator(Player p) {
		tele(p, TrenchPvP.getTrenchCFG().getSpectator_spawn());
	}
	
	/**
	 * Teleport player to Trench board in Minigames spawn
	 * @param p: Player to teleport
	 */
	public static void teleToGameLeave(Player p) {
		tele(p, TrenchPvP.getTrenchCFG().getQuit_spawn());
	}
	
		/**
		 * Internal teleporting function to reduce code duplication
		 * @param p: Player to teleport
		 * @param cfg: Map with world, x, y, z to create teleport Location
		 */
		private static void tele(Player p, Map<String, Object> cfg) {
			Location loc = new Location(
					Bukkit.getWorld((String) cfg.get("world")),
					Integer.valueOf((String) cfg.get("x")),
					Integer.valueOf((String) cfg.get("y")),
					Integer.valueOf((String) cfg.get("z"))
				);
		
			p.teleport(loc);
			
		}
	
}
