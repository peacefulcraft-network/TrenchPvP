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

import net.peacefulcraft.trenchpvp.gameclasses.abilities.LaunchPad;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchAdrenalineJunkie extends TrenchKit
{

	public TrenchAdrenalineJunkie(TrenchPlayer t)
	{
		super(t, TrenchKits.ADRENALINE_JUNKIE);
		
		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new LaunchPad(this));
		
	}

	@Override
	public void equipItems()
	{
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		//Melee Weapon
		ItemStack melee = new ItemStack(Material.IRON_SWORD, 1);
		
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName("The Bird");
		
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Flip 'em The Bird!");
		meleeMeta.setLore(mDesc);
		melee.setItemMeta(meleeMeta);
		
		//Primary Ability
		ItemStack primary = new ItemStack(Material.GLASS_BOTTLE, 1);
		
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName("Syrum High");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Inject the Syrum!");
		pDesc.add("Ability Time: 15 Seconds");
		pDesc.add("Cooldown Time: 25 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		
		//Secondary Ability
		ItemStack secondary = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 1);
		
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Launch Pad");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click the Ground to Place a Launch Pad!");
		sDesc.add("Ability Time: 15 Seconds");
		sDesc.add("Cooldown Time: 30 Seconds");
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
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		
		armor[1] = new ItemStack(Material.IRON_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.LIME);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaC = (LeatherArmorMeta) armor[2].getItemMeta();
		metaC.setColor(Color.GREEN);
		armor[2].setItemMeta(metaC);
		LeatherArmorMeta metaB = (LeatherArmorMeta) armor[0].getItemMeta();
		metaB.setColor(Color.BLACK);
		armor[0].setItemMeta(metaB);
		
		ItemMeta MetaH = armor[2].getItemMeta();
		MetaH.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2, true);
		armor[2].setItemMeta(MetaH);	
		
		ItemMeta MetaC = armor[2].getItemMeta();
		MetaC.addEnchant(Enchantment.THORNS, 2, true);
		armor[2].setItemMeta(MetaC);
		
		ItemMeta MetaB = armor[0].getItemMeta();
		MetaB.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		armor[0].setItemMeta(MetaB);
		
		p.getInventory().setArmorContents(armor);
		
	}
	
}
