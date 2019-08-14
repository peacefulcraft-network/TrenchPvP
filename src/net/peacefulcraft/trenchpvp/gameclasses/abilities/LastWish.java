package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class LastWish extends TrenchAbility
{
	private TrenchKit k;
	
	public LastWish(TrenchKit k) {
		super(k.getTrenchPlayer(), 0, "Last Wish");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{	
		try {
		
			PlayerDeathEvent e = (PlayerDeathEvent) ev;
			TrenchPlayer killed = TeamManager.findTrenchPlayer(e.getEntity());
			if(!(killed.getKitType() == TrenchKits.DEMOMAN)) return false;
			
			if(!(e.getEntity().getInventory().contains(Material.FIREWORK_ROCKET))) return false;
			
			return true;
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return false;
		}
	}

	@Override
	public void triggerAbility(Event ev)
	{
		try {
			PlayerDeathEvent e = (PlayerDeathEvent) ev;
			TrenchPlayer killed = TeamManager.findTrenchPlayer(e.getEntity());
			
			if(killed == k.getTrenchPlayer()) {
				Location loc = e.getEntity().getLocation();
				
				loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2.0f, false, false);
			}
			
		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return;
		}
	}
}
