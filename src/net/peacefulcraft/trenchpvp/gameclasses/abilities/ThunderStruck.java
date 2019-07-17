package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class ThunderStruck extends TrenchAbility
{
	private TrenchKit k;
	
	public ThunderStruck(TrenchKit k) {
		super(k.getTrenchPlayer(), 0);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Thunderstruck"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		try {
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ev;
			
			if(e.getEntity() instanceof Player) {
				Player victim = (Player) e.getEntity();
				Random rand = new Random();
				if(rand.nextInt(10) == 1) {
					victim.getWorld().strikeLightning(victim.getLocation());
				}
			}
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Triggering " + this.getClass() + " evnet. Incompatible event loop " + ev.getClass());
			return;
		}
	}
}
