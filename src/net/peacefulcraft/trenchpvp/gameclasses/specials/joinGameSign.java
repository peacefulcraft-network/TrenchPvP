package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TrenchCueHandle;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;//(Interface, not the class material)
import org.bukkit.entity.Player;

public class joinGameSign implements Listener {
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		if(e.getClickedBlock() == null){return;}
		if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN ){
			Block block = e.getClickedBlock();
			Sign sign = (Sign)block.getState();
			if(sign.getLine(0).equalsIgnoreCase("[TRJOIN]")){
				Player user = e.getPlayer();
				if(TrenchPlayer.findTrenchPlayer(user) == -1){
					if(user.hasPermission("tpp.player")){
						TrenchCueHandle.joinTeam(user);//Join game / tp player to spawn for class choosing
						if(TrenchTeam.trenchPlayers[TrenchPlayer.findTrenchPlayer(user)].getPlayerTeam() == TrenchTeam.BLUE)
							user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
						else
							user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " team!");
					}else
						user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You do not have access to this command");
				}else 
					user.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are already on a Trench team! Type /trleave to leave."); 
			}
		}
			
	}
}
