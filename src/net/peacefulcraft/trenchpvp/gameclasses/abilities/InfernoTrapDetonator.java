package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;

public class InfernoTrapDetonator extends TrenchAbility{

	private TrenchPyro k;
	
	public InfernoTrapDetonator(TrenchPyro k) {
		super(k.getTrenchPlayer(), -1, "Inferno Trap");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Trap Detonator"))) { return false; } 
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev) {
		
		//Iterates through ArrayList to detonate each trap.
		for(Location temp : k.getInfernoTraps()) {
			if(temp.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
				temp.getWorld().createExplosion(temp.getX(), temp.getY(), temp.getZ(), 2.0f, true, false);
				temp.getBlock().setType(Material.AIR);
			}
		}
		Player p = k.getTrenchPlayer().getPlayer();
		if(p.getInventory().contains(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			int itemIndex = p.getInventory().first(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
			p.getInventory().getItem(itemIndex).setAmount(5);
		} else if(p.getInventory().getItemInOffHand().getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
			p.getInventory().getItemInOffHand().setAmount(5);
		} else {
			ItemStack trapAmmo = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 5);
			ItemMeta tAMeta = trapAmmo.getItemMeta();
			tAMeta.setDisplayName("Inferno Trap");

			ArrayList<String> aDesc = new ArrayList<String>();
			aDesc.add("Can Only Place 5 at a Time! Detonate to Get More!");
			tAMeta.setLore(aDesc);

			trapAmmo.setItemMeta(tAMeta);
			p.getInventory().addItem(trapAmmo);
		}
		
		k.resetInfernoTraps();
		
	}

}
