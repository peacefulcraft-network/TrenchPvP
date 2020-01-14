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

import net.peacefulcraft.trenchpvp.gameclasses.abilities.PorkChop;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.SacraficialCannon;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchPigKing extends TrenchKit
{

	public TrenchPigKing(TrenchPlayer t)
	{
		super(t, TrenchKits.PIG_KING);
		
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new SacraficialCannon(this));
		getAbilityManager().registerAbility(TrenchAbilityType.ENTITY_DAMAGE_ENTITY, new PorkChop(this));
	}

	@Override
	public void equipItems()
	{
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String MELEE_NAME = "Pork CHOP";
		final String PRIMARY_NAME = "Sacraficial Cannon";
		
		addItemName(MELEE_NAME, 2);
		addItemName(PRIMARY_NAME, 1);
		
		//Melee Weapon
		ItemStack melee = new ItemStack(Material.PORKCHOP, 1);
		
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Smack Down Your Foes With The Blood of Your Fallen");
		mDesc.add("Deals 4 Hearts of Damage");
		meleeMeta.setLore(mDesc);
		melee.setItemMeta(meleeMeta);
		
		//Primary Ability
		ItemStack primary = new ItemStack(Material.COOKED_PORKCHOP, 1);
		
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName(PRIMARY_NAME);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click On Ground to Summon Explosive Pigs!");
		pDesc.add("Summons 5-7 Pigs That Explode After 5 Seconds");
		pDesc.add("Cooldown Time: 15 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		
		inv.setItem(0, melee);
		inv.setItem(1, primary);
	}

	@Override
	public void equipArmor()
	{
		Player p = this.getTrenchPlayer().getPlayer();
		
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET, 1);
		
		armor[2] = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.IRON_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.FUCHSIA);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaL = (LeatherArmorMeta) armor[1].getItemMeta();
		metaL.setColor(Color.FUCHSIA);
		armor[1].setItemMeta(metaL);
		
		ItemMeta MetaH = armor[3].getItemMeta();
		MetaH.addEnchant(Enchantment.PROTECTION_FIRE, 3, true);
		armor[3].setItemMeta(MetaH);
		ItemMeta MetaC = armor[2].getItemMeta();
		MetaC.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
		armor[2].setItemMeta(MetaC);
		
		ItemMeta MetaB = armor[0].getItemMeta();
		MetaB.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
		armor[0].setItemMeta(MetaB);
		
		p.getInventory().setArmorContents(armor);
	}

}
