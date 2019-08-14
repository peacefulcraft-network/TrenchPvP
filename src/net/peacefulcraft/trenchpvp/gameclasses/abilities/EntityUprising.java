package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class EntityUprising extends TrenchAbility
{
	private TrenchKit k;
	
	private final int EFFECT_TIME = 200; //20 ticks per second
	
	public EntityUprising(TrenchKit k)
	{
		super(k.getTrenchPlayer(), 20000, "Entity Uprising");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.WHITE_STAINED_GLASS)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Entity Uprising"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		TrenchPlayer t = k.getTrenchPlayer();
		Player p = t.getPlayer();
		
		List<Entity> levList = p.getNearbyEntities(5, 5, 5);
		for(Entity e : levList) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				if(TeamManager.findTrenchPlayer(vic).getPlayerTeam() != t.getPlayerTeam()) {
					vic.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, EFFECT_TIME, 1));
					vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, EFFECT_TIME, 4));
				}
			}
		}
	}

}
