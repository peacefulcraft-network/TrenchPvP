package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class SacraficialCannon extends TrenchAbility
{
	private TrenchKit k;
	
	public SacraficialCannon(TrenchKit k) {
		super(k.getTrenchPlayer(), 20000, "Sacraficial Cannon");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.COOKED_PORKCHOP)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Sacraficial Cannon"))) { return false; }
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);
		if(lookingBlock == null || !(lookingBlock.getType().isBlock())) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);
		Location loc = lookingBlock.getLocation().add(0, 1, 0);
		
		Random rand = new Random();
		int add = rand.nextInt(2);
		ArrayList<Pig> pigs = new ArrayList<Pig>();
		
		for(int i = 0; i<=(5+add); i++) {
			Pig pig = (Pig)p.getWorld().spawnEntity(loc, EntityType.PIG);
			pig.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 3));
			pigs.add(pig);
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
			public void run() {
				for(Pig pig : pigs) {
					Location loc = pig.getLocation();
					loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 3.0f, false, false);
					pig.setHealth(0);
				}
			}
		}, 60);
	}
}
