package net.peacefulcraft.trenchpvp.gamehande.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;

public class TrenchPlayer{
	protected Player user;
		public Player getPlayer() {return this.user;}
	
	protected TrenchTeam playerTeam;
		public TrenchTeam getPlayerTeam() {return this.playerTeam;}
	
	protected TrenchClass playerClass;
		public void setPlayerClass(TrenchClass playerClass){this.playerClass = playerClass;}	
		public TrenchClass getPlayerClass(){return playerClass;}
	
	
	public TrenchPlayer(Player userIn, TrenchTeam team, TrenchClass classSelection){
		user = userIn;
		playerTeam = team;
		playerClass = classSelection;
	}
	
//////Class Inventory////////////////////////////////////
	protected ItemStack primary;
	protected ItemStack primaryUtil;
	
	protected ItemStack secondary;
	
	protected ItemStack bread;
	protected ItemStack  health;
	
	protected ItemStack[] armor = new ItemStack[4];
		
	public void giveInvent(){		
		user.getInventory().setItem(0, primary);
		user.getInventory().setItem(2, primaryUtil);
		
		user.getInventory().setItem(1, secondary);
		
		user.getInventory().setItem(7, bread);
		user.getInventory().setItem(8, (ItemStack) health);
		
		user.getInventory().setArmorContents(armor);
		giveCustom();
		user.updateInventory();
	}
	
	public void giveCustom(){
		//Defined by child class. Used to give custom items that may not fit in the above template
		//Items are defined and given to the player directly
	}
}
