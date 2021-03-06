package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.BuildTimer;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.LonelyMansMic;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchJuniorCommunityManager extends TrenchKit
{
	public TrenchJuniorCommunityManager(TrenchPlayer t)
	{
		super(t, TrenchKits.JUNIOR_COMMUNITY_MANAGER);
		
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new LonelyMansMic(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new BuildTimer(this));
	}
	
	@Override
	public void equipItems()
	{
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String MELEE_NAME = "Bitch Stick";
		final String PRIMARY_NAME = "Lonely Mans Mic";
		final String SECONDARY_NAME = "Build Timer";
		
		addItemName(MELEE_NAME, 2);
		addItemName(PRIMARY_NAME, 1);
		addItemName(SECONDARY_NAME, 1);	
		
		//Melee
		ItemStack melee = new ItemStack(Material.STICK, 1);
		ItemMeta meleeMeta = melee.getItemMeta();
		
		meleeMeta.setDisplayName(MELEE_NAME);
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Literally Just A Stick");
		meleeMeta.setLore(mDesc);
		
		melee.setItemMeta(meleeMeta);
		
		//Primary Ability
		ItemStack primary = new ItemStack(Material.LEVER, 1);
		ItemMeta pMeta = primary.getItemMeta();
		
		pMeta.setDisplayName(PRIMARY_NAME);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click To Deep Throat Your Mic And Embrace Your Lonlieness");
		pDesc.add("Cooldown Time: 15 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		
		//Secondary Ability
		ItemStack secondary = new ItemStack(Material.CLOCK, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		
		sMeta.setDisplayName(SECONDARY_NAME);
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click To Make All Nearby Players Experience How Long It Takes To Complete A Build!");
		sDesc.add("Ability Time: 20 Seconds");
		sDesc.add("Cooldown Time: 60 Seconds");
		sMeta.setLore(sDesc);
		
		secondary.setItemMeta(sMeta);
		
		inv.setItem(0, melee);
		inv.setItem(1, primary);
		inv.setItem(2, secondary);
	}
	@Override
	public void equipArmor()
	{
		Player p = this.getTrenchPlayer().getPlayer();
		
		ItemStack[] armor = new ItemStack[4];
		
		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
		ItemMeta leg = armor[1].getItemMeta();
		leg.addEnchant(Enchantment.THORNS, 1, true);
		leg.setUnbreakable(true);
		armor[1].setItemMeta(leg);
		
		p.getInventory().setArmorContents(armor);
	}
	

}
