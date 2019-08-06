package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;//(Interface, not the class material)
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;

public class JoinGameSign implements Listener {
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		
		if(e.getClickedBlock() == null){return;}
		
		if(e.getClickedBlock().getType() == Material.OAK_SIGN || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN ){
			
			Block block = e.getClickedBlock();
			Sign sign = (Sign)block.getState();
			if(sign.getLine(0).equalsIgnoreCase("[Trench]")){
				if(sign.getLine(1).equalsIgnoreCase("Join")) {
					if(TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer()) != null) {
						e.getPlayer().sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + " You're already playing TrenchPvP!");
						return;
					}
					
					TrenchPvP.getTrenchManager().getCurrentArena().playerJoin(e.getPlayer());
				}
			}else{ return; }
			
		}else{ return; }
			
	}
}

