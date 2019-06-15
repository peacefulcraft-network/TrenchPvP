package net.peacefulcraft.trenchpvp.menu.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.gamehandle.GameManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.menu.GameMenu;
import net.peacefulcraft.trenchpvp.menu.GameMenu.Row;
import net.peacefulcraft.trenchpvp.menu.GameMenu.onClick;

public class PurchasedMenu
{
	private enum PurchasedKits {
		ADRENALINE_JUNKIE;
	}
	private GameMenu menu = new GameMenu("Purchased Menu", 3, new onClick() {
	    @Override
	    public boolean click(Player p, GameMenu menu, Row row, int slot, ItemStack item) {
	        if(row.getRow() == 0 || row.getRow() == 2){
	            inventoryClick(p, item);
	        }
	        return true;
	    }
	});
	
	public PurchasedMenu() {
		menu.addButton(menu.getRow(0), 0, new ItemStack(Material.GLASS_BOTTLE), "Adrenaline Junkie", "Click to Equip The Adrenaline Junkie Class!");
		
		menu.addButton(menu.getRow(0), 8, new ItemStack(Material.RED_STAINED_GLASS_PANE), "Quit", "Click to Leave Trench!");
		menu.addButton(menu.getRow(2), 8, new ItemStack(Material.BLUE_STAINED_GLASS_PANE), "Regular Classes", "Click to Return to Regular Class Selection!");
	}
	
	private void inventoryClick(Player p, ItemStack item) {
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		String itemText = item.getItemMeta().getDisplayName().toUpperCase();
		if(itemText.equals("QUIT")) {
			GameManager.quitPlayer(p);
			//TODO: Remove from kitmenu cooldown
		}
		if(itemText.equals("REGULAR CLASSES")) {
			KitMenu kitMenu = new KitMenu();
			menu.close(p);
			kitMenu.menuOpen(p);
		}
	}
	
	public void menuOpen(Player p) {
		menu.open(p);
	}
}
