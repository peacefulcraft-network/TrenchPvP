package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.PyroGodBell;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.PyroGodBellWalk;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.ThunderStruck;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchElementalist extends TrenchKit
{

	public TrenchElementalist(TrenchPlayer t)
	{
		super(t, TrenchKits.ELEMENTALIST);
		
		//TODO: Register ability handlers
		PyroGodBellWalk walkEnforcer = new PyroGodBellWalk(this);
		getAbilityManager().registerAbility(TrenchAbilityType.ENTITY_DAMAGE_ENTITY, new ThunderStruck(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new PyroGodBell(this, walkEnforcer));
		getAbilityManager().registerAbility(TrenchAbilityType.ENTITY_DAMAGE_ENTITY, walkEnforcer);
	}

	@Override
	public void equipItems()
	{
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String MELEE_NAME = "Thunderstruck";
		final String PRIMARY_NAME = "Bell of The Pyro God";
		final String SECONDARY_NAME = "Terra Wall";
		final String PASSIVE_NAME = "Grassy Life Drain";
		
		addItemName(MELEE_NAME, 2);
		addItemName(PRIMARY_NAME, 1);
		addItemName(SECONDARY_NAME, 1);	
		addItemName(PASSIVE_NAME, 2);
		
		/*
		 * Melee Item
		 */
		ItemStack melee = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);
		
		meleeMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Forged From The Armpit Hairs of Zeus");
		mDesc.add("Has Chance to Summon A Bolt of Lightning");
		meleeMeta.setLore(mDesc);
		melee.setItemMeta(meleeMeta);
		
		//Primary Ability
		ItemStack primary = new ItemStack(Material.BELL, 1);
		
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName(PRIMARY_NAME);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Bring Out The Power of The Pyro God");
		pDesc.add("Summons Flames... So Many Flames...");
		pDesc.add("Ability Time: 20 Seconds");
		pDesc.add("Cooldown Time: 35 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		
		//Secondary Ability
		ItemStack secondary = new ItemStack(Material.EMERALD_ORE, 1);
		
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName(SECONDARY_NAME);
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click to Create Earthen Walls of Defense!");
		sDesc.add("Ability Time: 10 Seconds");
		sDesc.add("Cooldown Time: 25 Seconds");
		
		sMeta.setLore(sDesc);
		
		secondary.setItemMeta(sMeta);
		
		//Passive Ability Icon
		ItemStack passive = new ItemStack(Material.GRASS, 1);
		
		ItemMeta paMeta = passive.getItemMeta();
		paMeta.setDisplayName(PASSIVE_NAME);
		
		ArrayList<String> paDesc = new ArrayList<String>();
		paDesc.add("Passive: Regenerate Health While Standing On Grass!");
		paMeta.setLore(paDesc);
	
		passive.setItemMeta(paMeta);
		
		inv.setItem(0, melee);
		inv.setItem(1, primary);
		inv.setItem(2, secondary);
		inv.setItem(4, passive);
	}

	@Override
	public void equipArmor()
	{
		Player p = this.getTrenchPlayer().getPlayer();
		
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET, 1);
		
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE, 1);
		
		armor[1] = new ItemStack(Material.GOLDEN_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.IRON_BOOTS, 1);
		
		ItemMeta metaH = armor[3].getItemMeta();
		metaH.addEnchant(Enchantment.THORNS, 1, true);
		armor[3].setItemMeta(metaH);
		ItemMeta metaC = armor[2].getItemMeta();
		metaC.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
		armor[2].setItemMeta(metaC);
		ItemMeta metaB = armor[0].getItemMeta();
		metaB.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
		metaB.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
		armor[0].setItemMeta(metaB);
		
		p.getInventory().setArmorContents(armor);
	}
	

}
