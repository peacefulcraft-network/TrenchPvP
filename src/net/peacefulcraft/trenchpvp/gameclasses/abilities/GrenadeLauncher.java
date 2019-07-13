package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class GrenadeLauncher extends TrenchAbility{
	
	private TrenchKit k;
	
	public GrenadeLauncher(TrenchKit k) {
		super(k.getTrenchPlayer(), 1000, "Grenade Launcer");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		Player p = k.getTrenchPlayer().getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.QUARTZ)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Grenade Launcher"))) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
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
