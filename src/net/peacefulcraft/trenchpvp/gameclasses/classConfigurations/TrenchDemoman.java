package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.BigBertha;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.GrenadeLauncher;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.LastWish;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchDemoman extends TrenchKit{

	public TrenchDemoman(TrenchPlayer t) {
		//Configure TrenchKit
		super(t, TrenchKits.DEMOMAN);

		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new GrenadeLauncher(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new BigBertha(this));
		getAbilityManager().registerAbility(TrenchAbilityType.ENTITY_DAMAGE_ENTITY, new LastWish(this));
	}

	/**
	 * Give main inventory items
	 * Primary - Grenade Launcher
	 * Secondary - Big Bertha's Embrace
	 * Util - Instant health && Bread
	 * Melee - Iron Axe
	 */
	@Override
	public void equipItems() {

		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();

		final String PRIMARY_NAME = "Grenade Launcher";
		final String UTIL_NAME = "Grenades";
		final String SECONDARY_NAME = "Big Bertha's Embrace";
		final String PASSIVE_NAME = "Last Wish";
		final String MELEE_NAME = "Headless Horseless Headmans Axe";
		
		/*
		 * Primary Weapon
		 */
		ItemStack primary = new ItemStack(Material.QUARTZ, 1);
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName(PRIMARY_NAME);

		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Left Click to Launch Grenades!");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);

		ItemStack primaryUtil = new ItemStack(Material.FIREWORK_ROCKET, 24);
		pMetaData = primaryUtil.getItemMeta();
		pMetaData.setDisplayName(UTIL_NAME);

		pDesc.set(0, "Ammunition for Grenade Launcher");
		pMetaData.setLore(pDesc);

		primaryUtil.setItemMeta(pMetaData);

		/**
		 * Secondary Weapon
		 */
		
		ItemStack secondary = new ItemStack(Material.TNT, 1);
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName(SECONDARY_NAME);

		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Left Click to Place Big Bertha's Embrace!");
		sDesc.add("Bomb Fuse Time: 6 Seconds");
		sDesc.add("Cooldown Time: 20 Seconds");
		sMetaData.setLore(sDesc);

		secondary.setItemMeta(sMetaData);
		
		/**
		 * Melee Weapon
		 */
		
		ItemStack melee = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);

		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("The Legendary Axe of The Headless Horseless Headman");
		meleeMeta.setLore(mDesc);
		meleeMeta.setUnbreakable(true);
		melee.setItemMeta(meleeMeta);
		
		/**
		 * Passive Ability
		 */
		ItemStack passive = new ItemStack(Material.MUSIC_DISC_STRAD, 1);
		ItemMeta passMeta = passive.getItemMeta();
		passMeta.setDisplayName(PASSIVE_NAME);
		
		ArrayList<String> passDesc = new ArrayList<String>();
		passDesc.add("Passive Ability: Triggers All Remaining Grenades on Death!");
		passMeta.setLore(passDesc);
		
		passive.setItemMeta(passMeta);	
		
		inv.setItem(0, melee);
		inv.setItem(1, primary);
		inv.setItem(2, secondary);
		inv.setItem(3, primaryUtil);
		inv.setItem(5, passive);
		
		addItemName(PRIMARY_NAME, 1);
		addItemName(UTIL_NAME, 2);
		addItemName(SECONDARY_NAME, 1);
		addItemName(MELEE_NAME, 2);
		addItemName(PASSIVE_NAME, 2);
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
		armor[2].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 2);

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		armor[1].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);

		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);

		ItemMeta metaH = armor[3].getItemMeta();
		metaH.setUnbreakable(true);
		armor[3].setItemMeta(metaH);

		ItemMeta metaC = armor[2].getItemMeta();
		metaC.setUnbreakable(true);
		armor[2].setItemMeta(metaC);

		ItemMeta metaL = armor[1].getItemMeta();
		metaL.setUnbreakable(true);
		armor[1].setItemMeta(metaL);

		ItemMeta metaB = armor[0].getItemMeta();
		metaB.setUnbreakable(true);
		armor[0].setItemMeta(metaB);

		this.getTrenchPlayer().getPlayer().getInventory().setArmorContents(armor);
	}
}
