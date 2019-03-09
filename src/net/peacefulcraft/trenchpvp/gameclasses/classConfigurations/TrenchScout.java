package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;
import java.util.ArrayList;

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

public class TrenchScout extends TrenchKit {
	public TrenchScout() {
		kitType = TrenchKits.SCOUT;
	}
	
	@Override
	protected void equipPrimary(Player p) {
		ItemStack primary = new ItemStack(Material.STONE_SWORD, 1);
		
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Slim Slicer");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Hold Blade for Increased Attack Speed!");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		p.getInventory().setItem(0, primary);		
	}

	@Override
	protected void equipSecondary(Player p) {
		ItemStack primary = new ItemStack(Material.BOW, 1);
		primary.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Sling Shot");
		primary.setItemMeta(pMetaData);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Tag Your Foes to Help Your Team!");
		pMetaData.setLore(pDesc);
		
		ItemStack pUtil = new ItemStack(Material.SPECTRAL_ARROW, 32);
		ItemMeta pUMetaData = pUtil.getItemMeta();
		pUMetaData.setDisplayName("Tag Ammo");
		pUtil.setItemMeta(pUMetaData);
		
		p.getInventory().setItem(1, primary);
		p.getInventory().setItem(2, pUtil);
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
		
		p.getInventory().setItem(7, bread);
		p.getInventory().setItem(8, (ItemStack) health);
		
	}

	@Override
	protected void equipArmor(Player p) {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.BLACK);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaC = (LeatherArmorMeta) armor[2].getItemMeta();
		metaC.setColor(Color.RED);
		armor[2].setItemMeta(metaC);
		LeatherArmorMeta metaL = (LeatherArmorMeta) armor[1].getItemMeta();
		metaL.setColor(Color.BLUE);
		armor[1].setItemMeta(metaL);
		LeatherArmorMeta metaS = (LeatherArmorMeta) armor[0].getItemMeta();
		metaS.setColor(Color.BLACK);
		armor[0].setItemMeta(metaS);
		
		p.getInventory().setArmorContents(armor);
	}
}
