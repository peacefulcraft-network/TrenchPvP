package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.abilities.CriticalIntelligence;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.FriendlyKissOfDeath;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.HiddenBlade;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.HiddenBladeBlock;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.IntelligenceRemover;
import net.peacefulcraft.trenchpvp.gameclasses.abilities.TrenchAbilityType;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchSpy extends TrenchKit{
	
	private TrenchKits k = TrenchKits.SPY;
		public TrenchKits getKitType() {return k;}
	private ArrayList<TrenchPlayer> intelligenceList;
		public ArrayList<TrenchPlayer> getIntelligence() {return intelligenceList; }
		public void resetIntelligence() {intelligenceList = new ArrayList<TrenchPlayer>(); }
	public IntelligenceRemover intelligenceRemover;
	
	public TrenchSpy(TrenchPlayer t) {
		super(t, TrenchKits.SPY);

		resetIntelligence();
		
		//Register ability listeners
		intelligenceRemover = new IntelligenceRemover(this);
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new HiddenBlade(this));
		getAbilityManager().registerAbility(TrenchAbilityType.ENTITY_DAMAGE_ENTITY, new HiddenBladeBlock(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT_ENTITY, new CriticalIntelligence(this));
		getAbilityManager().registerAbility(TrenchAbilityType.PLAYER_INTERACT, new FriendlyKissOfDeath(this));
	}

	@Override
	public void equipItems() {

		Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();

		final String MELEE_NAME = "Hidden Blade";
		//final String SECONDARY_NAME = "Speed Shot";
		final String PRIMARY_NAME = "Critical Intelligence";
		final String PRIMARY_UTIL_NAME = "Friendly Kiss of Death";
		
		addItemName(MELEE_NAME, 1);
		//addItemName(SECONDARY_NAME, 1);
		addItemName(PRIMARY_NAME, 1);
		addItemName(PRIMARY_UTIL_NAME, 1);
		
		//Creates sword
		ItemStack melee = new ItemStack(Material.GOLDEN_SWORD, 1);
		ItemMeta enchantMeta = melee.getItemMeta();
		enchantMeta.setUnbreakable(true);
		enchantMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
		enchantMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		enchantMeta.addEnchant(Enchantment.DURABILITY, 5, true);
		melee.setItemMeta(enchantMeta);
		//Set name
		ItemMeta meleeMeta = melee.getItemMeta();
		meleeMeta.setDisplayName(MELEE_NAME);
		//Lore for item
		ArrayList<String> mDesc = new ArrayList<String>();
		mDesc.add("Right Click to Go Invisible And Get Attack Bonus!");
		mDesc.add("Be Careful, You Can't Attack While Invisible!");
		mDesc.add("Ability Time: 8 Seconds");
		mDesc.add("Cooldown Time: 16 Seconds");
		meleeMeta.setLore(mDesc);

		melee.setItemMeta(meleeMeta);

		inv.setItem(0, melee);

		ItemStack primary = new ItemStack(Material.PAPER, 1);
		ItemMeta pMeta = primary.getItemMeta();
		pMeta.setDisplayName(PRIMARY_NAME);
		
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click On Enemies To Gather Intelligence For Kiss Of Death!");
		pDesc.add("The More Intelligence, The Stronger Your Attack!");
		pDesc.add("Cooldown Time: 5 Seconds");
		pMeta.setLore(pDesc);
		
		primary.setItemMeta(pMeta);
		inv.setItem(2, primary);
		
		ItemStack primaryUtil = new ItemStack(Material.WITHER_ROSE, 1);
		ItemMeta pUMeta = primaryUtil.getItemMeta();
		pUMeta.setDisplayName(PRIMARY_UTIL_NAME);
		
		ArrayList<String> pUDesc = new ArrayList<String>();
		pUDesc.add("Right Click To Use Gathered Intelligence To Appear Behind Your Enemies!");
		pUDesc.add("Cooldwon Time: 60 Seconds");
		pUMeta.setLore(pUDesc);
		
		primaryUtil.setItemMeta(pUMeta);
		inv.setItem(1, primaryUtil);
		
		
		//ItemStack secondary = new ItemStack(Material.CHORUS_FLOWER, 1);
		//ItemMeta sMetaData = secondary.getItemMeta();
		//sMetaData.setDisplayName(SECONDARY_NAME);

		//ArrayList<String> sDesc = new ArrayList<String>();
		//sDesc.add("Right Click Your Teammate to Give Them a Boost!");
		//sDesc.add("Ability Time: 7 Seconds");
		//sDesc.add("Cooldown Time: 16 Seconds");
		//sMetaData.setLore(sDesc);

		//secondary.setItemMeta(sMetaData);

		//inv.setItem(3, secondary);
		
		

	}

	@Override
	public void equipArmor() {
		ItemStack[] armor = new ItemStack[4];

		armor[2] = new ItemStack(Material.BAKED_POTATO, 1);

		this.getTrenchPlayer().getPlayer().getInventory().setArmorContents(armor);
	}
}
