package net.peacefulcraft.parsonswy.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.parsonswy.trenchpvp.gameclasses.TrenchClass;
import net.peacefulcraft.parsonswy.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.parsonswy.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchDemoman extends TrenchPlayer{

	public TrenchDemoman(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.DEMOMAN);
		configure();
	}
	public void configure() {
	//Primary///////////////////////////////////////
		/*Demoman - Grenade Launcher - Launches grenades(FireWork Charges x 16) that bounce and explode on impact
		 * --Handled by GrenadeLauncher(class)
		 * 
		 *
		 */
		//Create Bow / give enchantments
		primary = new ItemStack(Material.QUARTZ, 1);
		
		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Grenade Launcher");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Left Click to Launch Grenades!");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		primaryUtil = new ItemStack(Material.FIREWORK_CHARGE, 16);//give arrows for syringe gun
		pMetaData = primaryUtil.getItemMeta();
		pMetaData.setDisplayName("Grenades");

		pDesc.set(0, "Ammunition for Grenade Launcher");
		pMetaData.setLore(pDesc);
		
		primaryUtil.setItemMeta(pMetaData);
	//Secondary///////////////////////////////////////////
		/*Demoman - Stick Bomb Launcher - Launches Sticky Bombs (Fire Charges) On land, turns into pressure plate that explodes when right click with launcher
		 *--Handled by StickyLauncher(class)
		 */
		secondary = new ItemStack(Material.NETHER_BRICK_ITEM, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Sticky Bomb Launcher.");
		secondary.setItemMeta(sMeta);
		
		//Bread for food
		bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		health = new ItemStack(Material.POTION, 1);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(healthMeta);

	//Armor///////////////////////////////////////////////	
		/*Leather Helment
		 * Leather Chestplate : Projectile Protection 1
		 * Leather Leggings
		 * Leather Boots
		 */
		armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 2);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		armor[1].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
	}

	public void giveCustom(){
		ItemStack stickyAmo = new ItemStack(Material.FIREBALL, 12);
		ItemMeta sAMeta = stickyAmo.getItemMeta();
		sAMeta.setDisplayName("Sticky Bombs");
		
		stickyAmo.setItemMeta(sAMeta);
		
		user.getInventory().setItem(3, stickyAmo);
	}
}
