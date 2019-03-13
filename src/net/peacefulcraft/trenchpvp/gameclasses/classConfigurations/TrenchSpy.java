package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class TrenchSpy extends TrenchKit{
	
	public TrenchSpy() {
		kitType = TrenchKits.SPY;
	}
	
	/*
	 * Primary
	 * Spy - Sugar Rush - Raises Health and Speed
	 */
	@Override
	protected void equipPrimary(Player p) {

		//Creates Sugar
		ItemStack primary = new ItemStack(Material.SUGAR, 1);
		//Set sugar name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Sugar Rush");
		//Lore for sugar rush
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Sneak Fast!");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
		p.getInventory().setItem(0, primary);
		
	}
	@Override
	protected void equipSecondary(Player p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void equipGenerics(Player p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void equipArmor(Player p) {
		// TODO Auto-generated method stub
		
	}
}
