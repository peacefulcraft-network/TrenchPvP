package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
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
				
				TrenchPvP.getStatTracker().track(t, GeneralStat.player_damage, damage);
				
				if(kit == TrenchKits.DEMOMAN) {
					TrenchPvP.getStatTracker().track(t, DemoStat.demoman_damage_dealt, damage);
				} else if(kit == TrenchKits.HEAVY) {
					TrenchPvP.getStatTracker().track(t, HeavyStat.heavy_damage_dealt, damage);
				} else if(kit == TrenchKits.MEDIC) {
					TrenchPvP.getStatTracker().track(t, MedicStat.medic_damage_dealt, damage);
				} else if(kit == TrenchKits.PYRO) {
					TrenchPvP.getStatTracker().track(t, PyroStat.pyro_damage_dealt, damage);
				} else if(kit == TrenchKits.SCOUT) {
					TrenchPvP.getStatTracker().track(t, ScoutStat.scout_damage_dealt, damage);
				} else if(kit == TrenchKits.SNIPER) {
					TrenchPvP.getStatTracker().track(t, SniperStat.sniper_damage_dealt, damage);
				} else if(kit == TrenchKits.SOLDIER) {
					TrenchPvP.getStatTracker().track(t, SoldierStat.soldier_damage_dealt, damage);
				} else if(kit == TrenchKits.SPY) {
					TrenchPvP.getStatTracker().track(t, SpyStat.spy_damage_dealt, damage);
				}
			}
		}
	}
}
