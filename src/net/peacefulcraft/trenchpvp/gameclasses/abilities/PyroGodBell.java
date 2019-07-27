package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;

public class PyroGodBell extends TrenchAbility
{
	private TrenchKit k;
	private PyroGodBellWalk walkEnforcer;
	
	private final int EFFECT_TIME = 400;
	
	public PyroGodBell(TrenchKit k, PyroGodBellWalk walkEnforcer) {
		super(k.getTrenchPlayer(), 35000, "Bell of The Pyro God");
		
		this.k = k;
		this.walkEnforcer = walkEnforcer;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.BELL)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Bell of The Pyro God"))) { return false; }
		
		PlayerInteractEvent e = (PlayerInteractEvent) ev;
		if(!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		int primaryIndex = p.getInventory().first(Material.BELL);
		ItemStack primary = p.getInventory().getItem(primaryIndex);
		
		ItemStack copy = primary;
		ItemMeta copyM = copy.getItemMeta();
		copyM.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
		copy.setItemMeta(copyM);
		
		p.getInventory().setItem(primaryIndex, copy);
		
		walkEnforcer.enableEnforce();
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TrenchPvP.getPluginInstance(), new Runnable() {
			public void run() {
				if(k.getTrenchPlayer().getKitType() == TrenchKits.ELEMENTALIST) {
					p.getInventory().setItem(primaryIndex, primary);
				} else {
					return;
				}
			}
		}, EFFECT_TIME / 20);
	}
}
