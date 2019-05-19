package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class FlamethrowerListener implements Listener
{
	private int fireChance = 5;
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private final int COOLDOWN_TIME = 1;
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		UUID id = p.getUniqueId();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Flamethrower"))) return;
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		if(!(t.getKitType() == TrenchKits.PYRO)) return;
		
		if(cooldown.containsKey(id)) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				useFlamethrower(p);
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(id, System.currentTimeMillis());
			useFlamethrower(p);
			p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
		}
	}
	public boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	private void useFlamethrower(Player p)
	{
		shootFlames(p);//Add checks here
	}
	private void shootFlames(Player p) {

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
        
        if (Math.random() < fireChance) { // Light fire to block one fifth of the time
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
