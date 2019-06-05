package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SniperStat;

public class PowerShot extends TrenchAbility{

	private TrenchKit k;
	
	public PowerShot(TrenchKit k) {
		super(k.getTrenchPlayer(), 15000);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature() {
		Player p = k.getTrenchPlayer().getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.PISTON)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Power Shot"))) return false;
		
		return canUseAbility();
	}

	@Override
	public void triggerAbility() {
		
		markAbilityUsed();
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		int itemIndex = p.getInventory().first(Material.BOW);
		if(itemIndex >= 0) {
			ItemStack rifle = p.getInventory().getItem(itemIndex);
			
			ItemStack shotR = new ItemStack(Material.BOW);
			shotR.addEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
			shotR.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
			
			ItemMeta meta = shotR.getItemMeta();
			meta.setDisplayName("Component Rifle Mk.IV");
			shotR.setItemMeta(meta);
			
			p.getInventory().setItem(itemIndex, shotR);
			
			TrenchPvP.getStatTracker().track(p.getUniqueId(), SniperStat.sniper_power_shot_upgrades, 1);
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
                //@Override
                public void run() {
                	p.getInventory().setItem(itemIndex, rifle);
                }
            }, 100);
		}
		
	}

}
