package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;
import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.DoubleJump;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.SpeedShot;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.WindsongBlade;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchScout extends TrenchKit {
	public TrenchScout(TrenchPlayer t) {
		super(t, TrenchKits.SCOUT);
		
		//Register Abilities
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT_ENTITY, new SpeedShot(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_MOVE, new WindsongBlade(this));

		//Listens for two events so they need to share
		DoubleJump dj = new DoubleJump(this);
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_MOVE, dj);
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_TOGGLE_FLIGHT, dj);
	}

	@Override
	public void equipItems() {
		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String PRIMARY_NAME = "Windsong Blade";
		final String SECONDARY_NAME = "Sling Shot";
		
		addItemName(PRIMARY_NAME, 1);
		addItemName(SECONDARY_NAME, 1);	
		
		/**
		 * primary weapon
		 */
		ItemStack primary = new ItemStack(Material.STONE_SWORD, 1);

		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName(PRIMARY_NAME);

		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Hold Blade for Increased Attack Speed!");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);

		inv.setItem(0, primary);


		/**
		 * Secondary Weapon
		 */
		ItemStack arrows = new ItemStack(Material.BOW, 1);
		arrows.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);

		ItemMeta arrowMetaData = arrows.getItemMeta();
		arrowMetaData.setDisplayName(SECONDARY_NAME);
		arrows.setItemMeta(arrowMetaData);

		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Tag Your Foes to Help Your Team!");
		arrowMetaData.setLore(sDesc);

		ItemStack pUtil = new ItemStack(Material.SPECTRAL_ARROW, 64);
		ItemMeta pUMetaData = pUtil.getItemMeta();
		pUMetaData.setDisplayName("Tag Ammo");
		pUtil.setItemMeta(pUMetaData);

		inv.setItem(1, arrows);
		inv.setItem(2, pUtil);

	}

	@Override
	public void equipArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.LEATHER_HELMET,1);

		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);

		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);

		LeatherArmorMeta metaH = (LeatherArmorMeta) armor[3].getItemMeta();
		metaH.setColor(Color.BLACK);
		armor[3].setItemMeta(metaH);
		LeatherArmorMeta metaC = (LeatherArmorMeta) armor[2].getItemMeta();
		metaC.setColor(Color.RED);
		armor[2].setItemMeta(metaC);
		LeatherArmorMeta metaL = (LeatherArmorMeta) armor[1].getItemMeta();
		metaL.setColor(Color.BLUE);
		armor[1].setItemMeta(metaL);
		LeatherArmorMeta metaS = (LeatherArmorMeta) armor[0].getItemMeta();
		metaS.setColor(Color.BLACK);
		armor[0].setItemMeta(metaS);

		this.getTrenchPlayer().getPlayer().getInventory().setArmorContents(armor);
	}
}
