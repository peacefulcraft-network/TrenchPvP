package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

public interface TrenchClassConfiguration {

	/**
	 * Player configuration that needs changed when the class is equiped
	 */
	public void initConfig();
	
	/**
	 * Player configuration that needs changed when the class is dequiped
	 */
	public void dinitConfig();
	
}
