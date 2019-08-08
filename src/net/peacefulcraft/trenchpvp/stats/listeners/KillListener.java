package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;
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
		
		if( !(e.getEntity().getKiller() instanceof Player) ) { return; }
		
		Player killer = e.getEntity().getKiller();
		
		TrenchPlayer t = TeamManager.findTrenchPlayer(killer);
		if(t == null) {
			return;
		}
		TrenchKits kit = t.getKitType();
		
		TrenchPvP.getStatTracker().track(t, GeneralStat.player_kills, 1);
		
		if(t.getPlayerTeam() == TrenchTeams.BLUE) {
			TrenchPvP.getTeamManager().getScoreboard().registerBlueKill();
		}else {
			TrenchPvP.getTeamManager().getScoreboard().registerRedKill();
		}
		
		if(kit == TrenchKits.DEMOMAN) {
			demoKill(t);
		} else if(kit == TrenchKits.HEAVY) {
			heavyKill(t);
		} else if(kit == TrenchKits.MEDIC) {
			medicKill(t);
		} else if(kit == TrenchKits.PYRO) {
			pyroKill(t);
		} else if(kit == TrenchKits.SCOUT) {
			scoutKill(t);
		} else if(kit == TrenchKits.SNIPER) {
			sniperKill(t);
		} else if(kit == TrenchKits.SOLDIER) {
			soldierKill(t);
		} else if(kit == TrenchKits.SPY) {
			spyKill(t);
		}
	}
	private void demoKill(TrenchPlayer demoman) {
		TrenchPvP.getStatTracker().track(demoman, DemoStat.demoman_kills, 1);
	}
	private void heavyKill(TrenchPlayer heavy) {
		TrenchPvP.getStatTracker().track(heavy, HeavyStat.heavy_kills, 1);
		if(heavy.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_AXE) {
			if(heavy.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dense Axe")) {
				TrenchPvP.getStatTracker().track(heavy, HeavyStat.heavy_dusks_edge_kills, 1);
			}
		}
	}
	private void medicKill(TrenchPlayer medic) {
		TrenchPvP.getStatTracker().track(medic, MedicStat.medic_kills, 1);
		if(medic.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(medic.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Syringe Gun")) {
				TrenchPvP.getStatTracker().track(medic, MedicStat.medic_syringe_gun_kills, 1);
			}
		}
	}
	private void pyroKill(TrenchPlayer pyro) {
		TrenchPvP.getStatTracker().track(pyro, PyroStat.pyro_kills, 1);
		if(pyro.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE) {
			if(pyro.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Axe")) {
				TrenchPvP.getStatTracker().track(pyro, PyroStat.pyro_inferno_axe_kills, 1);
			}
		}
	}
	private void scoutKill(TrenchPlayer scout) {
		TrenchPvP.getStatTracker().track(scout, ScoutStat.scout_kills, 1);
		if(scout.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
			if(scout.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Slim Slicer")) {
				TrenchPvP.getStatTracker().track(scout, ScoutStat.scout_windsong_blade_kills, 1);
			}
		} else if(scout.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(scout.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Sling Shot")) {
				TrenchPvP.getStatTracker().track(scout, ScoutStat.scout_sling_shot_kills, 1);
			}
		}
	}
	private void sniperKill(TrenchPlayer sniper) {
		TrenchPvP.getStatTracker().track(sniper, SniperStat.sniper_kills, 1);
		if(sniper.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(sniper.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Component Rifle Mk.II")) {
				TrenchPvP.getStatTracker().track(sniper, SniperStat.sniper_component_rifle_kills, 1);
			} else if(sniper.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Component Rifle Mk.IV")) {
				TrenchPvP.getStatTracker().track(sniper, SniperStat.sniper_component_rifle_kills, 1);
			}
		} else if(sniper.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
			if(sniper.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Kukri")) {
				TrenchPvP.getStatTracker().track(sniper, SniperStat.sniper_kukiri_kills, 1);
			}
		}
	}
	private void soldierKill(TrenchPlayer soldier) {
		TrenchPvP.getStatTracker().track(soldier, SoldierStat.soldier_kills, 1);
		if(soldier.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) {
			if(soldier.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Deep Cut")) {
				TrenchPvP.getStatTracker().track(soldier, SoldierStat.soldier_witherbringer_kills, 1);
			}
		}
	}
	private void spyKill(TrenchPlayer spy) {
		TrenchPvP.getStatTracker().track(spy, SpyStat.spy_kills, 1);
		if(spy.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
			if(spy.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Hidden Blade")) {
				TrenchPvP.getStatTracker().track(spy, SpyStat.spy_hidden_blade_kills, 1);
			}
		}
		if(spy.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			TrenchPvP.getStatTracker().track(spy, SpyStat.spy_kills_while_invisible, 1);
		}
	}
}
