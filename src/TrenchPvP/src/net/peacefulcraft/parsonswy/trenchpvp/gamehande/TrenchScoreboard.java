package net.peacefulcraft.parsonswy.trenchpvp.gamehande;

import net.peacefulcraft.parsonswy.trenchpvp.*;

public class TrenchScoreboard{
	private TrenchPvP pluginInstance;
	
	public TrenchScoreboard(){
		pluginInstance = TrenchPvP.getPluginInstance();
	}
	public void setBlue(String username){
		pluginInstance.getServer().dispatchCommand(pluginInstance.getServer().getConsoleSender(),"scoreboard teams join Blue " + username);
	}
	
	public void unsetBlue(String username){
		pluginInstance.getServer().dispatchCommand(pluginInstance.getServer().getConsoleSender(),"scoreboard teams leave Blue " + username);
	}
	
	public void setRed(String username){
		pluginInstance.getServer().dispatchCommand(pluginInstance.getServer().getConsoleSender(),"scoreboard teams join Red " + username);
	}
	
	public void unsetRed(String username){
		pluginInstance.getServer().dispatchCommand(pluginInstance.getServer().getConsoleSender(),"scoreboard teams leave Red " + username);
	}
}
