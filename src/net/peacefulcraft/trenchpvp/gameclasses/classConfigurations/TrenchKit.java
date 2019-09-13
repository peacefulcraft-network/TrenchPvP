package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public abstract class TrenchKit implements TrenchKitInventory, TrenchClassConfiguration{
	
	protected TrenchPlayer t;
		public TrenchPlayer getTrenchPlayer() { return t; }
	
	private TrenchKits kitType;
		public TrenchKits getKitType() {return kitType;}
	
	private TrenchAbilityManager abilities;
		public TrenchAbilityManager getAbilityManager() { return abilities; }
		
	private HashMap<String, Integer> itemNames;
		public Set<String> getItemNamesSet() { return itemNames.keySet(); }
		/*
		 * int encodes side of ability click.
		 * 0 left, 1 right, 2 neither
		 */
		public void addItemName(String name, int side) { 
			itemNames.put(name, side);
		}
		public HashMap<String, Integer> getItemNames() { return itemNames; }
		
	public TrenchKit(TrenchPlayer t, TrenchKits kitType) {
		this.t = t;
		this.kitType = kitType;
		this.abilities = new TrenchAbilityManager();
		this.itemNames = new HashMap<String, Integer>();
	}
	
	/**
	 * Overriden by children if needed
	 */
	@Override
	public void initConfig() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Overriden by children if needed
	 */
	@Override
	public void dinitConfig() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Give the player all their items
	 */
	public void equip() {
		equipItems();
		equipArmor();
		equipIUniversalItems();
		t.getPlayer().updateInventory();
	}
	
	/**
	 * Give player items that are in all kits
	 */
	private void equipIUniversalItems() {
		
		Inventory inv = t.getPlayer().getInventory();
		
		inv.setItem(6, item_food());
		inv.setItem(7, item_medkit());
		inv.setItem(8, item_classSelectionMenu());
	}
		
		/**
		 * @return Steak
		 */
		private ItemStack item_food() {
			return new ItemStack(Material.COOKED_BEEF, 32);
		}
	
		/**
		 * @return Instant health potions
		 */
		private ItemStack item_medkit() {
			ItemStack health = new ItemStack(Material.POTION, 2);
			ItemMeta healthMeta = health.getItemMeta();
			healthMeta.setDisplayName("Medkit");
			PotionMeta pHealthMeta = (PotionMeta) healthMeta;
			
			PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
			pHealthMeta.addCustomEffect(instantHealth, true);
			health.setItemMeta(healthMeta);
			
			return health;
		}
	
		/**
		 * @return Emerald with meta for class selection menu
		 */
		private ItemStack item_classSelectionMenu() {
			ItemStack menu = new ItemStack(Material.EMERALD, 1);
			ItemMeta menuMeta = menu.getItemMeta();
			menuMeta.setDisplayName(ChatColor.AQUA + "Kit Menu");
			
			ArrayList<String> menuDesc = new ArrayList<String>();
			menuDesc.add("Right Click to Open Kit Menu!");
			menuDesc.add("Selection Cooldown: 1 Minute!");
			menuMeta.setLore(menuDesc);
			menu.setItemMeta(menuMeta);
			
			return menu;
		}
}
