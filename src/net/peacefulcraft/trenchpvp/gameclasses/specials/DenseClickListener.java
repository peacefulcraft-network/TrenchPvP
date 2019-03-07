package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.specials.DenseAxe;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class DenseClickListener implements Listener 
{
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		//Checks item in main hand is Dense Axe
		if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dense Axe"))) return;
		
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.HEAVY)) return;
		
		DenseAxe axe = new DenseAxe();
		axe.updateClick();
		//Check for cooldown time 8 seconds
		if(!(System.currentTimeMillis() >= (axe.getClick() + 8000))) return;
		//Potion effects
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 2));
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 3));
		
		
	}

}

