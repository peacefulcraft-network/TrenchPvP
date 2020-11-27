package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

/**
 * TrenchAbility
 * Common utility functions for TrenchClass Abilities
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
		
		(new CooldownMessage(3)).runTaskTimer(TrenchPvP.getPluginInstance(), 0L, 20L);
		return false;
	}
	
		/**
		 * Actionbar messages only display for 1 second so we have to send the same thing
		 * once a second for 3 seconds to make the message appear for 3 seconds.
		 * Because we are resending, it will update with the new cooldown time each second
		 */
		private class CooldownMessage extends BukkitRunnable {
			
			private int seconds;
			
			public CooldownMessage(int seconds) {
				this.seconds = seconds;
			}
			
			@Override
			public void run() {
				
				//Make sure users has not disconnected
				if(t == null || t.getPlayer() == null) {
					this.cancel();
					return;
				}
				
				//Make sure cooldown has not expired
				long timeRem = (cooldown - System.currentTimeMillis())/1000;
				if(timeRem < 0) {
					this.cancel();
					return;
				}
				
				//Construct Text object to send
				BaseComponent base = new TextComponent(abilityCooldownMessage(timeRem));
				base.setColor(ChatColor.RED);
				t.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, base);
				
				//Decriment and see if this needs to run again
				seconds--;
				if(seconds < 1)
					this.cancel();
				
			}
			
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

