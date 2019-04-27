package net.peacefulcraft.trenchpvp.stats;

import net.peacefulcraft.trenchpvp.stats.StatTracker.TrenchStat;

public class TrenchStats {
	public TrenchStats() {
		
	}
	public enum GeneralStat implements TrenchStat {
		player_bread_eaten,
		player_health_potion_drank, player_kills, player_damage,
		player_highest_kill_streak, player_highest_round_kill_count,
		player_friendly_kills
	}
	public enum DemoStat implements TrenchStat {
		demoman_kills, demoman_daamage_dealt, demoman_bethas_placed 
	}
	public enum HeavyStat implements TrenchStat {
		heavy_kills, heavy_damage_dealt, heavy_dense_axe_kills, heavy_armadillo_usage
	}
	public enum MedicStat implements TrenchStat {
		medic_kills, medic_damage_dealt, medic_damage_healed, medic_syringe_gun_kills
	}
	public enum PyroStat implements TrenchStat {
		pyro_kills, pyro_damage_dealt, pyro_inferno_axe_kills, pyro_traps_placed
	}
	public enum ScoutStat implements TrenchStat {
		scout_kills, scout_damage_dealt, scout_slim_slicer_kills, scout_sling_shot_kills
	}
	public enum SniperStat implements TrenchStat {
		sniper_kills, sniper_damage_dealt, 
		sniper_component_rifle_kills, sniper_power_shot_upgrades,
		sniper_poison_upgrades, sniper_kukiri_kills
	}
	public enum SoldierStat implements TrenchStat {
		soldier_kills, soldier_damage_dealt, soldier_deep_cut_kills, soldier_highest_onslaught
	}
	public enum SpyStat implements TrenchStat {
		spy_kills, spy_damage_dealt, spy_hidden_blade_kills, spy_speed_shot_usage, spy_kills_while_invisible
	}
}



