package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.HiddenBlade;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchSpy extends TrenchKit{

	public TrenchSpy(TrenchPlayer t) {
		super(t, TrenchKits.SPY);

		//Register ability listeners
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new HiddenBlade(this));
	}

	@Override
	public void equipItems() {

		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();

		//Creates sword
		ItemStack primary = new ItemStack(Material.GOLDEN_SWORD, 1);
		ItemMeta enchantMeta = primary.getItemMeta();
		enchantMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
		enchantMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		primary.setItemMeta(enchantMeta);
		//Set name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Hidden Blade");
		//Lore for item
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Go Invisible!");
		pDesc.add("Be Careful, You Can't Attack While Invisible!");
		pDesc.add("Ability Time: 8 Seconds");
		pDesc.add("Cooldown Time: 16 Seconds");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);

		inv.setItem(0, primary);

		ItemStack secondary = new ItemStack(Material.CHORUS_FLOWER, 1);
		ItemMeta sMetaData = secondary.getItemMeta();
		sMetaData.setDisplayName("Speed Shot");

		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click Your Teammate to Give Them a Boost!");
		sDesc.add("Ability Time: 7 Seconds");
		sDesc.add("Cooldown Time: 16 Seconds");
		sMetaData.setLore(sDesc);

		secondary.setItemMeta(sMetaData);

		inv.setItem(1, secondary);

	}

	@Override
	public void equipArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.CHAINMAIL_HELMET,1);

		armor[2] = new ItemStack(Material.BAKED_POTATO, 1);

		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);

		armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS, 1);

		ItemMeta enchantMeta = armor[0].getItemMeta();
		enchantMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		armor[0].setItemMeta(enchantMeta);

		this.getTrenchPlayer().getPlayer().getInventory().setArmorContents(armor);
	}
}
