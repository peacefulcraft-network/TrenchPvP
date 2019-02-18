package net.peacefulcraft.parsonswy.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import net.peacefulcraft.parsonswy.trenchpvp.gameclasses.MediGun;
import net.peacefulcraft.parsonswy.trenchpvp.gameclasses.TrenchClass;
import net.peacefulcraft.parsonswy.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.parsonswy.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchMedic extends TrenchPlayer{
	private MediGun medigun;
	
	public TrenchMedic(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.MEDIC);
		configure();//Configure class Inventory
		medigun = new MediGun();
	}
	
	public MediGun getMediGun(){
		return medigun;
	}
	
	public void configure() {		
	//Primary///////////////////////////////////////
		/*Medic - Medi Gun
		 *Heal players ever 1.5 seconds (MediGun.java (class))
		 */
		//Create Bow / give enchantments
		primary = new ItemStack(Material.REDSTONE_BLOCK, 1);
		
		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Medi Gun");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click Your Teamates to Heal Them! (1.5 second cool down)");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
						
	//Secondary///////////////////////////////////////////
		/*Bow:Syringe Gun w/ 32 arrows
		 * 
		 */
		secondary = new ItemStack(Material.BOW, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Syringe Gun");
		secondary.setItemMeta(sMeta);
		primaryUtil = new ItemStack(Material.ARROW, 32);//give arrows for syringe gun
		
		//Bread for food
		bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		health = new ItemStack(Material.POTION, 2);
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
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
	}
}
