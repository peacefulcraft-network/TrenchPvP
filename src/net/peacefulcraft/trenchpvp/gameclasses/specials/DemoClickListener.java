package net.peacefulcraft.trenchpvp.gamehande.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.peacefulcraft.trenchpvp.gameclasses.TrenchClass;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class DemoClickListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player user = e.getPlayer();
		if(!(user.getItemInHand().getType() == Material.NETHER_BRICK)) return;
		if(!(user.getItemInHand().getItemMeta().getDisplayName().equals("Grenade Launcher"))) return;
		
		int loc = TrenchPlayer.findTrenchPlayer(user);
		if(loc == -1) return;
		
		TrenchPlayer player = TrenchTeam.trenchPlayers[loc];
		if(!(player.getPlayerClass() == TrenchClass.DEMOMAN)) return;
		
		
	}
}
