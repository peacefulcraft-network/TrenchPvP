package net.peacefulcraft.parsonswy.trenchpvp.gameclasses.classConfigurations;

import org.bukkit.entity.Player;

import net.peacefulcraft.parsonswy.trenchpvp.gameclasses.TrenchClass;
import net.peacefulcraft.parsonswy.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.parsonswy.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchSpy extends TrenchPlayer{
	
	public TrenchSpy(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.SPY);
	}
	public void configure() {
		// TODO Auto-generated method stub
		
	}
}
