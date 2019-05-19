package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TrenchSniper extends TrenchKit{
	
	public TrenchSniper() {
		kitType = TrenchKits.SNIPER;
	}
	
	/*Sniper - punch 2 power 1
	 *32 arrows
	 */
	@Override
	protected void equipPrimary(Player p) {

		//Create Bow / give enchantments
		ItemStack primary = new ItemStack(Material.BOW, 1);
		primary.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		primary.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		
		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Component Rifle Mk.II");
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Equipped With Upgradable Components!");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		//Create arrows for sniper riffle
		ItemStack primaryUtil = new ItemStack(Material.ARROW, 32);
		ItemMeta pUMetaData = primaryUtil.getItemMeta();
		pUMetaData.setDisplayName("Bullets");
		ArrayList<String> pUDesc = new ArrayList<String>();
		pUDesc.add("Right Click to Switch to Poison Rounds!");
		pUDesc.add("Ability Time: 5 Seconds");
		pUDesc.add("Cooldown Time: 12 Seconds");
		pUMetaData.setLore(pUDesc);
		
		primaryUtil.setItemMeta(pUMetaData);
		
		ItemStack secondUtil = new ItemStack(Material.PISTON, 1);
		ItemMeta sUMeta = secondUtil.getItemMeta();
		sUMeta.setDisplayName("Power Shot");
		ArrayList<String> sUDesc = new ArrayList<String>();
		sUDesc.add("Right Click to Boost Sniper Rifle Mk.II Damage!");
		sUDesc.add("Ability Time: 5 Seconds");
		sUDesc.add("Cooldown Time: 15 Seconds");
		sUMeta.setLore(sUDesc);
		
		secondUtil.setItemMeta(sUMeta);
		
		p.getInventory().setItem(0, primary);
		p.getInventory().setItem(2, primaryUtil);
		p.getInventory().setItem(3, secondUtil);
		
	}

	/*Stone Sword "Kukri" 
	 * 
	 */
	@Override
	protected void equipSecondary(Player p) {

		ItemStack secondary = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Kukri");
		secondary.setItemMeta(sMeta);
		secondary.addEnchantment(Enchantment.SWEEPING_EDGE, 1);
		
		p.getInventory().setItem(1, secondary);
		
	}

	@Override
	protected void equipGenerics(Player p) {
		
		ItemStack bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		ItemStack health = new ItemStack(Material.POTION, 2);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(pHealthMeta);
		
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
		armor[2].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.IRON_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.BLACK);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaC = (LeatherArmorMeta) armor[2].getItemMeta();
		metaC.setColor(Color.GREEN);
		armor[3].setItemMeta(metaC);
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
