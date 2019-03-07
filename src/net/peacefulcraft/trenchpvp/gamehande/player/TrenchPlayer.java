package net.peacefulcraft.trenchpvp.gamehande.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;

public class TrenchPlayer{
	private Player user;
		public Player getPlayer() {return this.user;}
	
	private TrenchTeam team;
		public TrenchTeam getPlayerTeam() {return this.team;}
	
	protected TrenchKits kitType;
		public TrenchKits getKitType() {return kitType;}
	
	public TrenchPlayer(Player user, TrenchTeam team){
		this.user = user;
		this.team = team;
		this.kitType = TrenchKits.UNASSIGNED;
	}
	
	public void equipKit(TrenchKit k) {
		this.kitType = k.getKitType();
		k.equip(this);
	}
	
}
