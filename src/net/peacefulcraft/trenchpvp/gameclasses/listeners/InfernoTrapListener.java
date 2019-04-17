package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.PyroStat;

public class InfernoTrapListener implements Listener
{
	HashMap<UUID,ArrayList<Location>> trapCord = new HashMap<UUID,ArrayList<Location>>(); //HashMap for UUID and Location ArrayList
	ArrayList<Location> traps = new ArrayList<Location>(); //Creates ArrayList to contain Location of traps.
	@EventHandler
	public void trapRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Trap"))) return;
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.PYRO)) return;
		
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);//Gets block within 4 block range
		 if (lookingBlock != null && lookingBlock.getType().isBlock() && lookingBlock.getType() != Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
             Block upBlock = lookingBlock.getRelative(BlockFace.UP);
             if (upBlock != null && upBlock.getType() == Material.AIR) {
            	 int itemIndex = p.getInventory().first(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
            	 if(itemIndex >= 0) {
            		 ItemStack trap = p.getInventory().getItem(itemIndex);
            		 trapCord.put(p.getUniqueId(), traps);
            		 
            		 StatTracker s = new StatTracker();//Handling stat tracking
            		 s.track(p.getUniqueId(), PyroStat.InfernoTrapsPlaced, 1);
            		 
            		 if(trap.getAmount() <= 1) {
            			 upBlock.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);//"Places" trap
            			 //Adds location of trap to ArrayList HashMap
            			 trapCord.get(p.getUniqueId()).add(upBlock.getLocation());
            			 p.getInventory().clear(itemIndex);//Clears item slot
            			 p.sendMessage(ChatColor.RED + "Out of Inferno Traps! Time to Detonate!");
            		 } else {
            			 upBlock.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
            			 //Adds location of trap to ArrayList HashMap
            			 trapCord.get(p.getUniqueId()).add(upBlock.getLocation());
            			 trap.setAmount(trap.getAmount() - 1);
            		 }
            	 }
             }
         }
	}
	@EventHandler
	public void detonateRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK)) return;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Trap Detonator"))) return;
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(p);
		}catch(RuntimeException x) {
			return;
		}
		
		if(!(t.getKitType() == TrenchKits.PYRO)) return;
		
		if(trapCord.containsKey(p.getUniqueId())) {
			int itemIndex = p.getInventory().first(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);//Gets amount of traps and slot.
			ItemStack trap = p.getInventory().getItem(itemIndex);
			trap.setAmount(5);//Sets traps to 5 after each detonation
			
			ArrayList<Location> traps = trapCord.get(p.getUniqueId());
			//Iterates through ArrayList to detonate each trap.
			for(Location temp : traps) {
				if(temp.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
					temp.getWorld().createExplosion(temp.getX(), temp.getY(), temp.getZ(), 2.0f, true, false);
					temp.getBlock().setType(Material.AIR);
				}
			}
		}
	}
}
