package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class LaunchPadUse implements Listener
{
	@EventHandler
	private void LaunchPadLaunch(PlayerInteractEvent e) {
		if(e.getPlayer().getGameMode() != GameMode.ADVENTURE) { return; }
		
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
		if(t == null) { return; }
		
		if(t.getKitType() == TrenchKits.UNASSIGNED) { return; }
		if(!(e.getAction().equals(Action.PHYSICAL))) { return; }
		
		Player p = e.getPlayer();
		if(e.getClickedBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
			if(t.getKitType() == TrenchKits.ADRENALINE_JUNKIE) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 180, 4));
			}
			
			Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
			Vector forward = p.getLocation().getDirection().multiply(2.4);
			Vector jump = new Vector(0, 8, 0);
			v.add(forward).add(jump);
			
			p.setVelocity(v);
		}
		return;
	}
	
	@EventHandler
	private void PigLaunch(EntityInteractEvent e) {
		if(!(e.getEntity() instanceof Pig)) { return; }
		if(!(e.getBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE))) { return; }
		
		Pig p = (Pig) e.getEntity();
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
		
		Vector v = new Vector(p.getVelocity().getX(), p.getVelocity().getY(), p.getVelocity().getZ());
		Vector forward = p.getLocation().getDirection().multiply(3.0);
		Vector jump = new Vector(0, 3, 0);
		v.add(forward).add(jump);
		
		p.setVelocity(v);
	}
}
