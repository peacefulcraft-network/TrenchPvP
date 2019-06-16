package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.MediGun;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchMedic extends TrenchKit{

	public TrenchMedic(TrenchPlayer t) {
		super(t, TrenchKits.MEDIC);

		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT_ENTITY, new MediGun(this));

	}


	/*Leather Helment
	 * Leather Chestplate : Projectile Protection 1
	 * Leather Leggings
	 * Leather Boots
	 */
	@Override
	public void equipArmor() {

		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);

		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);

		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		
		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.WHITE);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaC = (LeatherArmorMeta) armor[2].getItemMeta();
		metaC.setColor(Color.WHITE);
		armor[2].setItemMeta(metaC);
		LeatherArmorMeta metaL = (LeatherArmorMeta) armor[1].getItemMeta();
		metaL.setColor(Color.WHITE);
		armor[1].setItemMeta(metaL);
		LeatherArmorMeta metaB = (LeatherArmorMeta) armor[0].getItemMeta();
		metaB.setColor(Color.BLACK);
		armor[0].setItemMeta(metaB);
		
		t.getPlayer().getInventory().setArmorContents(armor);
	}

	@Override
	public void equipItems() {

		Inventory inv = t.getPlayer().getInventory();

		/*Medic - Medi Gun
		 *Heal players ever 1.5 seconds (MediGun.java (class))
		 */
		//Create Bow / give enchantments
		ItemStack primary = new ItemStack(Material.REDSTONE_BLOCK, 1);

		//Get metadata / set bow name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Medi Gun");

		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click Your Teamates to Heal Them! (1.5 second cool down)");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);
		inv.setItem(0, primary);

		/*Bow:Syringe Gun w/ 32 arrows
		 *
		 */
		ItemStack secondary = new ItemStack(Material.BOW, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Syringe Gun");
		secondary.setItemMeta(sMeta);
		ItemStack secondaryyUtil = new ItemStack(Material.ARROW, 32);//give arrows for syringe gun

		inv.setItem(1, secondary);
		inv.setItem(3, secondaryyUtil);

	}
}
