package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbility;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchKitClickAbilityExecutor;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchKitInventory;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public abstract class TrenchKit implements TrenchKitInventory, TrenchKitClickAbilityExecutor{
	
	protected TrenchPlayer t;
		public TrenchPlayer getTrenchPlayer() { return t; }
	
	private TrenchKits kitType;
		public TrenchKits getKitType() {return kitType;}
	
	private List<TrenchAbility> abilities = new LinkedList<TrenchAbility>();
	
	public TrenchKit(TrenchPlayer t, TrenchKits kitType) {
		this.t = t;
		this.kitType = kitType;
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
			ItemStack menu = new ItemStack(Material.EMERALD, 60);
			ItemMeta menuMeta = menu.getItemMeta();
			menuMeta.setDisplayName(ChatColor.AQUA + "Kit Menu");
			
			ArrayList<String> menuDesc = new ArrayList<String>();
			menuDesc.add("Right Click to Open Kit Menu!");
			menuDesc.add("Selection Cooldown: 1 Minute!");
			menuMeta.setLore(menuDesc);
			menu.setItemMeta(menuMeta);
			
			return menu;
		}
	
	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchKitClickAbilityExecutor#registerAbility(net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbility)
	 */
	public void registerAbility(TrenchAbility ability) throws IllegalStateException{
		
		for(TrenchAbility ta : abilities) {
			
			if(ta.getClass().toString().equals(ability.getClass().toString())) {
				throw new IllegalStateException("TrenchAbility child " + ability.getClass() + " is already registered with this executor");	
			}
			
			abilities.add(ability);			
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchKitClickAbilityExecutor#unregisterAbility(java.lang.String)
	 */
	public void unregisterAbility(String abilityClassName) throws RuntimeException{
		
		for(TrenchAbility ta : abilities) {
			
			if(ta.getClass().toString().equals(abilityClassName)) {
				abilities.remove(ta);
				return;
			}
			
		}
		
		throw new RuntimeException("Ability " + abilityClassName + " was not registered with this ability executor");
		
	}
	
	/* (non-Javadoc)
	 * @see net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchKitClickAbilityExecutor#executeClickAbilities()
	 */
	public void executeClickAbilities() {
		
		for(TrenchAbility ta : abilities) {
			
			ta.executeAbility();
			
		}
		
	}
	
}
