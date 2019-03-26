package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;


public class GrenadeLauncherListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.QUARTZ)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Grenade Launcher"))) return;
		TrenchPlayer t;
		try {
		
			t = TeamManager.findTrenchPlayer(p);
		
		}catch(RuntimeException x) {
			return;
		}
		if(!(t.getKitType() == TrenchKits.DEMOMAN)) return;
		
		int itemIndex = p.getInventory().first(Material.FIREWORK_ROCKET);
		if(itemIndex >= 0) {
			ItemStack grenades = p.getInventory().getItem(itemIndex);
			if(grenades.getAmount() <= 1) {
				useLauncher(p);
				p.getInventory().clear(itemIndex);
				p.sendMessage(ChatColor.RED + "Out of Grenades!");
			} else {
				useLauncher(p);
				grenades.setAmount(grenades.getAmount()-1);
			}
		}
	}
	private void useLauncher(Player p) {
		Fireball f = p.launchProjectile(Fireball.class);
		f.setVelocity(p.getLocation().getDirection().multiply(2));
		f.setIsIncendiary(false);
		f.setGravity(true);
		f.setYield(2);
	}
}


