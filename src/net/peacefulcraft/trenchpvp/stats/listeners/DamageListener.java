package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
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
	@EventHandler
	private void onDamageEvent(EntityDamageByEntityEvent e) {
		Entity agent = e.getDamager();
		Entity damageTaker = e.getEntity();

		if(damageTaker instanceof Player) {
			if(agent instanceof Player) {
				Player damager = (Player) agent;
				
				TrenchPlayer t = TeamManager.findTrenchPlayer(damager);
				if(t == null) {
					return;
				}
				
				TrenchKits kit = t.getKitType();
				int damage = (int) e.getDamage();
				
				TrenchPvP.getStatTracker().track(damager.getUniqueId(), GeneralStat.player_damage, damage);
				
				if(kit == TrenchKits.DEMOMAN) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), DemoStat.demoman_daamage_dealt, damage);
				} else if(kit == TrenchKits.HEAVY) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), HeavyStat.heavy_damage_dealt, damage);
				} else if(kit == TrenchKits.MEDIC) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), MedicStat.medic_damage_dealt, damage);
				} else if(kit == TrenchKits.PYRO) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), PyroStat.pyro_damage_dealt, damage);
				} else if(kit == TrenchKits.SCOUT) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), ScoutStat.scout_damage_dealt, damage);
				} else if(kit == TrenchKits.SNIPER) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), SniperStat.sniper_damage_dealt, damage);
				} else if(kit == TrenchKits.SOLDIER) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), SoldierStat.soldier_damage_dealt, damage);
				} else if(kit == TrenchKits.SPY) {
					TrenchPvP.getStatTracker().track(damager.getUniqueId(), SpyStat.spy_damage_dealt, damage);
				}
			}
		}
	}
}
