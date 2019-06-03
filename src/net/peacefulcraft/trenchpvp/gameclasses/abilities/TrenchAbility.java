package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

/**
 * TrenchAbility
 * Common utility functions for TrenchClass Abilities
 * @author wyatt
 *
 */
public abstract class TrenchAbility {
	
	//Time between allowed clicks
	protected TrenchPlayer t;
		public TrenchPlayer getTrenchPlayer() { return t; }
	private long cooldowndelay;
	
	//Next time ability can be used
	private long cooldown = 0L;
		public long getCooldown() { return cooldown; }
		public long getCooldownSeconds() { return cooldown / 1000;}
		public void setCooldown(Long delay) { cooldown = delay; }
	
	/**
	 * Set the cooldown time, in ms, between clicks
	 * @param delay
	 */
	public TrenchAbility(TrenchPlayer t, long delay) {
		this.t= t;
		cooldowndelay = delay;
	}
	
	/**
	 * Check if ability is on cooldown, alert player if it is on cooldown.
	 * @return T/F can use ability
	 */
	public boolean canUseAbility() {
		if(canUseAbilitySilent()) {
			return true;
		}
		
		t.getPlayer().sendMessage(abilityCooldownMessage((cooldown - System.currentTimeMillis())/1000));
		return false;
	}
	
	/**
	 * Check if ability is on cooldown without alerting player
	 * @return T/F can use ability
	 */
	public boolean canUseAbilitySilent() {
		if(cooldown < System.currentTimeMillis()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Trigger cooldown for the ability
	 */
	public void markAbilityUsed() {
		cooldown = System.currentTimeMillis() + cooldowndelay;
	}
	
	/**
	 * Check if ability signature matches, then trigger the ability
	 */
	public void executeAbility() {
		if(abilitySignature() && canUseAbility()) {
			markAbilityUsed();
			triggerAbility();	
		}
	}
	
	/**
	 * Game conditions that must be met to trigger ability. Clicks, location, item in inventory, etc
	 * @return T/F can trigger ability
	 */
	public abstract boolean abilitySignature();
	
	/**
	 * Execute ability action.
	 */
	public abstract void triggerAbility();
	
	/**
	 * 
	 * @param timeRemainer: How many seconds are left on the cooldown
	 * @return Message to send user when plugin is on cooldown
	 */
	public String abilityCooldownMessage(long timeRemaining) {
		return Announcer.getTrenchPrefix() + " " + getClass() + " is on cooldown for " + timeRemaining;
	}
	
}

