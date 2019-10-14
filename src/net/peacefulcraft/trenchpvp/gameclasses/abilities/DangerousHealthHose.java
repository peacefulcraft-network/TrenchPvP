package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class DangerousHealthHose extends TrenchAbility
{
	private TrenchKit k;
	
	public DangerousHealthHose(TrenchKit k) {
		super(k.getTrenchPlayer(), 30000, "Dangerous Health Hose");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.BEACON)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dangerous Health Hose"))) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		List<Entity> list = p.getNearbyEntities(7, 7, 7);
		for(Entity e : list) {
			if(e instanceof Player) {
				Player vic = (Player) e;
				if(TrenchPvP.getTrenchManager().findTrenchPlayer(vic).getPlayerTeam() != k.getTrenchPlayer().getPlayerTeam()) {
					if(vic.getHealth() - 5 <= 0) {
						vic.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 4));
					} else {
						vic.setHealth(vic.getHealth() - 5);
					}
					vic.sendMessage(ChatColor.DARK_RED + "You Feel Your Blood Being Drained By An Enemy Medic...");
				} else {
					if(vic.getHealth() + 10 >= 20) {
						vic.setHealth(20);
						vic.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 140, 3));
					} else {
						vic.setHealth(vic.getHealth() + 8);
						vic.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 2));
					}
					vic.sendMessage(ChatColor.DARK_RED + "You Feel Your Body Surge With Help From A Friendly Medic...");
				}
			}
		}
	}
}
