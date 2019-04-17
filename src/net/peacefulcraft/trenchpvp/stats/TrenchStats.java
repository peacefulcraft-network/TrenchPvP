package net.peacefulcraft.trenchpvp.stats;

import net.peacefulcraft.trenchpvp.stats.StatTracker.TrenchStat;

public class TrenchStats {
	public TrenchStats() {
		
	}
	public enum GeneralStat implements TrenchStat {
		BreadEaten,
		HealthPotionUsage, TotalKills, TotalDamage,
		HighestKillStreak, MostKills
	}
	public enum DemoStat implements TrenchStat {
		KillsInClass, DamageDealt, BerthasPlaced 
	}
	public enum HeavyStat implements TrenchStat {
		KillsInClass, DamageDealt, DenseAxeKills, ArmadilloShellUsage
	}
	public enum MedicStat implements TrenchStat {
		KillsInClass, DamageDealt, DamageHealed, SyringGunKills
	}
	public enum PyroStat implements TrenchStat {
		KillsInClass, DamageDealt, InfernoAxeKills, InfernoTrapsPlaced
	}
	public enum ScoutStat implements TrenchStat {
		KillsInClass, DamageDealt, SlimSlicerKills, SlingShotKills
	}
	public enum SniperStat implements TrenchStat {
		KillsInClass, DamageDealt, 
		ComponentRifleKills, PowerShotUpgrades,
		PoisonRoundUpgrades, KukiriKills
	}
	public enum SoldierStat implements TrenchStat {
		KillsInClass, DamageDealt, DeepCutKills, HighestOnslaughtPerRound
	}
	public enum SpyStat implements TrenchStat {
		KillsInClass, DamageDealt, HiddenBladeKills, SpeedShotUsage, InvisKills
	}
}



