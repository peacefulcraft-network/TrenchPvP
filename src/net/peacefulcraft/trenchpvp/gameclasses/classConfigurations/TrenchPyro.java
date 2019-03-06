package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class TrenchPyro extends TrenchPlayer{
	
	public TrenchPyro(Player userIn, TrenchTeam team){
		super(userIn, team, TrenchClass.PYRO);
	}
	
	public void configure() {
		// TODO Auto-generated method stub
		
	}
}
