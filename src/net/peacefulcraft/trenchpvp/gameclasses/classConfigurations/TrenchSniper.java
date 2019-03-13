package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class TrenchSniper extends TrenchKit{
	
	public TrenchSniper() {
		kitType = TrenchKits.SNIPER;
	}
	
	/*Sniper - punch 2 power 1
	 *32 arrows
	 */
	@Override
	protected void equipPrimary(Player p) {

		//Create Bow / give enchantments
		ItemStack primary = new ItemStack(Material.BOW, 1);
		primary.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		primary.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		
		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Sniper Riffle");
		primary.setItemMeta(pMetaData);
		
		//Create arrows for sniper riffle
		ItemStack primaryUtil = new ItemStack(Material.ARROW, 32);
		ItemMeta pUMetaData = primaryUtil.getItemMeta();
		pUMetaData.setDisplayName("Bullets");
		primaryUtil.setItemMeta(pUMetaData);
		
		p.getInventory().setItem(0, primary);
		p.getInventory().setItem(2, primaryUtil);
		
	}

	/*Stone Sword "Kukri" 
	 * 
	 */
	@Override
	protected void equipSecondary(Player p) {

		ItemStack secondary = new ItemStack(Material.STONE_SWORD, 1);
		secondary.getItemMeta().setDisplayName("Kukri");
		
		p.getInventory().setItem(1, secondary);
		
	}

	@Override
	protected void equipGenerics(Player p) {
		
		ItemStack bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		ItemStack health = new ItemStack(Material.POTION, 2);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(pHealthMeta);
		
		p.getInventory().setItem(7, bread);
		p.getInventory().setItem(8, (ItemStack) health);
	}

	/*Leather Helment
	 * Leather Chestplate : Projectile Protection 1
	 * Leather Leggings
	 * Leather Boots
	 */
	@Override
	protected void equipArmor(Player p) {

		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);
		
		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		p.getInventory().setArmorContents(armor);
		
	}
}
