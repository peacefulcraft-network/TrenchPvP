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

import net.peacefulcraft.trenchpvp.menu.GameMenu;

public class TrenchDemoman extends TrenchKit{

	public TrenchDemoman() {
		kitType = TrenchKits.DEMOMAN;
	}
	
	/*Demoman - Grenade Launcher - Launches grenades(FireWork Charges x 16) that bounce and explode on impact
	 * --Handled by GrenadeLauncher(class)
	 * 
	 *
	 */
	@Override
	protected void equipPrimary(Player p) {
		ItemStack melee = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName("Headless Horseless Headmans Axe");
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("The Legendary Axe of The Headless Horseless Headman");
		meleeMeta.setLore(mDesc);
		
		melee.setItemMeta(meleeMeta);
		
		ItemStack primary = new ItemStack(Material.QUARTZ, 1);
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Grenade Launcher");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Left Click to Launch Grenades!");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);
		
		ItemStack primaryUtil = new ItemStack(Material.FIREWORK_ROCKET, 16);
		pMetaData = primaryUtil.getItemMeta();
		pMetaData.setDisplayName("Grenades");

		pDesc.set(0, "Ammunition for Grenade Launcher");
		pMetaData.setLore(pDesc);
		
		primaryUtil.setItemMeta(pMetaData);
		
		p.getInventory().setItem(0, melee);
		p.getInventory().setItem(1, primary);
		p.getInventory().setItem(3, primaryUtil);
	}
	
	/*Demoman - Stick Bomb Launcher - Launches Sticky Bombs (Fire Charges) On land, turns into pressure plate that explodes when right click with launcher
	 *--Handled by StickyLauncher(class)
	 */
	@Override
	protected void equipSecondary(Player p) {
		ItemStack secondary = new ItemStack(Material.TNT, 1);
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName("Big Bertha's Embrace");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Left Click to Place Big Bertha's Embrace!");
		sDesc.add("Bomb Fuse Time: 6 Seconds");
		sDesc.add("Cooldown Time: 20 Seconds");
		sMetaData.setLore(sDesc);
		
		secondary.setItemMeta(sMetaData);
		
		p.getInventory().setItem(2, secondary);
	}
	
	@Override
	protected void equipGenerics(Player p) {
		
		//Bread for food
		ItemStack bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		ItemStack health = new ItemStack(Material.POTION, 1);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(healthMeta);
		p.getInventory().setItem(6, bread);
		p.getInventory().setItem(7, (ItemStack) health);
		
	}
	
	/*Leather Helment
	 * Leather Chestplate : Projectile Protection 1
	 * Leather Leggings
	 * Leather Boots
	 */
	@Override
	protected void equipArmor(Player p) {
		
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 2);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		armor[1].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		p.getInventory().setArmorContents(armor);
		
	}
	
	@Override
	protected void equipMenu(Player p) {
		ItemStack menu = new ItemStack(Material.EMERALD, 60);
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
