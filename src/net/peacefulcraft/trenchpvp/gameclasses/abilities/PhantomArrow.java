package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class PhantomArrow implements Listener
{
	@EventHandler
	private void ArrowReturn(EntityShootBowEvent e) {
		if(e.getEntity() instanceof Player) {
			TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) e.getEntity()); 
			if(t == null) { return; }
			
			if(t.getKitType() != TrenchKits.SNIPER) { return; }
			
			Player p = t.getPlayer();
			Random rand = new Random();
			
			if(rand.nextInt(5) <= 2) {
				int index = p.getInventory().first(Material.ARROW);
				ItemStack arrow = p.getInventory().getItem(index);
				
				arrow.setAmount(arrow.getAmount() + 1);
			}
			
		}
	}
}
