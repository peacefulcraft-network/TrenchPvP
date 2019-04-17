package net.peacefulcraft.trenchpvp.stats.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

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
	StatTracker s = new StatTracker();
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
		
		s.track(killer.getUniqueId(), GeneralStat.TotalKills, 1);
		
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
		s.track(demoman.getUniqueId(), DemoStat.KillsInClass, 1);
	}
	private void heavyKill(Player heavy) {
		s.track(heavy.getUniqueId(), HeavyStat.KillsInClass, 1);
		if(heavy.getInventory().getItemInMainHand().getType() == Material.IRON_AXE) {
			if(heavy.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Dense Axe")) {
				s.track(heavy.getUniqueId(), HeavyStat.DenseAxeKills, 1);
			}
		}
	}
	private void medicKill(Player medic) {
		s.track(medic.getUniqueId(), MedicStat.KillsInClass, 1);
		if(medic.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(medic.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Syringe Gun")) {
				s.track(medic.getUniqueId(), MedicStat.SyringGunKills, 1);
			}
		}
	}
	private void pyroKill(Player pyro) {
		s.track(pyro.getUniqueId(), PyroStat.KillsInClass, 1);
		if(pyro.getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE) {
			if(pyro.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Inferno Axe")) {
				s.track(pyro.getUniqueId(), PyroStat.InfernoAxeKills, 1);
			}
		}
	}
	private void scoutKill(Player scout) {
		s.track(scout.getUniqueId(), ScoutStat.KillsInClass, 1);
		if(scout.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
			if(scout.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Slim Slicer")) {
				s.track(scout.getUniqueId(), ScoutStat.SlimSlicerKills, 1);
			}
		} else if(scout.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(scout.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Sling Shot")) {
				s.track(scout.getUniqueId(), ScoutStat.SlingShotKills, 1);
			}
		}
	}
	private void sniperKill(Player sniper) {
		s.track(sniper.getUniqueId(), SniperStat.KillsInClass, 1);
		if(sniper.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(sniper.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Component Rifle Mk.II")) {
				s.track(sniper.getUniqueId(), SniperStat.ComponentRifleKills, 1);
			} else if(sniper.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Component Rifle Mk.IV")) {
				s.track(sniper.getUniqueId(), SniperStat.ComponentRifleKills, 1);
			}
		} else if(sniper.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
			if(sniper.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Kukri")) {
				s.track(sniper.getUniqueId(), SniperStat.KukiriKills, 1);
			}
		}
	}
	private void soldierKill(Player soldier) {
		s.track(soldier.getUniqueId(), SoldierStat.KillsInClass, 1);
		if(soldier.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) {
			if(soldier.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Deep Cut")) {
				s.track(soldier.getUniqueId(), SoldierStat.DeepCutKills, 1);
			}
		}
	}
	private void spyKill(Player spy) {
		s.track(spy.getUniqueId(), SpyStat.KillsInClass, 1);
		if(spy.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
			if(spy.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Hidden Blade")) {
				s.track(spy.getUniqueId(), SpyStat.HiddenBladeKills, 1);
			}
		}
		if(spy.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			s.track(spy.getUniqueId(), SpyStat.InvisKills, 1);
		}
	}
}
