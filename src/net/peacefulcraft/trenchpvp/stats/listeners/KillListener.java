package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

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

public class KillListener implements Listener
{
	@EventHandler
	private void onDeathEvent(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		TrenchPlayer t;
		try {
			t = TeamManager.findTrenchPlayer(killer);
		} catch(RuntimeException x) {
			return;
		}
		TrenchKits kit = t.getKitType();
		
		TrenchPvP.getStatTracker().track(killer.getUniqueId(), GeneralStat.player_kills, 1);
		
		if(kit == TrenchKits.DEMOMAN) {
			demoKill(killer);
		} else if(kit == TrenchKits.HEAVY) {
			heavyKill(killer);
		} else if(kit == TrenchKits.MEDIC) {
			medicKill(killer);
		} else if(kit == TrenchKits.PYRO) {
			pyroKill(killer);
		} else if(kit == TrenchKits.SCOUT) {
			scoutKill(killer);
		} else if(kit == TrenchKits.SNIPER) {
			sniperKill(killer);
		} else if(kit == TrenchKits.SOLDIER) {
			soldierKill(killer);
		} else if(kit == TrenchKits.SPY) {
			spyKill(killer);
		}
	}
	private void demoKill(Player demoman) {
		TrenchPvP.getStatTracker().track(demoman.getUniqueId(), DemoStat.demoman_kills, 1);
	}
	private void heavyKill(Player heavy) {
		TrenchPvP.getStatTracker().track(heavy.getUniqueId(), HeavyStat.heavy_kills, 1);
		if(heavy.getInventory().getItemInMainHand().getType() == Material.IRON_AXE) {
			if(heavy.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dense Axe")) {
				TrenchPvP.getStatTracker().track(heavy.getUniqueId(), HeavyStat.heavy_dense_axe_kills, 1);
			}
		}
	}
	private void medicKill(Player medic) {
		TrenchPvP.getStatTracker().track(medic.getUniqueId(), MedicStat.medic_kills, 1);
		if(medic.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(medic.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Syringe Gun")) {
				TrenchPvP.getStatTracker().track(medic.getUniqueId(), MedicStat.medic_syringe_gun_kills, 1);
			}
		}
	}
	private void pyroKill(Player pyro) {
		TrenchPvP.getStatTracker().track(pyro.getUniqueId(), PyroStat.pyro_kills, 1);
		if(pyro.getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE) {
			if(pyro.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Axe")) {
				TrenchPvP.getStatTracker().track(pyro.getUniqueId(), PyroStat.pyro_inferno_axe_kills, 1);
			}
		}
	}
	private void scoutKill(Player scout) {
		TrenchPvP.getStatTracker().track(scout.getUniqueId(), ScoutStat.scout_kills, 1);
		if(scout.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
			if(scout.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Slim Slicer")) {
				TrenchPvP.getStatTracker().track(scout.getUniqueId(), ScoutStat.scout_slim_slicer_kills, 1);
			}
		} else if(scout.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(scout.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Sling Shot")) {
				TrenchPvP.getStatTracker().track(scout.getUniqueId(), ScoutStat.scout_sling_shot_kills, 1);
			}
		}
	}
	private void sniperKill(Player sniper) {
		TrenchPvP.getStatTracker().track(sniper.getUniqueId(), SniperStat.sniper_kills, 1);
		if(sniper.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(sniper.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Component Rifle Mk.II")) {
				TrenchPvP.getStatTracker().track(sniper.getUniqueId(), SniperStat.sniper_component_rifle_kills, 1);
			} else if(sniper.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Component Rifle Mk.IV")) {
				TrenchPvP.getStatTracker().track(sniper.getUniqueId(), SniperStat.sniper_component_rifle_kills, 1);
			}
		} else if(sniper.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
			if(sniper.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Kukri")) {
				TrenchPvP.getStatTracker().track(sniper.getUniqueId(), SniperStat.sniper_kukiri_kills, 1);
			}
		}
	}
	private void soldierKill(Player soldier) {
		TrenchPvP.getStatTracker().track(soldier.getUniqueId(), SoldierStat.soldier_kills, 1);
		if(soldier.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) {
			if(soldier.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Deep Cut")) {
				TrenchPvP.getStatTracker().track(soldier.getUniqueId(), SoldierStat.soldier_deep_cut_kills, 1);
			}
		}
	}
	private void spyKill(Player spy) {
		TrenchPvP.getStatTracker().track(spy.getUniqueId(), SpyStat.spy_kills, 1);
		if(spy.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
			if(spy.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Hidden Blade")) {
				TrenchPvP.getStatTracker().track(spy.getUniqueId(), SpyStat.spy_hidden_blade_kills, 1);
			}
		}
		if(spy.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			TrenchPvP.getStatTracker().track(spy.getUniqueId(), SpyStat.spy_kills_while_invisible, 1);
		}
	}
}
