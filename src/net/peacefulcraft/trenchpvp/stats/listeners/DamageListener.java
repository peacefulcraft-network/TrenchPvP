package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.StatTracker;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.DemoStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.GeneralStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.HeavyStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.MedicStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.PyroStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.ScoutStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SniperStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SoldierStat;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SpyStat;

public class DamageListener implements Listener
{
	StatTracker s = new StatTracker();
	@EventHandler
	private void onDamageEvent(EntityDamageByEntityEvent e) {
		Entity agent = e.getDamager();
		Entity damageTaker = e.getEntity();

		if(damageTaker instanceof Player) {
			if(agent instanceof Player) {
				Player damager = (Player) agent;
				
				TrenchPlayer t;
				try {
					t = TeamManager.findTrenchPlayer(damager);
				} catch(RuntimeException x) {
					return;
				}
				TrenchKits kit = t.getKitType();
				int damage = (int) e.getDamage();
				
				s.track(damager.getUniqueId(), GeneralStat.TotalDamage, damage);
				
				if(kit == TrenchKits.DEMOMAN) {
					s.track(damager.getUniqueId(), DemoStat.DamageDealt, damage);
				} else if(kit == TrenchKits.HEAVY) {
					s.track(damager.getUniqueId(), HeavyStat.DamageDealt, damage);
				} else if(kit == TrenchKits.MEDIC) {
					s.track(damager.getUniqueId(), MedicStat.DamageDealt, damage);
				} else if(kit == TrenchKits.PYRO) {
					s.track(damager.getUniqueId(), PyroStat.DamageDealt, damage);
				} else if(kit == TrenchKits.SCOUT) {
					s.track(damager.getUniqueId(), ScoutStat.DamageDealt, damage);
				} else if(kit == TrenchKits.SNIPER) {
					s.track(damager.getUniqueId(), SniperStat.DamageDealt, damage);
				} else if(kit == TrenchKits.SOLDIER) {
					s.track(damager.getUniqueId(), SoldierStat.DamageDealt, damage);
				} else if(kit == TrenchKits.SPY) {
					s.track(damager.getUniqueId(), SpyStat.DamageDealt, damage);
				}
			}
		}
	}
}
