package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class Flamethrower extends TrenchAbility {

	private TrenchKit k;
	
	public Flamethrower(TrenchKit k) {
		super(k.getTrenchPlayer(), 1000);

		this.k = k;
	}
	
	@Override
	public boolean abilitySignature(Event ev) {
		Player p = k.getTrenchPlayer().getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Flamethrower"))) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		Vector playerDirection = p.getLocation().getDirection();
        Vector particleVector = playerDirection.clone();
        
		Vector fireballVector = playerDirection.clone();
		        
	        Fireball f = p.launchProjectile(Fireball.class);
	        f.setVelocity(fireballVector.multiply(2));//Possibly remove because garbage
	        f.setIsIncendiary(true);
	        f.setYield(0);
		        

        playerDirection.multiply(8); // Set length to 8 blocks out

        // rotate particle location 90 degrees
        double x = particleVector.getX();
        particleVector.setX(-particleVector.getZ());
        particleVector.setZ(x);
        particleVector.divide(new Vector(3, 3, 3)); // Divide it by 2 to shorten length

        Location particleLocation = particleVector.toLocation(p.getWorld()).add(p.getLocation()).add(0, 1.05, 0);

        for (int i = 0; i < 5; i++) { //Amount of flames shot. Change here.
            shootSingleFlame(p, playerDirection, particleLocation);
        }

        //int fireTimer = 4000;
        
        if (Math.random() < 5) { // Light fire to block one fifth of the time
            Block lookingBlock = p.getTargetBlock((Set<Material>) null, 15); // Get target block in 15 block range
            if (lookingBlock != null && lookingBlock.getType().isBlock()) {
                Block upBlock = lookingBlock.getRelative(BlockFace.UP);
                if (upBlock != null && upBlock.getType() == Material.AIR) {
                	upBlock.setType(Material.FIRE);
                }
            }
        }
        
	}
	
		private void shootSingleFlame(Player p, Vector playerDirection, Location particleLocation) {
	        Vector particlePath = playerDirection.clone(); // clone to prevent extra math calculations
	
	        particlePath.add(new Vector(Math.random() - Math.random(), Math.random() - Math.random(), Math.random() - Math.random())); // Add some randomness
	
	        Location offsetLocation = particlePath.toLocation(p.getWorld());
	
	        p.getWorld().spawnParticle(Particle.FLAME, particleLocation, 0, offsetLocation.getX(), offsetLocation.getY(), offsetLocation.getZ(), 0.1); // Count of zero, to respect 'speed'
	    }

}
