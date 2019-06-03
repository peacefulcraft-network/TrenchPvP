package net.peacefulcraft.trenchpvp.gameclasses.abilities;

public interface TrenchKitClickAbilityExecutor {
	
	/**
	 * Add a TrenchAbility to the TrenchKit load out
	 * @param t: The TrenchAbility
	 * @throws IllegalStateException: Thrown if ability is already registered to this kit
	 */
	public void registerAbility(TrenchAbility t) throws IllegalStateException;
	
	/**
	 * Remove a TrenchAbility from the TrenchKit load out
	 * @param abilityClassName: Name of the TrenchAbility.class (String) to remove
	 * @throws IllegalStateException: Thrown if ability is not registered
	 */
	public void unregisterAbility(String abilityClassName) throws IllegalStateException;
	
	/**
	 * Loop through all registered abilities and attempt to trigger them 
	 */
	public void executeClickAbilities();
	
}
