package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.HomeInvasion;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.SpanishLesson;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class TrenchDuolingoBird extends TrenchKit
{

	public TrenchDuolingoBird(TrenchPlayer t)
	{
		super(t, TrenchKits.DUOLINGO_BIRD);
		
		//Register ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new HomeInvasion(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new SpanishLesson(this));
	}

	@Override
	public void equipItems()
	{
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String MELEE_NAME = "French Baguette";
		final String RANGED_NAME = "German Artillery";
		final String PRIMARY_NAME = "Home Invasion";
		final String SECONDARY_NAME = "Spanish Lesson";
		
		addItemName(MELEE_NAME, 2);
		addItemName(RANGED_NAME, 2);
		addItemName(PRIMARY_NAME, 1);
		addItemName(SECONDARY_NAME, 1);
		
		//Melee weapon
		ItemStack melee = new ItemStack(Material.IRON_SWORD, 1);
		
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Good ole' French Lesson");
		mDesc.add("Each Kill Has A Chance To Give Speed!");
		meleeMeta.setLore(mDesc);
		
		meleeMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meleeMeta.addEnchant(Enchantment.DURABILITY, 4, true);
		melee.setItemMeta(meleeMeta);
		
		//Ranged weapon
		ItemStack ranged = new ItemStack(Material.BOW, 1);
		
		ItemMeta rangedMeta = ranged.getItemMeta();
		rangedMeta.setDisplayName(RANGED_NAME);
		
		ArrayList<String> rDesc = new ArrayList<String>();
		rDesc.add("A Completed German Lesson");
		rDesc.add("Taught You The Wonders Of Artillery!");
		rDesc.add("No Longer Do You Worry About Arrow Count!");
		rangedMeta.setLore(rDesc);
		
		rangedMeta.addEnchant(Enchantment.DURABILITY, 4, true);
		rangedMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
		rangedMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		ranged.setItemMeta(rangedMeta);
		
		//Primary ability
		ItemStack primary = new ItemStack(Material.FEATHER, 1);
		
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName(PRIMARY_NAME);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click To Invade The Homes Of Nearby Enemies!");
		pDesc.add("Teleports You For 2 Seconds To All Nearby Enemies!");
		pDesc.add("Cooldown Time: 20 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		
		//Secondary ability
		ItemStack secondary = new ItemStack(Material.BOOK, 1);
		
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName(SECONDARY_NAME);
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click To Confuse Nearby Enemies With Spanish!");
		sDesc.add("Ability Time: 10 Seconds");
		sDesc.add("Cooldown Time: 25 Seconds");
		sMeta.setLore(sDesc);
		
		secondary.setItemMeta(sMeta);
		
		inv.setItem(0, melee);
		inv.setItem(1, ranged);
		inv.setItem(2, primary);
		inv.setItem(3, secondary);
		inv.setItem(6, new ItemStack(Material.ARROW, 1));
	}

	@Override
	public void equipArmor()
	{
		Player p = this.getTrenchPlayer().getPlayer();
		
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.GOLDEN_HELMET, 1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		
		armor[1] = new ItemStack(Material.GOLDEN_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[2].getItemMeta();
		metaH.setColor(Color.GREEN);
		armor[2].setItemMeta(metaH);
		LeatherArmorMeta metaB = (LeatherArmorMeta) armor[0].getItemMeta();
		metaB.setColor(Color.GREEN);
		armor[0].setItemMeta(metaB);
		
		ItemMeta MetaH = armor[3].getItemMeta();
		MetaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		MetaH.addEnchant(Enchantment.DURABILITY, 5, true);
		armor[3].setItemMeta(MetaH);
		ItemMeta MetaC = armor[2].getItemMeta();
		MetaC.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
		MetaC.addEnchant(Enchantment.DURABILITY, 5, true);
		armor[2].setItemMeta(MetaC);
		ItemMeta MetaL = armor[1].getItemMeta();
		MetaL.addEnchant(Enchantment.DURABILITY, 5, true);
		armor[1].setItemMeta(MetaL);
		ItemMeta MetaB = armor[0].getItemMeta();
		MetaB.addEnchant(Enchantment.PROTECTION_FALL, 2, true);
		MetaB.addEnchant(Enchantment.DURABILITY, 5, true);
		armor[0].setItemMeta(MetaB);
		
		p.getInventory().setArmorContents(armor);
	}
}
