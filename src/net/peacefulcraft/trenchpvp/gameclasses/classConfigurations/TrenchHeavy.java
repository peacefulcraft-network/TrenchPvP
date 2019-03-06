package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.specials.ArmadilloShell;
import net.peacefulcraft.trenchpvp.gameclasses.specials.DenseAxe;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchHeavy extends TrenchPlayer{
	private DenseAxe denseAxe;
	private ArmadilloShell armadilloShell;
	
	public TrenchHeavy(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.HEAVY);
		configure();
		denseAxe = new DenseAxe();
		armadilloShell = new ArmadilloShell();
	}
	
	public ArmadilloShell getArmaShell()
	{
		return armadilloShell;
	}
	
	public DenseAxe getDenseAxe()
	{
		return denseAxe;
	}
	
	public void configure() {
		/*
		 * Primary
		 * Heavy - Dense Axe - Slows player and increases attack
		 */
		//Create Axe
		primary = new ItemStack(Material.IRON_AXE, 1);
		
		//Set axe name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Dense Axe");
		//Set lore to axe
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Increase Attack!");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		/*
		 * Secondary
		 * Heavy - Armadillo Shell - Slows, fatigues player increase defense
		 */
		secondary = new ItemStack(Material.SHULKER_SHELL, 1);
		
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName("Armadillo Shell");
		
		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click to Engage Defensive Shell!");
		sMetaData.setLore(sDesc);
		
		secondary.setItemMeta(sMetaData);
		
		/*
		 * Assorted Inventory
		 */
		//Food
		bread = new ItemStack(Material.BREAD, 32);
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		health = new ItemStack(Material.POTION, 2);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(healthMeta);
		
		/*
		 * Armor Set:
		 * Iron Helmet
		 * Iron Chestplate : Projectile Protection 1
		 * Leather Leggings
		 * Leather Boots
		 */
		armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET,1);
		
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE,1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		ItemMeta enchantMeta = armor[2].getItemMeta();
		enchantMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
		armor[2].setItemMeta(enchantMeta);
	}

}
