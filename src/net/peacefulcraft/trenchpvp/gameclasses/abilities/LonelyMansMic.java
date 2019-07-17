package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class LonelyMansMic extends TrenchAbility
{
	private TrenchKit k;
	public LonelyMansMic(TrenchKit k)
	{
		super(k.getTrenchPlayer(), 15000);
		
		this.k=k;
	}
	
	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.LEVER)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Lonely Mans Mic"))) { return false; }
		
		return true;
	}
	
	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		Location loc = p.getLocation();
		
		List<Entity> list = p.getNearbyEntities(4, 4, 4);
		for(Entity e : list) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				Location t = vic.getLocation().subtract(loc);
				double distance = vic.getLocation().distance(loc);
				
				t.getDirection().normalize().multiply(-1);
				t.multiply(distance/1.2);
				t.multiply(2); //Change
				
				vic.setVelocity(t.toVector());
			}
		}
	}

}
