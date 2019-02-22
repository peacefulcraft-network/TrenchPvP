package net.peacefulcraft.trenchpvp.gamehande;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchUndefined;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public abstract class TrenchCueHandle {
	private TrenchCueHandle(){}//Private constructor
	public static void joinTeam(Player user){
		TrenchScoreboard teamAssign = new TrenchScoreboard();
		if(TrenchTeam.blueCount > TrenchTeam.redCount){//Add to red
			TrenchTeam.redCount++;//Add one to team count
			TrenchTeam.trenchPlayers[TrenchTeam.redCount + TrenchTeam.blueCount] = new TrenchUndefined(user ,TrenchTeam.RED);//Add user to team list as undefined tClass type
			teamAssign.setRed(user.getDisplayName());
			user.teleport(TrenchPvP.RED_SPAWN);
		}else{//Add to blue
			TrenchTeam.blueCount++;//Add one to team count
			TrenchTeam.trenchPlayers[TrenchTeam.redCount + TrenchTeam.blueCount] = new TrenchUndefined(user, TrenchTeam.BLUE);//Add user to team list as undefined tClass type
			teamAssign.setBlue(user.getDisplayName());
			user.teleport(TrenchPvP.BLUE_SPAWN);
		}
		user.setGameMode(GameMode.ADVENTURE);
	}
	
	public static void leaveTeam(Player user){
		int userReferenceInt = TrenchPlayer.findTrenchPlayer(user);
		TrenchScoreboard teamAssign = new TrenchScoreboard();
		if(userReferenceInt != -1){//Return index for array where user reference resides
			TrenchPlayer userReference = TrenchTeam.trenchPlayers[userReferenceInt];
			TrenchTeam.trenchPlayers[userReferenceInt] = null;
			if(userReference.getPlayerTeam() == TrenchTeam.BLUE){
				teamAssign.unsetBlue(user.getDisplayName());
				TrenchTeam.blueCount--;
			}else{
				teamAssign.unsetRed(user.getDisplayName());
				TrenchTeam.redCount--;
				user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have left Trench PvP!");
			}
			user.getInventory().clear();//Clear inventory / Armor
			ItemStack[] emptyArmor = new ItemStack[4];
			user.getInventory().setArmorContents(emptyArmor);
			user.setGameMode(GameMode.ADVENTURE);
			user.teleport(TrenchPvP.WORLD_SPAWN);
		}else{
			user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are not apart of a team!");
		}
		
	}
	
	public static void playerVisualFormat(){//Colored names in chat / stuff
		
	}
}
