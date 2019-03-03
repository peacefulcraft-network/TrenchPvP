package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchSoldier extends TrenchPlayer{
	
	public TrenchSoldier(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.SOLDIER);
	}
	public void configure() {
		// TODO Auto-generated method stub
		
	}
}
