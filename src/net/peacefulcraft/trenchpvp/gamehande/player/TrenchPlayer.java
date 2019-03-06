package net.peacefulcraft.trenchpvp.gamehande.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;

public class TrenchPlayer{
	private int[] playerScore;
	protected Player user;
	private TrenchTeam playerTeam;
	private TrenchClass playerClass;
	private int ID;
	
	public TrenchPlayer(Player userIn, TrenchTeam team, TrenchClass classSelection){
		user = userIn;
		playerTeam = team;
		playerScore = new int[3];//score, kills, deaths
		playerClass = classSelection;
	}
	
	public static int findTrenchPlayer(Player user){
		for(int i=0;i<TrenchTeam.trenchPlayers.length; i++){
			if(TrenchTeam.trenchPlayers[i] != null){
				if(TrenchTeam.trenchPlayers[i].getPlayerReference() == user)
					return i;
			}
		}
		return -1;
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
//////////////////////////////////////////////////////
	public void setPlayerClass(TrenchClass classSelection){
		playerClass = classSelection;
	}	
	
	public TrenchClass getPlayerClass(){
		return playerClass;
	}
	
	public Player getPlayerReference(){
		return user;
	}
	public TrenchTeam getPlayerTeam(){
		return playerTeam;
	}
}
