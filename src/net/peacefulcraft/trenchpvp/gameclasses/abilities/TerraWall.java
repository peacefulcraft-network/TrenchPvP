package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class TerraWall extends TrenchAbility
{
	private TrenchKit k;
	
	public TerraWall(TrenchKit k) {
		super(k.getTrenchPlayer(), 25000, "Terra Wall");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();

		if(!(p.getInventory().getItemInMainHand().getType() == Material.EMERALD_ORE)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Terra Wall"))) { return false; }
		if(p.getTargetBlock((Set<Material>) null, 4).getType().equals(Material.AIR)) {return false;}
		
		return true;	
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		Block lookingBlock = p.getTargetBlock((Set<Material>) null, 4);
		Block upBlock = lookingBlock.getRelative(BlockFace.UP);
		if(upBlock != null && upBlock.getType().equals(Material.AIR)) {
			for(int x=1; x <= 3; x++) {
				//TODO: Things
			}
		}
	}
}
