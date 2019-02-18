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

public class TrenchSniper extends TrenchPlayer{
	public TrenchSniper(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.SNIPER);
		configure();//Configure Inventory for class
	}
	
	public void configure() {
		user.sendMessage("Configuring your class");
	//Primary///////////////////////////////////////
		/*Sniper - punch 2 power 1
		 *32 arrows
		 */
		//Create Bow / give enchantments
		primary = new ItemStack(Material.BOW, 1);
		primary.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		primary.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		
		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Sniper Riffle");
		primary.setItemMeta(pMetaData);
		
		//Create arrows for sniper riffle
		primaryUtil = new ItemStack(Material.ARROW, 32);
		ItemMeta pUMetaData = primaryUtil.getItemMeta();
		pUMetaData.setDisplayName("Bullets");
		primaryUtil.setItemMeta(pUMetaData);

	//Secondary///////////////////////////////////////////
		/*Stone Sword "Kukri" 
		 * 
		 */
		secondary = new ItemStack(Material.STONE_SWORD, 1);
		secondary.getItemMeta().setDisplayName("Kukri");

		bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		health = new ItemStack(Material.POTION, 2);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(pHealthMeta);

	//Armor///////////////////////////////////////////////	
		/*Leather Helment
		 * Leather Chestplate : Projectile Protection 1
		 * Leather Leggings
		 * Leather Boots
		 */
		armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
	}
}
