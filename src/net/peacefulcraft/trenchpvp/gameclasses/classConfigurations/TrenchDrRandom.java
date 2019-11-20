package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchDrRandom extends TrenchKit 
{
    public TrenchDrRandom(TrenchPlayer t) {
        super(t, TrenchKits.DR_RANDOM);

        //Register speical ability handlers
    }

    @Override
    public void equipItems() {
        Inventory inv = this.getTrenchPlayer().getPlayer().getInventory();

        final String MELEE_NAME = "Lucky Blade";
        final String PRIMARY_NAME = "Critical Roll";

        addItemName(MELEE_NAME, 2);
        addItemName(PRIMARY_NAME, 1);

        //Melee Weapon
        ItemStack melee = new ItemStack(Material.IRON_SWORD, 1);

        ItemMeta meleeMeta = melee.getItemMeta();
        meleeMeta.setDisplayName(MELEE_NAME);

        ArrayList<String> mDesc = new ArrayList<String>();
        mDesc.add("May The Rolls Be In Your Favor!");
        meleeMeta.setLore(mDesc);
        melee.setItemMeta(meleeMeta);

        //Primary Ability
        ItemStack primary = new ItemStack(Material.WHITE_DYE, 1);

        ItemMeta pMeta = primary.getItemMeta();
        pMeta.setDisplayName(PRIMARY_NAME);

        ArrayList<String> pDesc = new ArrayList<String>();
        pDesc.add("Right Click to Roll Your Abilities!");
        pDesc.add("Ability Can Be Triggered Once");
        pDesc.add("After Class Selection.");
        pMeta.setLore(pDesc);

        primary.setItemMeta(pMeta);

        inv.setItem(0, melee);
        inv.setItem(1, primary);
    }

    @Override
    public void equipArmor() {
        Player p = this.getTrenchPlayer().getPlayer();

        ItemStack[] armor = new ItemStack[4];
        armor[3] = new ItemStack(Material.IRON_HELMET, 1);

        armor[2] = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);

        armor[1] = new ItemStack(Material.IRON_LEGGINGS, 1);

        armor[0] = new ItemStack(Material.GOLDEN_BOOTS, 1);

        //TODO: Add Enchantments

        p.getInventory().setArmorContents(armor);
    }

    
}