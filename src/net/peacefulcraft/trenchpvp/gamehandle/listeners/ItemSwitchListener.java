
package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class ItemSwitchListener implements Listener
{	
	private final String leftPrefix = ChatColor.DARK_RED + "[" + ChatColor.DARK_BLUE + "Left Click" + ChatColor.DARK_RED + "]: ";
		private String getLeftPrefix() {return leftPrefix;}
	private final String rightPrefix = ChatColor.DARK_RED + " :[" + ChatColor.DARK_BLUE + "Right Click" + ChatColor.DARK_RED + "]";
		private String getRightPrefix() {return rightPrefix;}
		
	@EventHandler
	public void switchEvent(PlayerItemHeldEvent e) {
		
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
		if(t == null) {return;}
		
		TrenchKit k = t.getKit();
		Player p = t.getPlayer();
		
		if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR) || p.getInventory().getItemInMainHand().getType().equals(null)) {
			return;
		}

		ItemStack item = p.getInventory().getItem(e.getNewSlot());
		if(item == null) { return; }
		if(k.getItemNamesSet().contains(item.getItemMeta().getDisplayName())) {
			Announcer.messagePlayerActionBar(p, message(k, item.getItemMeta().getDisplayName()));
		}
	}
	
	public String message(TrenchKit k, String name) {
		if(k.getItemNames().get(name) == 0) {
			return getLeftPrefix() + ChatColor.AQUA + name + ChatColor.WHITE + "  |  " + ChatColor.AQUA + "None" + getRightPrefix();
		} else if(k.getItemNames().get(name) == 1) {
			return getLeftPrefix() + ChatColor.AQUA + "None" + ChatColor.WHITE + "  |  " + ChatColor.AQUA + name + getRightPrefix();
		} else {
			return getLeftPrefix() + ChatColor.AQUA + "None" + ChatColor.WHITE + "  |  " + ChatColor.AQUA + "None" + getRightPrefix();
		}
	}
}
