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

import net.peacefulcraft.trenchpvp.gameclasses.abilities.CatastrophicRapier;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.EntityUprising;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.NthExplosion;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchNthEntity extends TrenchKit
{

	public TrenchNthEntity(TrenchPlayer t)
	{
		super(t, TrenchKits.NTH_ENTITY);

		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new CatastrophicRapier(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new EntityUprising(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new NthExplosion(this));
	}

	@Override
	public void equipItems()
	{
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String MELEE_NAME = "Catastrophic Rapier";
		final String PRIMARY_NAME = "Entity Uprising";
		final String SECONDARY_NAME = "Nth Explosion";
		
		addItemName(MELEE_NAME, 1);
		addItemName(PRIMARY_NAME, 1);
		addItemName(SECONDARY_NAME, 1);	
		
		//Melee Weapon
		ItemStack melee = new ItemStack(Material.DIAMOND_SWORD, 1);
		
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Strike Down Your Foes With Frightening Power");
		mDesc.add("Right Click To Lunge Forward!");
		mDesc.add("Cooldown: 2 Seconds");
		meleeMeta.setLore(mDesc);
		
		meleeMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		
		melee.setItemMeta(meleeMeta);
		
		//Primary Ability
		ItemStack primary = new ItemStack(Material.WHITE_STAINED_GLASS, 1);
		
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName(PRIMARY_NAME);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click To Cause Your Enemies To Levitate!");
		pDesc.add("Ability Time: 7 Seconds");
		pDesc.add("Cooldown Time: 20 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		
		//Secondary Ability
		ItemStack secondary = new ItemStack(Material.TNT, 1);
		
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName(SECONDARY_NAME);
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click To Cause Nearby Enemies To Explode!");
		sDesc.add("Cooldwn Time: 25 Seconds");
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
		armor[3] = new ItemStack(Material.LEATHER_HELMET, 1);
		
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE, 1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.GOLDEN_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.WHITE);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaL = (LeatherArmorMeta) armor[1].getItemMeta();
		metaL.setColor(Color.BLACK);
		armor[1].setItemMeta(metaL);
		
		ItemMeta MetaH = armor[3].getItemMeta();
		MetaH.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 5, true);
		armor[3].setItemMeta(MetaH);
		ItemMeta MetaB = armor[0].getItemMeta();
		MetaB.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2, true);
		MetaB.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
		armor[0].setItemMeta(MetaB);
		
		p.getInventory().setArmorContents(armor);
	}
	

}
