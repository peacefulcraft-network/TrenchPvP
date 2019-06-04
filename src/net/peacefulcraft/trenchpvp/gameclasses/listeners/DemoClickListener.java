package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class DemoClickListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.NETHER_BRICK)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Grenade Launcher"))) return;
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		if(!(t.getKitType() == TrenchKits.DEMOMAN)) return;
		
	}
}
