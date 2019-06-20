package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.event.Event;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
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
	
	private String abilityName;
		public String getAbilityName() {return abilityName; }
		
	/**
	 * Set the cooldown time, in ms, between clicks
	 * @param delay
	 */
	public TrenchAbility(TrenchPlayer t, long delay, String abilityName) {
		this.t= t;
		cooldowndelay = delay;
		this.abilityName = abilityName;
	}
	
	/**
	 * Check if ability is on cooldown, alert player if it is on cooldown.
	 * @return T/F can use ability
	 */
	public boolean canUseAbility() {
		if(canUseAbilitySilent()) {
			return true;
		}
		
		BaseComponent base = new TextComponent(abilityCooldownMessage((cooldown - System.currentTimeMillis())/1000));
		base.setColor(ChatColor.RED);
		t.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, base);
		//t.getPlayer().sendMessage(abilityCooldownMessage((cooldown - System.currentTimeMillis())/1000));
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
	 * @param sourceEvent
	 */
	public void execute(Event ev) {
		markAbilityUsed();
		triggerAbility(ev);	
	}
	
	/**
	 * Game conditions that must be met to trigger ability. Clicks, location, item in inventory, etc
	 * @return T/F can trigger ability
	 */
	public abstract boolean abilitySignature(Event ev);
	
	/**
	 * Execute ability action.
	 */
	public abstract void triggerAbility(Event ev);
	
	/**
	 * 
	 * @param timeRemainer: How many seconds are left on the cooldown
	 * @return Message to send user when plugin is on cooldown
	 */
	public String abilityCooldownMessage(long timeRemaining) {
		return Announcer.getTrenchPrefix() + abilityName + " is on cooldown for " + timeRemaining + " seconds.";
	}
	
}

