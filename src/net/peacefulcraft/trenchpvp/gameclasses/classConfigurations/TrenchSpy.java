package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchSpy extends TrenchPlayer{
	
	public TrenchSpy(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.SPY);
		configure();
	}
	public void configure() {
		/*
		 * Primary
		 * Spy - Sugar Rush - Raises Health and Speed
		 */
		//Creates Sugar
		primary = new ItemStack(Material.SUGAR, 1);
		//Set sugar name
		ItemMeta pMetaData = primary.getItemMeta();
		pMetaData.setDisplayName("Sugar Rush");
		//Lore for sugar rush
		ArrayList<String> pDesc = new ArrayList<String>();
		pDesc.add("Right Click to Sneak Fast!");
		pMetaData.setLore(pDesc);
		
		primary.setItemMeta(pMetaData);
		
	}
}
