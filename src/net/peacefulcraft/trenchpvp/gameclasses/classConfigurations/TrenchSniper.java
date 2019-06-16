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

		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.BLACK);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaC = (LeatherArmorMeta) armor[2].getItemMeta();
		metaC.setColor(Color.GREEN);
		armor[3].setItemMeta(metaC);
		this.getTrenchPlayer().getPlayer().getInventory().setArmorContents(armor);

	}

	@Override
	public void equipItems() {

		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();

		/**
		 * Sniper Bow & Arrows
		 */
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
		ItemStack primaryUtil = new ItemStack(Material.ARROW, 64);
		ItemMeta pUMetaData = primaryUtil.getItemMeta();
		pUMetaData.setDisplayName("Bullets");
		ArrayList<String> pUDesc = new ArrayList<String>();
		pUDesc.add("Right Click to Switch to Poison Rounds!");
		pUDesc.add("Ability Time: 10 Seconds");
		pUDesc.add("Cooldown Time: 20 Seconds");
		pUMetaData.setLore(pUDesc);

		primaryUtil.setItemMeta(pUMetaData);

		ItemStack secondUtil = new ItemStack(Material.PISTON, 1);
		ItemMeta sUMeta = secondUtil.getItemMeta();
		sUMeta.setDisplayName("Power Shot");
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
		sMeta.setDisplayName("Kukri");
		secondary.setItemMeta(sMeta);
		secondary.addEnchantment(Enchantment.SWEEPING_EDGE, 1);

		inv.setItem(1, secondary);

	}

}
