package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class StartGameSign implements Listener {

	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() == null) { return; }
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(e.getPlayer());
		if(t == null) { return; }
		
		if(e.getClickedBlock().getType() == Material.OAK_SIGN || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN) {
			
			Sign sign = (Sign) e.getClickedBlock().getState();
			if(sign.getLine(0).equalsIgnoreCase("[Trench]")) {
				if(sign.getLine(1).equalsIgnoreCase("Start")) {
					
					if(t.getPlayerTeam() == TrenchTeams.BLUE) {
						t.getPlayer().teleport(Teleports.getBlueSpawn());
					}else {
						t.getPlayer().teleport(Teleports.getRedSpawn());
					}
					
				}
				
			}
			
		}
		
	}
	
}
