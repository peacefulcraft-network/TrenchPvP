package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

public interface TrenchKitInventory {

	/**
	 * Equip hot bar items to the player
	 */
	public void equipItems();
	
	/**
	 * Equip player armor
	 */
	public void equipArmor();
	
	/**
	 * Kit indetification
	 */
	public TrenchKits getKitType();
	
}
