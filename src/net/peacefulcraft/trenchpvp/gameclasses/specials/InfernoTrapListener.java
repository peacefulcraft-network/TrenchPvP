package net.peacefulcraft.trenchpvp.gameclasses.specials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;

public class InfernoTrapListener implements Listener
{
	HashMap<UUID,ArrayList<Location>> trapCord = new HashMap<UUID,ArrayList<Location>>();
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
		
		ArrayList<Location> traps = new ArrayList<Location>();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);//Gets block within 4 block range
		 if (lookingBlock != null && lookingBlock.getType().isBlock() && lookingBlock.getType() != Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
             Block upBlock = lookingBlock.getRelative(BlockFace.UP);
             if (upBlock != null && upBlock.getType() == Material.AIR) {
            	 int itemIndex = p.getInventory().first(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
            	 if(itemIndex >= 0) {
            		 ItemStack trap = p.getInventory().getItem(itemIndex);
            		 if(trap.getAmount() <= 1) {
            			 upBlock.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
            			 
            			 traps.add(upBlock.getLocation());
            			 trapCord.put(p.getUniqueId(), traps); //Registers trap coords into hashmap
            			 
            			 p.getInventory().clear(itemIndex);//Clears item slot
            			 p.sendMessage(ChatColor.RED + "Out of Inferno Traps! Time to Detonate!");
            		 } else {
            			 upBlock.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
            			 
            			 traps.add(upBlock.getLocation());
            			 trapCord.put(p.getUniqueId(), traps);
            			 
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
			for(Map.Entry<UUID, ArrayList<Location>> entry : trapCord.entrySet()) {
				UUID key = entry.getKey();
				ArrayList<Location> traps = entry.getValue();
				
				if(key == p.getUniqueId()) {
					for(Location temp : traps) {
						if(temp.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
							temp.getWorld().createExplosion(temp.getX(), temp.getY(), temp.getZ(), 2.0f, true, false);
							temp.getBlock().setType(Material.AIR);
						}
					}
				}
			}
		}
	}
}
