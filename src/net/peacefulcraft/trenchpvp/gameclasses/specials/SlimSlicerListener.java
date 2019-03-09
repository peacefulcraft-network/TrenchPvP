package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class SlimSlicerListener implements Listener
{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.SCOUT)) return;
		
		if((p.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD))
		{
			if((p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Slim Slicer")))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 3));
			}
		}
		else
		{
			p.removePotionEffect(PotionEffectType.FAST_DIGGING);
		}
		
	}        
}
