package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.PoisonRound;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.PowerShot;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchSniper extends TrenchKit{

	public TrenchSniper(TrenchPlayer t) {
		super(t, TrenchKits.SNIPER);

		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new PowerShot(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new PoisonRound(this));
	}

	/*Leather Helment
	 * Leather Chestplate : Projectile Protection 1
	 * Leather Leggings
	 * Leather Boots
	 */
	@Override
	public void equipArmor() {

		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);

		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);

		armor[0] = new ItemStack(Material.IRON_BOOTS, 1);

		ItemMeta metaH = armor[3].getItemMeta();
		metaH.setUnbreakable(true);
		((LeatherArmorMeta)metaH).setColor(Color.BLACK);
		armor[3].setItemMeta(metaH);

		ItemMeta metaC = armor[2].getItemMeta();
		metaC.setUnbreakable(true);
		((LeatherArmorMeta)metaC).setColor(Color.GREEN);
		armor[2].setItemMeta(metaC);

		ItemMeta metaL = armor[1].getItemMeta();
		metaL.setUnbreakable(true);
		armor[1].setItemMeta(metaL);

		ItemMeta metaB = armor[0].getItemMeta();
		metaB.setUnbreakable(true);
		armor[0].setItemMeta(metaB);

		this.getTrenchPlayer().getPlayer().getInventory().setArmorContents(armor);

	}

	@Override
	public void equipItems() {

		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String MELEE_NAME = "Kukiri";
		final String PRIMARY_NAME = "Component Rifle Mk.II";
		final String FIRST_UTIL_NAME = "Bullets";
		final String SECOND_UTIL_NAME = "Power Shot";
		final String PASSIVE_NAME = "Phantom Arrow";
		
		addItemName(MELEE_NAME, 2);
		addItemName(PRIMARY_NAME, 2);
		addItemName(FIRST_UTIL_NAME, 1);
		addItemName(SECOND_UTIL_NAME, 1);
		addItemName(PASSIVE_NAME, 2);
		
		/**
		 * Sniper Bow & Arrows
		 */
		//Create Bow / give enchantments
		ItemStack primary = new ItemStack(Material.BOW, 1);
		primary.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		primary.addEnchantment(Enchantment.ARROW_DAMAGE, 1);

		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setUnbreakable(true);
		pMetaData.setDisplayName(PRIMARY_NAME);
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Equipped With Upgradable Components!");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);

		//Create arrows for sniper riffle
		ItemStack primaryUtil = new ItemStack(Material.ARROW, 56);
		ItemMeta pUMetaData = primaryUtil.getItemMeta();
		pUMetaData.setDisplayName(FIRST_UTIL_NAME);
		ArrayList<String> pUDesc = new ArrayList<String>();
		pUDesc.add("Right Click to Switch to Poison Rounds!");
		pUDesc.add("Ability Time: 10 Seconds");
		pUDesc.add("Cooldown Time: 20 Seconds");
		pUMetaData.setLore(pUDesc);

		primaryUtil.setItemMeta(pUMetaData);

		ItemStack secondUtil = new ItemStack(Material.PISTON, 1);
		ItemMeta sUMeta = secondUtil.getItemMeta();
		sUMeta.setDisplayName(SECOND_UTIL_NAME);
		ArrayList<String> sUDesc = new ArrayList<String>();
		sUDesc.add("Right Click to Boost Sniper Rifle Mk.II Damage!");
		sUDesc.add("Ability Time: 10 Seconds");
		sUDesc.add("Cooldown Time: 20 Seconds");
		sUMeta.setLore(sUDesc);

		secondUtil.setItemMeta(sUMeta);

		inv.setItem(0, primary);
		inv.setItem(2, primaryUtil);
		inv.setItem(3, secondUtil);

		/**
		 * Melee Sword
		 */
		ItemStack secondary = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setUnbreakable(true);
		sMeta.setDisplayName(MELEE_NAME);
		secondary.setItemMeta(sMeta);
		secondary.addEnchantment(Enchantment.SWEEPING_EDGE, 1);
		secondary.addEnchantment(Enchantment.DURABILITY, 3);

		inv.setItem(1, secondary);
		
		/*
		 * Passive
		 */
		ItemStack passive = new ItemStack(Material.END_ROD, 1);
		ItemMeta passMeta = passive.getItemMeta();
		passMeta.setDisplayName(PASSIVE_NAME);
		
		ArrayList<String> passDesc = new ArrayList<String>();
		passDesc.add("Passive Ability: 40% Chance For Arrow");
		passDesc.add("to Return to You After Being Shot!");
		passMeta.setLore(passDesc);
		
		passive.setItemMeta(passMeta);
		
		inv.setItem(5, passive);
		
	}

}
