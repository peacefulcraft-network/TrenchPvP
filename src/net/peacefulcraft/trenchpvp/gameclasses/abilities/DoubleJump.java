package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

/**
 * Trench Ability - Scout
 * Triggers on toggle of flight ("double click") - User must have .allowFlight(true) for this ability to work.
 * Watches for flight toggles so double press of space can be detected, intercepts the event, cancels flight, and then redirects the users vector
 */
public class DoubleJump extends TrenchAbility{

	private TrenchKit k;
	
	//Used to track if user has already used the second jump. Needs to land on ground to "recharge"
	private boolean canDoubleJump = true;;
	
	public DoubleJump(TrenchKit k) {
		super(k.getTrenchPlayer(), -1, "Double Jump");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
	
		if(ev instanceof PlayerMoveEvent || ev instanceof PlayerToggleFlightEvent) { return true;}
		
		return false;
		
	}

	
	/**
	 * Registered for PlayerToggleFlightEvent loop & PlayerMoveEvent loop
	 */
	@Override
	public void triggerAbility(Event ev) {
	
		//Check if player is on ground; reset double jump tracking if they are.
		if(!canDoubleJump && ev instanceof PlayerMoveEvent) {
			Player p = ((PlayerMoveEvent) ev).getPlayer();
			if(p.isOnGround()) {
				canDoubleJump = true;
				p.setAllowFlight(true);
			}
			return;
		}
		
		//Make sure the player doesn't actually start flying
		if(ev instanceof PlayerToggleFlightEvent) {
			((PlayerToggleFlightEvent) ev).getPlayer().setFlying(false);
			((PlayerToggleFlightEvent) ev).setCancelled(true);
			System.out.println("Canceling double jump.");
		}
		
		//Double jump logic
		if(canDoubleJump && ev instanceof PlayerToggleFlightEvent) {
			System.out.println("Vector 1");
			Player p = ((PlayerToggleFlightEvent)ev).getPlayer();
			Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0,2,0));
			
			System.out.println("Vector 2");
			//"Boost" their movement vector to simulate a double jump
			 Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
			Vector forward = p.getLocation().getDirection().multiply(0.3);
			Vector jump = p.getLocation().getDirection().multiply(0.05).setY(1);
			v.add(forward).add(jump);
			p.setVelocity(v);

			//Disable flight / jump until they touch the ground.
			canDoubleJump = false;
			p.setAllowFlight(false);
			return;
		}	
	}
}