package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TrenchHeavy extends TrenchKit{
	
	
	public TrenchHeavy() {
		kitType = TrenchKits.HEAVY;
	}

	/*
	 * Primary
	 * Heavy - Dense Axe - Slows player and increases attack
	 */
	@Override
	protected void equipPrimary(Player p) {
		
		//Create Axe
		ItemStack primary = new ItemStack(Material.IRON_AXE, 1);
		
		//Set axe name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Dusk's Edge");
		//Set lore to axe
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Increase Attack!");
		pDesc.add("Ability Time: 4 Seconds");
		pDesc.add("Cooldwon Time: 15 Seconds");
		
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		p.getInventory().setItem(0, primary);
		
	}

	/*
	 * Secondary
	 * Heavy - Armadillo Shell - Slows, fatigues player increase defense
	 */
	@Override
	protected void equipSecondary(Player p) {
		
		ItemStack secondary = new ItemStack(Material.SHULKER_SHELL, 1);
		
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName("Absolute Defense");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click to Engage Defensive Shell!");
		sDesc.add("Ability Time: 7 Seconds");
		sDesc.add("Cooldown Time: 20 Seconds");
		sMetaData.setLore(sDesc);
		
		secondary.setItemMeta(sMetaData);
		
		p.getInventory().setItem(1, secondary);
		
	}

	/*
	 * Assorted Inventory
	 */
	@Override
	protected void equipGenerics(Player p) {
		
		//Food
		ItemStack bread = new ItemStack(Material.BREAD, 32);
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		ItemStack health = new ItemStack(Material.POTION, 2);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(healthMeta);
		
		p.getInventory().setItem(6, bread);
		p.getInventory().setItem(7, (ItemStack) health);
	}

	/*
	 * Armor Set:
	 * Iron Helmet
	 * Iron Chestplate : Projectile Protection 1
	 * Leather Leggings
	 * Leather Boots
	 */
	@Override
	protected void equipArmor(Player p) {
		
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET,1);
		
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE,1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		ItemMeta helmetMeta = armor[3].getItemMeta();
		helmetMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
		armor[3].setItemMeta(helmetMeta);
		
		ItemMeta enchantMeta = armor[2].getItemMeta();
		enchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
		armor[2].setItemMeta(enchantMeta);
		
		p.getInventory().setArmorContents(armor);
		
	}
	@Override
	protected void equipMenu(Player p) {
		ItemStack menu = new ItemStack(Material.EMERALD, 1);
		ItemMeta menuMeta = menu.getItemMeta();
		menuMeta.setDisplayName(ChatColor.AQUA + "Kit Menu");
		
		ArrayList<String> menuDesc = new ArrayList<String>();
		menuDesc.add("Right Click to Open Kit Menu!");
		menuDesc.add("Selection Cooldown: 1 Minute!");
		
		menuMeta.setLore(menuDesc);
		menu.setItemMeta(menuMeta);
		
		p.getInventory().setItem(8, menu);
	}

}
