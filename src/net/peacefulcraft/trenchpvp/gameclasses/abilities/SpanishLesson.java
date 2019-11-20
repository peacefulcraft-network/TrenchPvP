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

public class SpanishLesson extends TrenchAbility
{
	private TrenchKit k;
	private final int EFFECT_TIME = 200;
	
	public SpanishLesson(TrenchKit k) {
		super(k.getTrenchPlayer(), 25000, "Spanish Lesson");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.BOOK)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Spanish Lesson"))) { return false; }
		
		return true;	
	}

	@Override
	public void triggerAbility(Event ev)
	{
		TrenchPlayer t = k.getTrenchPlayer();
		Player p = t.getPlayer();
		
		List<Entity> list = p.getNearbyEntities(8, 8, 8);
		
		for(Entity e : list) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				if(TeamManager.findTrenchPlayer(vic).getPlayerTeam() != t.getPlayerTeam()) {
					vic.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, EFFECT_TIME, 6));
					vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, EFFECT_TIME, 2));
				} else if(TeamManager.findTrenchPlayer(vic).getPlayerTeam() == t.getPlayerTeam()) {
					vic.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, EFFECT_TIME/2, 1));
				}
			}
		}
	}
}
