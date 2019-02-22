package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gameclasses.TrenchClass;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchSpy extends TrenchPlayer{
	
	public TrenchSpy(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.SPY);
	}
	public void configure() {
		// TODO Auto-generated method stub
		
	}
}
