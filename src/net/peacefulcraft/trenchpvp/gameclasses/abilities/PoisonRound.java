package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitScheduler;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SniperStat;

public class PoisonRound extends TrenchAbility{
	
	private TrenchKit k;
	
	public PoisonRound(TrenchKit k) {
		super(k.getTrenchPlayer(), 12000);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature() {
		Player p = k.getTrenchPlayer().getPlayer();
		if(!(p.getInventory().getItemInMainHand().getType() == Material.ARROW)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Bullets"))) return false;
		
		return canUseAbility();
	}

	@Override
	public void triggerAbility() {
		Player p = k.getTrenchPlayer().getPlayer();
		
		int itemIndex = p.getInventory().first(Material.ARROW);
		if(itemIndex >= 0) {
			ItemStack arrows = p.getInventory().getItem(itemIndex);
			int arrowAmount = arrows.getAmount();
			
			ItemStack tipped = new ItemStack(Material.TIPPED_ARROW, arrowAmount);
			PotionMeta pMeta = (PotionMeta) tipped.getItemMeta();
			pMeta.setBasePotionData(new PotionData(PotionType.POISON));//Creating tipped arrows
			pMeta.setDisplayName("Poison Bullets");
			tipped.setItemMeta(pMeta);
			
			p.getInventory().setItem(itemIndex, tipped);
			
			TrenchPvP.getStatTracker().track(p.getUniqueId(), SniperStat.sniper_poison_upgrades, 1);
			
			//Delay arrow return switch
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(TrenchPvP.getPluginInstance() , new Runnable() {
                //@Override
                public void run() {
                	ItemStack t = p.getInventory().getItem(itemIndex);
                	arrows.setAmount(t.getAmount());
                	p.getInventory().setItem(itemIndex, arrows);
                }
            }, 100);
		}
		
	}

}