package net.peacefulcraft.trenchpvp.gamehandle.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;

public class TrenchPlayer{
	private Player user;
		public Player getPlayer() {return this.user;}
	
	private TrenchTeams team;
		public TrenchTeams getPlayerTeam() {return this.team;}
	
	protected TrenchKits kitType;
		public TrenchKits getKitType() {return kitType;}
	
	protected TrenchKit kitInstance;
		public TrenchKit getKit() { return kitInstance; }
		
	public TrenchPlayer(Player user, TrenchTeams team){
		this.user = user;
		this.team = team;
		this.kitType = TrenchKits.UNASSIGNED;
	}
	
	public void equipKit(TrenchKit k) {
		dequipKits();
		clearPotionEffects();
		this.kitInstance = k;
		this.kitType = k.getKitType();
		k.equip(this);
	}
	
	public void dequipKits() {
		clearPotionEffects();
		user.getInventory().clear();
		user.getInventory().setArmorContents(new ItemStack[user.getInventory().getArmorContents().length]);
	}
	
	public void clearPotionEffects() {
		for(PotionEffect effect : user.getActivePotionEffects()) {
			user.removePotionEffect(effect.getType());
		}
	}
}
