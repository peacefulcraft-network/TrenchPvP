package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.AbsoluteDefense;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.DusksEdge;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class TrenchHeavy extends TrenchKit{


	public TrenchHeavy(TrenchPlayer t) {
		super(t, TrenchKits.HEAVY);

		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new DusksEdge(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new AbsoluteDefense(this));
	}

	@Override
	public void equipItems() {

		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();
		
		final String PRIMARY_NAME = "Dusk's Edge";
		final String SECONDARY_NAME = "Absolute Defense";
		
		addItemName(PRIMARY_NAME, 1);
		addItemName(SECONDARY_NAME, 1);	
		
		/*
		 * Primary
		 * Heavy - Dense Axe - Slows player and increases attack
		 */
		ItemStack primary = new ItemStack(Material.IRON_AXE, 1);

		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName(PRIMARY_NAME);
		//Set lore to axe
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Increase Attack!");
		pDesc.add("Ability Time: 10 Seconds");
		pDesc.add("Cooldown Time: 25 Seconds");

		pMetaData.setLore(pDesc);
		pMetaData.setUnbreakable(true);
		
		primary.setItemMeta(pMetaData);
		
		/*
		 * Secondary
		 * Heavy - Armadillo Shell - Slows, fatigues player increase defense
		 */
		ItemStack secondary = new ItemStack(Material.SHULKER_SHELL, 1);

		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName(SECONDARY_NAME);

		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click to Engage Defensive Shell!");
		sDesc.add("Ability Time: 15 Seconds");
		sDesc.add("Cooldown Time: 30 Seconds");
		sMetaData.setLore(sDesc);

		secondary.setItemMeta(sMetaData);

		inv.setItem(0, primary);
		inv.setItem(1, secondary);
	}

	/*
	 * Armor Set:
	 * Iron Helmet
	 * Iron Chestplate : Projectile Protection 1
	 * Leather Leggings
	 * Leather Boots
	 */
	@Override
	public void equipArmor() {

		Player p = this.getTrenchPlayer().getPlayer();

		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET,1);

		armor[2] = new ItemStack(Material.IRON_CHESTPLATE,1);

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);

		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);

		ItemMeta metaH = armor[3].getItemMeta();
		metaH.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
		metaH.setUnbreakable(true);
		armor[3].setItemMeta(metaH);

		ItemMeta metaC = armor[2].getItemMeta();
		metaC.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
		metaC.setUnbreakable(true);
		armor[2].setItemMeta(metaC);

		ItemMeta metaL = armor[1].getItemMeta();
		metaL.setUnbreakable(true);
		armor[1].setItemMeta(metaL);

		ItemMeta metaB = armor[0].getItemMeta();
		metaB.setUnbreakable(true);
		armor[0].setItemMeta(metaB);

		p.getInventory().setArmorContents(armor);

	}

}
