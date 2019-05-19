package net.peacefulcraft.trenchpvp.gameclasses.classConfigurations;

import org.bukkit.entity.Player;

import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public abstract class TrenchKit {
	
	protected TrenchKits kitType;
		public TrenchKits getKitType() {return kitType;}
	
	public void equip(TrenchPlayer t) {
		
		equipPrimary(t.getPlayer());
		equipSecondary(t.getPlayer());
		equipGenerics(t.getPlayer());
		equipArmor(t.getPlayer());
		t.getPlayer().updateInventory();
		
	}
	
	protected abstract void equipPrimary(Player p);
	
	protected abstract void equipSecondary(Player p);

	protected abstract void equipGenerics(Player p);
	
	protected abstract void equipArmor(Player p);
	
}
