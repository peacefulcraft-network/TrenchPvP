package net.peacefulcraft.trenchpvp.gamehandle;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;

public abstract class Announcer {
	
	private static final String trench_prefix = ChatColor.DARK_RED  + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";
		public static String getTrenchPrefix() { return trench_prefix; }
	
	public static void messagePlayer(Player p, String message) {
		p.sendMessage(trench_prefix + ChatColor.WHITE + " " + message);
	}
	
		
	public static void messageAll(String message) {
		messageTarget(message, null);
	}
	
	public static void messageRedTeam(String message) {
		messageTarget(message, TrenchTeams.RED);
	}
	
	public static void messageBlueTeam(String message) {
		messageTarget(message, TrenchTeams.BLUE);
	}
	
		private static void messageTarget(String message, TrenchTeams team) {
			
			TeamManager.ExecuteOnAllPlayers(
				(TrenchPlayer t) -> {
					if(team == null || t.getPlayerTeam() == team)
						t.getPlayer().sendMessage(trench_prefix + " " + message);
				}
			);
		}
}
