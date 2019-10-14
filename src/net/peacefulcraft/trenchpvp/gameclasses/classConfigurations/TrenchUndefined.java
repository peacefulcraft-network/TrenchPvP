package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;

public class TrenchUndefined extends TrenchKit{
	
	public TrenchUndefined(TrenchPlayer t) {
		super(t, TrenchKits.UNASSIGNED);
	}

	@Override
	public void equipItems() {
		//Nothing to equip
		//Items unequiped by gamehandle.player.TrenchPlayer
	}

	@Override
	public void equipArmor() {
		//Nothing to equip
		//Items unequiped by gamehandle.player.TrenchPlayer
	}
}
