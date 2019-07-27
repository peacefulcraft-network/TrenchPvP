package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class CommunityManagerDumbFoot extends TrenchAbility
{
	private TrenchKit k;
	
	public CommunityManagerDumbFoot(TrenchKit k)
	{
		super(k.getTrenchPlayer(), 0, "Community Manager's Dumb Foot");
		
		this.k=k;
	}
	@Override
	public boolean abilitySignature(Event ev)
	{
		try {
			
			PlayerMoveEvent e = (PlayerMoveEvent) ev;
			TrenchPlayer t = TeamManager.findTrenchPlayer(e.getPlayer());
			
		}catch(ClassCastException ex) {
			
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return false;
			
		}
		
		return true;
	}
	@Override
	public void triggerAbility(Event ev)
	{
		try {
			PlayerMoveEvent e = (PlayerMoveEvent) ev;
			Player p = e.getPlayer();
			
			Random rand = new Random();
			if(rand.nextInt(100) <= 1) {
				Location loc = p.getLocation();
				loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 1.0f, false, false);
			}
			
		}catch(ClassCastException ex) {
			
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return;
		}
	}

}
