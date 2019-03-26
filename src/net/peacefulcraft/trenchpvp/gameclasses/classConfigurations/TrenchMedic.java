package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchMedic extends TrenchKit{

	public TrenchMedic() {
		kitType = TrenchKits.MEDIC;
	}
	
	/*Medic - Medi Gun
	 *Heal players ever 1.5 seconds (MediGun.java (class))
	 */
	@Override
	protected void equipPrimary(Player p) {
			
		//Create Bow / give enchantments
		ItemStack primary = new ItemStack(Material.REDSTONE_BLOCK, 1);
		
		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Medi Gun");
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click Your Teamates to Heal Them! (1.5 second cool down)");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		p.getInventory().setItem(0, primary);
		
	}


	/*Bow:Syringe Gun w/ 32 arrows
	 * 
	 */
	@Override
	protected void equipSecondary(Player p) {
		ItemStack secondary = new ItemStack(Material.BOW, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Syringe Gun");
		secondary.setItemMeta(sMeta);
		ItemStack secondaryyUtil = new ItemStack(Material.ARROW, 32);//give arrows for syringe gun
		
		p.getInventory().setItem(1, secondary);
		p.getInventory().setItem(3, secondaryyUtil);
		
	}


	@Override
	protected void equipGenerics(Player p) {
		
		//Bread for food
		ItemStack bread = new ItemStack(Material.BREAD, 32);
		
		//Create PotionMeta ItemStack to set type of Instant Health 2. Overrides existing effects (true)
		ItemStack health = new ItemStack(Material.POTION, 4);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.setDisplayName("Instant Health");
		PotionMeta pHealthMeta = (PotionMeta) healthMeta;
		
		PotionEffect instantHealth = new PotionEffect(PotionEffectType.HEAL, 1, 2);
		pHealthMeta.addCustomEffect(instantHealth, true);
		
		health.setItemMeta(healthMeta);

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
		
		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		p.getInventory().setArmorContents(armor);
	
	}
}
