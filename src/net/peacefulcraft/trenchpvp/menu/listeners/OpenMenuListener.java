package net.peacefulcraft.trenchpvp.menu.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.menu.GameMenu;

public class OpenMenuListener implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private final int COOLDOWN_TIME = 60;
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.EMERALD)) return;
		
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Kit Menu"))) return;
		p.sendMessage("2");
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		p.sendMessage("3");
		if(cooldown.containsKey(p.getUniqueId())) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/100) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				
				menuOpen(p);
				
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Can't open menu for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			
			menuOpen(p);
			
		}
	}
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	private void menuOpen(Player p) {
		p.sendMessage("1");
		GameMenu menu = new GameMenu();
		menu.initializeItems();
		menu.openInventory(p);
	}
}
