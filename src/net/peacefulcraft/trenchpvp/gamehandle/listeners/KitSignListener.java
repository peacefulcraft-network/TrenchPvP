package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchDemoman;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchHeavy;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchMedic;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchScout;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSniper;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSoldier;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSpy;
import net.peacefulcraft.trenchpvp.gameclasses.listeners.InfernoTrapListener;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;

public class KitSignListener implements Listener {
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() == null){return;}
		
		if(e.getClickedBlock().getType() == Material.OAK_SIGN || e.getClickedBlock().getType() == Material.OAK_WALL_SIGN){
			
			Block block = e.getClickedBlock();
			Sign sign = (Sign)block.getState();
			
			if(sign.getLine(0).equalsIgnoreCase("[Class]")){
				
				if(sign.getLine(1).equalsIgnoreCase("Trench")){
					
					if(sign.getLine(2) != null){
						
						TrenchPlayer t = TeamManager.findTrenchPlayer(e.getPlayer());
						if(t == null) {
							e.getPlayer().sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + " You must be playing TrenchPvP to use this sign!");
							return;
						}

						InfernoTrapListener.pyroTrapRemove(t);
						
						String signText = sign.getLine(2).toUpperCase();
						switch(TrenchKits.valueOf(signText)){//Check which class was selected (based on 3rd line of class sign)
						case SCOUT:

							if(TrenchPvP.getTrenchCFG().isScoutEnabled()){
								
								t.equipKit(new TrenchScout());
								e.getPlayer().setAllowFlight(true);
								return;
							
							}else
							
							e.getPlayer().sendMessage("Sorry, the Scout class is currently not avalible for use. Please select another class.");
							return;
							
						case SOLDIER:
							
							if(TrenchPvP.getTrenchCFG().isSoldierEnabled()){
								
								t.equipKit(new TrenchSoldier());
								return;
							
							}else
							
							e.getPlayer().sendMessage("Sorry, the Soldier class is currently not avalible for use. Please select another class.");
							return;
							
						case PYRO:
							
							if(TrenchPvP.getTrenchCFG().isPyroEnabled()){
								
								t.equipKit(new TrenchPyro());
								return;
							
							}else
							
							e.getPlayer().sendMessage("Sorry, the Pyro class is currently not avalible for use. Please select another class.");
							return;
							
						case DEMOMAN:
							
							if(TrenchPvP.getTrenchCFG().isDemomanEnabled()){
								
								t.equipKit(new TrenchDemoman(t));
								return;
							
							}else
							
							e.getPlayer().sendMessage("Sorry, the Demoman class is currently not avalible for use. Please select another class.");
							return;
							
						case HEAVY:
							if(TrenchPvP.getTrenchCFG().isHeavyEnabled()){
							
								t.equipKit(new TrenchHeavy(t));
								return;
							
							}
							
							e.getPlayer().sendMessage("Sorry, the Heavy class is currently not avalible for use. Please select another class.");
							return;
							
						case SNIPER:
							
							if(TrenchPvP.getTrenchCFG().isSniperEnabled()){
								
								t.equipKit(new TrenchSniper(t));
								return;
							
							}
							
							e.getPlayer().sendMessage("Sorry, the Sniper class is currently not avalible for use. Please select another class.");
							return;
							
						case MEDIC:
							if(TrenchPvP.getTrenchCFG().isMedicEnabled()){
								
								t.equipKit(new TrenchMedic(t));
								return;
							}
							
							e.getPlayer().sendMessage("Sorry, the Medic class is currently not avalible for use. Please select another class");
							return;
							
						case SPY:
							if(TrenchPvP.getTrenchCFG().isSpyEnabled()) {
								
								t.equipKit(new TrenchSpy());
								return;
							}
							
							e.getPlayer().sendMessage("Sorry, the Medic class is currently not avalible for use. Please select another class");
							return;
							
						default:
							
						}
						
						if(TeamManager.findTrenchPlayer(e.getPlayer()).getPlayerTeam() == TrenchTeams.BLUE) {
							e.getPlayer().teleport(Teleports.getBlueSpawn());
						}else {
							e.getPlayer().teleport(Teleports.getRedSpawn());
						}
						t.clearPotionEffects();
						e.getPlayer().sendMessage("You are now type " + t.getKitType());
						
					}
				
				}
			
			}
		
		}
	
	}
	
}
