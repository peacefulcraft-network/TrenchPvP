package net.peacefulcraft.trenchpvp.gamehande;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public abstract class Announcer {
	
	private static final String trench_prefix = ChatColor.DARK_RED  + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";
		public static String getTrenchPrefix() { return trench_prefix; }
	
	public static void messagePlayer(Player p, String message) {
		p.sendMessage(trench_prefix + message);
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
			ArrayList<TrenchPlayer> ps = TeamManager.getPlayers();
			for(TrenchPlayer p : ps) {
				if(team == null || p.getPlayerTeam() == team) {
					p.getPlayer().sendMessage(message);
				}
			}
		}
	
	
	
}
