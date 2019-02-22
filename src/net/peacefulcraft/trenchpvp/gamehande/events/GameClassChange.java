package net.peacefulcraft.trenchpvp.gamehande.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.TrenchClass;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.*;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class GameClassChange implements Listener {
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		if(e.getClickedBlock() == null){return;}
		if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN){
			Block block = e.getClickedBlock();
			Sign sign = (Sign)block.getState();
			if(sign.getLine(0).equalsIgnoreCase("[Class]")){
				if(sign.getLine(1).equalsIgnoreCase("Trench")){
					if(sign.getLine(2) != null){
						int loc = TrenchPlayer.findTrenchPlayer(e.getPlayer());
						if(loc == -1){//User not playing game. Cannot use sign
							e.getPlayer().sendMessage(TrenchPvP.CMD_PREFIX + ChatColor.RED + " You must bne playing TrenchPvP to use this sign!");
							return;
						}
						//remove player class reference to create new one
						TrenchTeam team = TrenchTeam.trenchPlayers[loc].getPlayerTeam();
						TrenchTeam.trenchPlayers[loc] = null;
						
						String signText = sign.getLine(2).toUpperCase();
						switch(TrenchClass.valueOf(signText)){//Check which class was selected (based on 3rd line of class sign)
						case SCOUT:
						break;case SOLDIER:
						break;case PYRO:
						break;case DEMOMAN:
							if(TrenchClass.useDemoman){
								TrenchTeam.trenchPlayers[loc] = new TrenchDemoman(e.getPlayer(), team);
								TrenchTeam.trenchPlayers[loc].giveInvent();
							}
							e.getPlayer().sendMessage("Sorry, the Demoman class is currently not avalible for use. Please select another class.");
						break;case HEAVY:
						break;case SNIPER:
							if(TrenchClass.useSniper){
								TrenchTeam.trenchPlayers[loc] = new TrenchSniper(e.getPlayer(), team);
								TrenchTeam.trenchPlayers[loc].giveInvent();
							}else{
								e.getPlayer().sendMessage("Sorry, the Sniper class is currently not avalible for use. Please select another class.");
							}
						break;case MEDIC:
							if(TrenchClass.useMedic){
								TrenchTeam.trenchPlayers[loc] = new TrenchMedic(e.getPlayer(), team);
								TrenchTeam.trenchPlayers[loc].giveInvent();
							}else{
								e.getPlayer().sendMessage("Sorry, the Medic class is currently not avalible for use. Please select another class.");
							}
						break;case SPY:
							
						break;default:
							
						break;
						}
						
						e.getPlayer().sendMessage("You are now type " + TrenchTeam.trenchPlayers[loc].getPlayerClass());
					}
				}
			}
		}
	}
}
