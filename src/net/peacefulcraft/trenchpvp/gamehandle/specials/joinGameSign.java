package net.peacefulcraft.trenchpvp.gamehandle.specials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
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
				
				Player p = e.getPlayer();
				try {
					TrenchPlayer t = TeamManager.findTrenchPlayer(p);
					p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You are already on a Trench team! Type /trleave to leave.");
					return;
				}catch(RuntimeException x) {
					//RuntimeException good, means user is not on a team
				}
				
				if(p.hasPermission("tpp.player")){
					
					TrenchPlayer t = TrenchPvP.getTeamManager().joinTeam(p);
					if(t.getPlayerTeam() == TrenchTeam.BLUE)
						p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_BLUE + "Blue" + ChatColor.RED + " team!");
					else
						p.sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + "You have joined " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " team!");
					
					//TODO: GIVE SHIT TO PLAYER
					
					return;
				
				}else {return;}

			}else{return;}
			
		}
	}
	
}
