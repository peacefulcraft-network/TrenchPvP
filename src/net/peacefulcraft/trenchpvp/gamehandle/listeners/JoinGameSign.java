package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehande.Announcer;
import net.peacefulcraft.trenchpvp.gamehande.GameManager;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;//(Interface, not the class material)
import org.bukkit.entity.Player;

public class JoinGameSign implements Listener {
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		
		if(e.getClickedBlock() == null){return;}
		
		if(e.getClickedBlock().getType() == Material.OAK_SIGN || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN ){
			
			Block block = e.getClickedBlock();
			Sign sign = (Sign)block.getState();
			if(sign.getLine(0).equalsIgnoreCase("[Trench]")){
				if(sign.getLine(1).equalsIgnoreCase("Join")) {
					if(TeamManager.findTrenchPlayer(e.getPlayer()) != null) {
						e.getPlayer().sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + " You're already playing TrenchPvP!");
						return;
					}
					
					GameManager.joinPlayer(e.getPlayer());
				}
			}else{ return; }
			
		}else{ return; }
			
	}
}

