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

public class TrenchAdrenalineJunky extends TrenchKit
{
	public TrenchAdrenalineJunky() {
		kitType = TrenchKits.ADRENALINE_JUNKY;
	}
	@Override
	protected void equipPrimary(Player p)
	{
		ItemStack primary = new ItemStack(Material.IRON_SWORD, 1);
		
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName("Junkies Switch Blade");
		primary.setItemMeta(pMeta);
		
		ItemStack smallDose = new ItemStack(Material.GLASS_BOTTLE, 1);
		
		ItemMeta doseMeta = smallDose.getItemMeta();
		doseMeta.setDisplayName("Small Dose");
		
		ArrayList<String> doseDesc = new ArrayList<String>();
		doseDesc.add("Right Click to Take a Dose of Adrenaline!");
		doseDesc.add("Ability Time: 10 Seconds");
		doseDesc.add("Cooldown Time: 18 Seconds");
		doseMeta.setLore(doseDesc);
		smallDose.setItemMeta(doseMeta);
		
		p.getInventory().setItem(0, primary);
		p.getInventory().setItem(1, smallDose);
	}

	@Override
	protected void equipSecondary(Player p)
	{
		ItemStack secondary = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Launch Pad");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click On The Ground to Place a Launch Pad!");
		sDesc.add("Ability Time: 15 Seconds");
		sDesc.add("Cooldown Time: 30 Seconnds");
		
		sMeta.setLore(sDesc);
		secondary.setItemMeta(sMeta);
		
		p.getInventory().setItem(2, secondary);
	}

	@Override
	protected void equipGenerics(Player p)
	{
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

	@Override
	protected void equipArmor(Player p)
	{
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET, 1);
		armor[3].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE, 1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.IRON_BOOTS, 1);
		armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 2);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.LIME);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaL = (LeatherArmorMeta) armor[1].getItemMeta();
		metaL.setColor(Color.BLACK);
		armor[1].setItemMeta(metaL);
		p.getInventory().setArmorContents(armor);
	}

	@Override
	protected void equipMenu(Player p)
	{
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
