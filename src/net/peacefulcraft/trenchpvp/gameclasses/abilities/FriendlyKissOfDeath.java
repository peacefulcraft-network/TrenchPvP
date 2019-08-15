package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSpy;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class FriendlyKissOfDeath extends TrenchAbility
{
	private TrenchSpy k;
	
	public FriendlyKissOfDeath(TrenchSpy k) {
		super(k.getTrenchPlayer(), 60000, "Friendly Kiss Of Death");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.WITHER_ROSE)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Friendly Kiss of Death"))) { return false; }
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		TrenchPlayer t = k.getTrenchPlayer();
		Player p = t.getPlayer();
		
		List<Entity> list = p.getNearbyEntities(9, 9, 9);

		for(Entity e : list) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				if(TeamManager.findTrenchPlayer(vic).getPlayerTeam() != t.getPlayerTeam()) {

					int index = p.getInventory().first(Material.PAPER);
					int multiplier = (p.getInventory().getItem(index).getAmount() / 2) + 1;
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, multiplier));
					
					p.teleport(vic.getLocation());
					p.getInventory().getItem(index).setAmount(1);//Sets amount of intelligence back
					k.resetIntelligence();
					return;
				}
			}
		}
	}
}
