package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.Flamethrower;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchPyro extends TrenchKit{

	private TrenchPlayer t;
	private TrenchKits k = TrenchKits.PYRO;
		public TrenchKits getKitType() { return k; }

	public TrenchPyro(TrenchPlayer t) {
		super(t, TrenchKits.PYRO);
		this.t = t;

		//Register special ability handlers
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new Flamethrower(this));
	}

	@Override
	public void equipArmor() {

		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET,1);

		armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE,1);
		armor[2].addEnchantment(Enchantment.PROTECTION_FIRE, 2);

		armor[1] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		armor[1].addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);

		armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
		t.getPlayer().getInventory().setArmorContents(armor);

	}

	@Override
	public void equipItems() {

		Inventory inv = t.getPlayer().getInventory();

		ItemStack primary = new ItemStack(Material.GOLDEN_AXE);

		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Nether's Bite");

		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Just a Sharp Axe.");
		pMetaData.setLore(pDesc);

		primary.setItemMeta(pMetaData);

		ItemStack pUtil = new ItemStack(Material.BLAZE_ROD);
		ItemMeta pUMeta = pUtil.getItemMeta();
		pUMeta.setDisplayName("Flamethrower");

		ArrayList<String> pUDesc = new ArrayList<String>();
		pUDesc.add("Right Click to Shoot Flames!");
		pUMeta.setLore(pUDesc);

		pUtil.setItemMeta(pUMeta);

		inv.setItem(0, primary);
		inv.setItem(1, pUtil);

		/**
		 * Equip Inferno trap
		 */
		ItemStack secondary = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta sMeta = secondary.getItemMeta();
		sMeta.setDisplayName("Inferno Trap Detonator");

		ArrayList<String> sDesc = new ArrayList<String>();
		sDesc.add("Right Click to Detonate Inferno Traps!");
		sMeta.setLore(sDesc);

		secondary.setItemMeta(sMeta);

		ItemStack trapAmmo = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 5);
		ItemMeta tAMeta = trapAmmo.getItemMeta();
		tAMeta.setDisplayName("Inferno Trap");

		ArrayList<String> aDesc = new ArrayList<String>();
		aDesc.add("Can Only Place 5 at a Time! Detonate to Get More!");
		tAMeta.setLore(aDesc);

		trapAmmo.setItemMeta(tAMeta);

		inv.setItem(2, secondary);
		inv.setItem(3, trapAmmo);

	}

}
