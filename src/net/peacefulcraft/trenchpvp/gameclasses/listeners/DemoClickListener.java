package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeams;

public class DemoClickListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(!(p.getItemInHand().getType() == Material.NETHER_BRICK)) return;
		if(!(p.getItemInHand().getItemMeta().getDisplayName().equals("Grenade Launcher"))) return;
		
		try {
		
			TrenchPlayer player = TeamManager.findTrenchPlayer(p);
			if(!(player.getKitType() == TrenchKits.DEMOMAN)) return;
		
		}catch(RuntimeException x) {
			return;
		}
		
	}
}
