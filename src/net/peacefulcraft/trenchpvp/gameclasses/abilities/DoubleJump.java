package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class DoubleJump extends TrenchAbility{

	private TrenchKit k;
	private boolean canDoubleJump = true;;
	
	public DoubleJump(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
	
		if(ev instanceof PlayerMoveEvent || ev instanceof PlayerToggleFlightEvent) { return true;}
		
		return true;
		
	}

	@Override
	public void triggerAbility(Event ev) {
		
		if(canDoubleJump && ev instanceof PlayerToggleFlightEvent) {
			Player p = ((PlayerToggleFlightEvent)ev).getPlayer();
			Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0,2,0));
		    if(!b.getType().equals(Material.AIR)){
		     	Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
		       	Vector forward = p.getLocation().getDirection().multiply(0.3);
		        Vector jump = p.getLocation().getDirection().multiply(0.05).setY(1);
		        v.add(forward).add(jump);
		        p.setVelocity(v);     
		        canDoubleJump = false;
			}
			return;
		}
		
		if(!canDoubleJump && ev instanceof PlayerMoveEvent) {
			Player p = ((PlayerMoveEvent) ev).getPlayer();
			if(p.isOnGround()) {
				canDoubleJump = true;
			}
			return;
		}
		
	}
	
	
	
}
