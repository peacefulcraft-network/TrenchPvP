package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchSoldier extends TrenchKit{

	public TrenchSoldier() {
		kitType = TrenchKits.SOLDIER;
	}

	@Override
	protected void equipPrimary(Player p) {
		ItemStack primary = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta pMetaData = primary.getItemMeta();
		primary.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		pMetaData.setDisplayName("Deep Cut");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Activate Deep Cut!");
		pDesc.add("Ability Time: 10 Seconds");
		pDesc.add("Cooldown Time: 25 Seconds");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		p.getInventory().setItem(0, primary);		
	}

	@Override
	protected void equipSecondary(Player p) {
		ItemStack secondary = new ItemStack(Material.REDSTONE, 1);
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName("Onslaught");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Each Kill Adds the Blood of Your Enemies to Your Strength");
		sMetaData.setLore(sDesc);
		
		secondary.setItemMeta(sMetaData);
		
		p.getInventory().setItem(1, secondary);
	}

	@Override
	protected void equipGenerics(Player p) {
		//Bread for food
		ItemStack bread = new ItemStack(Material.BREAD, 32);
				
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		ItemStack health = new ItemStack(Material.POTION, 2);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
				
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
				
		health.setItemMeta(healthMeta);
		p.getInventory().setItem(7, bread);
		p.getInventory().setItem(8, (ItemStack) health);
	}

	@Override
	protected void equipArmor(Player p) {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.CHAINMAIL_HELMET, 1);
		
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE, 1);
		armor[2].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.IRON_BOOTS, 1);
		armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 1);
		p.getInventory().setArmorContents(armor);
	}

}
