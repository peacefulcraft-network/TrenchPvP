package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.PyroStat;

public class InfernoTrapPlacer extends TrenchAbility{

	private TrenchPyro k;
	
	public InfernoTrapPlacer(TrenchPyro k) {
		super(k.getTrenchPlayer(), -1);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Trap"))) { return false; } 
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);//Gets block within 4 block range
		 if (lookingBlock != null && lookingBlock.getType().isBlock() && lookingBlock.getType() != Material.LIGHT_WEIGHTED_PRESSURE_PLATE && lookingBlock.getType() != Material.AIR) {
            Block upBlock = lookingBlock.getRelative(BlockFace.UP);
            if (upBlock != null && upBlock.getType() == Material.AIR && upBlock.getType() != Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
           	 int itemIndex = p.getInventory().first(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
           	 if(itemIndex >= 0) {
           		 ItemStack trap = p.getInventory().getItem(itemIndex);
           		 
           		 TrenchPvP.getStatTracker().track(p.getUniqueId(), PyroStat.pyro_traps_placed, 1);
           		 
           		 if(trap.getAmount() <= 1) {
           			 upBlock.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);//"Places" trap
           			 //Adds location of trap to ArrayList HashMap
           			 k.getInfernoTraps().add(upBlock.getLocation());
           			 p.getInventory().clear(itemIndex);//Clears item slot
           			 p.sendMessage(ChatColor.RED + "Out of Inferno Traps! Time to Detonate!");
           		 } else {
           			 upBlock.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
           			 //Adds location of trap to ArrayList HashMap
           			 k.getInfernoTraps().add(upBlock.getLocation());
           			 trap.setAmount(trap.getAmount() - 1);
           		 }
           	 }
           	 
            }
        }
		
	}
	
}
