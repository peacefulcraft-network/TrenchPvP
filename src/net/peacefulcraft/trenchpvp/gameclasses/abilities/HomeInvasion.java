package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class HomeInvasion extends TrenchAbility
{
	private TrenchKit k;
	
	public HomeInvasion(TrenchKit k) {
		super(k.getTrenchPlayer(), 20000, "Home Invasion");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.FEATHER)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Home Invasion"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		TrenchPlayer t = k.getTrenchPlayer();
		Player p = t.getPlayer();
		Location loc = p.getLocation();
		
		List<Entity> list = p.getNearbyEntities(12, 12, 12);
		
		for(Entity e : list) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				if(TrenchPvP.getTrenchManager().findTrenchPlayer(vic).getPlayerTeam() != t.getPlayerTeam()) {
					if(t.getKitType() != TrenchKits.DUOLINGO_BIRD) { return; }
					
					
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
						public void run() {
							vic.sendMessage(ChatColor.RED + "Oh God... Oh F***");
							p.teleport(vic.getLocation());
						}
					}, 40);
				}
			}
		}
		p.teleport(loc);
	}
}
