package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchTeam;
import net.peacefulcraft.trenchpvp.gamehandle.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class ChangeClassSign implements Listener {

	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() == null) { return; }
		
		if(e.getClickedBlock().getType() == Material.OAK_SIGN || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN) {
			
			Sign sign = (Sign) e.getClickedBlock().getState();
			
			if(sign.getLine(0).equalsIgnoreCase("[Trench]")) {
				if(sign.getLine(1).equalsIgnoreCase("Class Room")) {
					
					TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
					if(t == null) { 
						Announcer.messagePlayer(e.getPlayer(), "You must be playing TrenchPvP to enter the class selection room");
						return;
					}
					
					if(t.getPlayerTeam() == TrenchTeam.BLUE) {
						t.getPlayer().teleport(Teleports.getBlueClassSpawn());
					}else {
						t.getPlayer().teleport(Teleports.getRedClassSpawn());
					}
					
				}
			}
			
		}
		
	}
	
}
