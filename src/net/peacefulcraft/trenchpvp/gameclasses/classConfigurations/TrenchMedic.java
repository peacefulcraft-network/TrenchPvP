package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.DangerousHealthHose;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.MediGun;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchMedic extends TrenchKit{

	public TrenchMedic(TrenchPlayer t) {
		super(t, TrenchKits.MEDIC);

		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT_ENTITY, new MediGun(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new DangerousHealthHose(this));
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

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);

		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		ItemMeta metaH = armor[3].getItemMeta();
		metaH.setUnbreakable(true);
		((LeatherArmorMeta)metaH).setColor(Color.WHITE);
		armor[3].setItemMeta(metaH);

		ItemMeta metaC = armor[2].getItemMeta();
		metaC.setUnbreakable(true);
		((LeatherArmorMeta)metaC).setColor(Color.WHITE);
		armor[2].setItemMeta(metaC);

		ItemMeta metaL = armor[1].getItemMeta();
		metaL.setUnbreakable(true);
		((LeatherArmorMeta)metaL).setColor(Color.WHITE);
		armor[1].setItemMeta(metaL);

		ItemMeta metaB = armor[0].getItemMeta();
		metaB.setUnbreakable(true);
		((LeatherArmorMeta)metaB).setColor(Color.BLACK);
		armor[0].setItemMeta(metaB);
		
		t.getPlayer().getInventory().setArmorContents(armor);
	}

	@Override
	public void equipItems() {

		Inventory inv = t.getPlayer().getInventory();
		
		final String MELEE_NAME = "Scalpel";
		final String PRIMARY_NAME = "Medi Gun";
		final String RANGED_NAME = "Syringe Gun";
		final String SECONDARY_NAME = "Dangerous Health Hose";		
		
		addItemName(MELEE_NAME, 2);
		addItemName(PRIMARY_NAME, 1);
		addItemName(RANGED_NAME, 2);
		addItemName(SECONDARY_NAME, 1);
		
		ItemStack melee = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Extra Sharp, Extra Cut");
		meleeMeta.setLore(mDesc);
		
		meleeMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		meleeMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
		
		melee.setItemMeta(meleeMeta);
		
		inv.setItem(0, melee);		
		
		/*Medic - Medi Gun
		 *Heal players ever 1.5 seconds (MediGun.java (class))
		 */
		//Create Bow / give enchantments
		ItemStack primary = new ItemStack(Material.REDSTONE_BLOCK, 1);

		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName(PRIMARY_NAME);

		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click Your Teamates to Heal Them!");
		pDesc.add("Cooldown Time: 1.5 seconds");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);
		inv.setItem(2, primary);

		/*Bow:Syringe Gun w/ 32 arrows
		 *
		 */
		ItemStack ranged = new ItemStack(Material.BOW, 1);
		ItemMeta rMeta = ranged.getItemMeta();
		rMeta.setUnbreakable(true);
		rMeta.setDisplayName(RANGED_NAME);
		ranged.setItemMeta(rMeta);
		ItemStack rangedUtil = new ItemStack(Material.ARROW, 32);//give arrows for syringe gun

		inv.setItem(1, ranged);
		inv.setItem(4, rangedUtil);
		
		/*
		 * Secondary ability
		 */
		ItemStack secondary = new ItemStack(Material.BEACON);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName(SECONDARY_NAME);
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click To Pour Your Life Into Your Teammates!");
		sDesc.add("Cooldown Time: 30 Seconds");
		sMeta.setLore(sDesc);
		
		secondary.setItemMeta(sMeta);
		
		inv.setItem(3, secondary);
	}
}
