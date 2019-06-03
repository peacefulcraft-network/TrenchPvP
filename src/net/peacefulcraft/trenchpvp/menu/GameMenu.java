package net.peacefulcraft.trenchpvp.menu;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameMenu
{
	private Inventory inv;
	
	public GameMenu() {
		inv = Bukkit.createInventory(null, 9, "Game Menu");
		
	}
	public void initializeItems() {
		inv.setItem(0, createGuiItem("Test Item", new ArrayList<String>(Arrays.asList("This is a Test")), Material.GOLD_BLOCK));
		inv.setItem(8, createGuiItem("Test Item", new ArrayList<String>(Arrays.asList("This is a Test")), Material.DIAMOND_BLOCK));
	}
	public ItemStack createGuiItem(String name, ArrayList<String> desc, Material mat) {
		ItemStack i = new ItemStack(mat, 1);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName(name);
        iMeta.setLore(desc);
        i.setItemMeta(iMeta);
        return i;
	}
	
	public void openInventory(Player p) {
		p.openInventory(inv);
	}
}
