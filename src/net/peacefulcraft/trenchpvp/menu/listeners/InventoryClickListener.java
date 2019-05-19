package net.peacefulcraft.trenchpvp.menu.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class InventoryClickListener implements Listener
{
	@EventHandler
	private void onInventoryClick(InventoryClickEvent e) {
		String invName = e.getView().getTitle();
		if(!invName.equals("Game Menu")) {
			return;
		}
		if(e.getClick().equals(ClickType.NUMBER_KEY)) {
			e.setCancelled(true);
		}
		e.setCancelled(true);
		
		Player p = (Player) e.getWhoClicked();
		ItemStack clickedItem = e.getCurrentItem();
		
		if(clickedItem == null || clickedItem.getType().equals(Material.AIR)) return;
		
		
	}
}
