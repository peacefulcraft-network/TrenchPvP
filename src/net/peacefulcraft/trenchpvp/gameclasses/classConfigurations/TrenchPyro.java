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

public class TrenchPyro extends TrenchKit{
	public TrenchPyro() {
		kitType = TrenchKits.PYRO;
	}
	
	@Override
	protected void equipPrimary(Player p) {
		ItemStack primary = new ItemStack(Material.GOLDEN_AXE);
		
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Nether's Bite");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Just a Sharp Axe.");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		ItemStack pUtil = new ItemStack(Material.BLAZE_ROD);
		ItemMeta pUMeta = pUtil.getItemMeta();
		pUMeta.setDisplayName("Flamethrower");
		
		ArrayList<String> pUDesc = new ArrayList<String>();
		pUDesc.add("Right Click to Shoot Flames!");
		pUMeta.setLore(pUDesc);
		
		pUtil.setItemMeta(pUMeta);
		
		p.getInventory().setItem(0, primary);
		p.getInventory().setItem(1, pUtil);
		
	}

	@Override
	protected void equipSecondary(Player p) {
		ItemStack secondary = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Inferno Trap Detonator");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click to Detonate Inferno Traps!");
		sMeta.setLore(sDesc);
		
		secondary.setItemMeta(sMeta);
		
		ItemStack trapAmmo = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 5);
		ItemMeta tAMeta = trapAmmo.getItemMeta();
		tAMeta.setDisplayName("Inferno Trap");
		
		ArrayList<String> aDesc = new ArrayList<String>();
		aDesc.add("Can Only Place 5 at a Time! Detonate to Get More!");
		tAMeta.setLore(aDesc);
		
		trapAmmo.setItemMeta(tAMeta);
		
		p.getInventory().setItem(2, secondary);
		p.getInventory().setItem(3, trapAmmo);
		
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

	@Override
	protected void equipArmor(Player p) {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET,1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_FIRE, 2);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		armor[1].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
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
