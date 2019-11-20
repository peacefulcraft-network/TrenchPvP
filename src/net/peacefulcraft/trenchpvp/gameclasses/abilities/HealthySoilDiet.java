package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class HealthySoilDiet extends TrenchAbility
{
	private TrenchKit k;
	
	public HealthySoilDiet(TrenchKit k) {
		super(k.getTrenchPlayer(), -1, "Healthy Soil Diet");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		try {
			
			PlayerMoveEvent e = (PlayerMoveEvent) ev;
			TrenchPlayer t = TeamManager.findTrenchPlayer(e.getPlayer());
			
		} catch(ClassCastException ex) {
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
			Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
			
			if(b.getType().equals(Material.DIRT) || b.getType().equals(Material.GRASS_BLOCK) || b.getType().equals(Material.COARSE_DIRT) || b.getType().equals(Material.PODZOL)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 1));
			} else {
				p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			}
			
		} catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Matching" + this.getClass() + " event. Incompatible event loop " + ev.getClass());
			return;
		}
	}
}
