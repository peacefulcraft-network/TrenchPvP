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
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class TrenchSpy extends TrenchKit{
	
	public TrenchSpy() {
		kitType = TrenchKits.SPY;
	}
	
	/*
	 * Primary
	 * Spy - Hidden Blade - Speed and Invisibility
	 */
	@Override
	protected void equipPrimary(Player p) {

		//Creates sword
		ItemStack primary = new ItemStack(Material.GOLDEN_SWORD, 1);
		ItemMeta enchantMeta = primary.getItemMeta();
		enchantMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
		enchantMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		primary.setItemMeta(enchantMeta);
		//Set name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Hidden Blade");
		//Lore for item
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Sneak Fast!");
		pDesc.add("Ability Time: 8 Seconds");
		pDesc.add("Cooldown Time: 16 Seconds");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		p.getInventory().setItem(0, primary);
		
	}
	@Override
	protected void equipSecondary(Player p) {
		ItemStack secondary = new ItemStack(Material.CHORUS_FLOWER, 1);
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName("Speed Shot");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click Your Teammate to Give Them a Boost!");
		sDesc.add("Ability Time: 7 Seconds");
		sDesc.add("Cooldown Time: 16 Seconds");
		sMetaData.setLore(sDesc);
		
		secondary.setItemMeta(sMetaData);
		
		p.getInventory().setItem(1, secondary);
		
	}
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
			
		p.getInventory().setItem(7, bread);
		p.getInventory().setItem(8, (ItemStack) health);
		
	}
	@Override
	protected void equipArmor(Player p) {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.CHAINMAIL_HELMET,1);
		
		armor[2] = new ItemStack(Material.BAKED_POTATO, 1);
		
		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
		
		ItemMeta enchantMeta = armor[1].getItemMeta();
		enchantMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		armor[2].setItemMeta(enchantMeta);
		
		p.getInventory().setArmorContents(armor);
	}
}
