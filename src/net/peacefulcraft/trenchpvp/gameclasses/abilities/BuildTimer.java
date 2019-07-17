package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class BuildTimer extends TrenchAbility
{
	private TrenchKit k;
	private final int EFFECT_TIME = 400;
	
	public BuildTimer(TrenchKit k)
	{
		super(k.getTrenchPlayer(), 20000);
		
		this.k=k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
	
		if(!(p.getInventory().getItemInMainHand().getType() == Material.CLOCK)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Build Timer"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		ArrayList<Player> lightning = new ArrayList<Player>();
		List<Entity> list = p.getNearbyEntities(4, 4, 4);
		for(Entity e : list) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				vic.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, EFFECT_TIME, 2));
				vic.addPotionEffect(new PotionEffect(PotionEffectType.POISON, EFFECT_TIME, 2));
				vic.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, EFFECT_TIME, 2));
				lightning.add(vic);
			}
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
			public void run() {
				for(Player v : lightning) {
					Location loc = v.getLocation();
					loc.getWorld().strikeLightning(loc);
					if(v.getHealth() != 0) {
						v.setHealth(v.getHealth() + 5);
					}
				}
			}
		}, 100);
		
		
	}

}
