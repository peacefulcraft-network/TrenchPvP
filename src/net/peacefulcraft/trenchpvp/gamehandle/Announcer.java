package net.peacefulcraft.trenchpvp.gamehandle;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;

public abstract class Announcer {
	
	private static final String trench_prefix = ChatColor.DARK_RED  + "[" + ChatColor.RED + "Trench" + ChatColor.DARK_RED + "]";
		public static String getTrenchPrefix() { return trench_prefix; }
	
	public static void messagePlayer(Player p, String message) {
		p.sendMessage(trench_prefix + ChatColor.WHITE + " " + message);
	}
	
	public static void messagePlayerActionBar(Player p, String message) {
		(new ActionBarMessage(1, p, message)).runTaskTimer(TrenchPvP.getPluginInstance(), 0L, 20L);
	}
	
	private static class ActionBarMessage extends BukkitRunnable {
		private int seconds;
		private Player p;
		private String message;
		
		public ActionBarMessage(int seconds, Player p, String message) {
			this.seconds = seconds;
			this.p = p;
			this.message = message;
		}
		
		@Override
		public void run() {
			BaseComponent base = new TextComponent(message);
			base.setColor(ChatColor.RED);
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, base);
			
			seconds--;
			if(seconds<1) {
				this.cancel();
			}
		}
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
		TrenchPvP.getTrenchManager().getCurrentArena().executeOnAllPlayers(
			(TrenchPlayer t) -> {
				if(team == null || t.getPlayerTeam() == team)
					t.getPlayer().sendMessage(trench_prefix + " " + message);
			}
		);
	}

}
