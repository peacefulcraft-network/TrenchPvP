package net.peacefulcraft.trenchpvp.gamehandle.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchUndefined;

public class TrenchPlayer{
	private Player p;
		public Player getPlayer() {return this.p;}
	
	private TrenchTeams team;
		public TrenchTeams getPlayerTeam() { return this.team; }
	
	protected TrenchKit kitInstance;
		public TrenchKit getKit() { return kitInstance; }
		public TrenchKits getKitType() { return kitInstance.getKitType(); }
		
	public TrenchPlayer(Player user, TrenchTeams team){
		this.p = user;
		this.team = team;
		kitInstance = new TrenchUndefined(this);
	}
	
	public void equipKit(TrenchKit k) {
		dequipKits();
		clearPotionEffects();
		this.kitInstance = k;
		k.equip();
	}
	
	public void dequipKits() {
		//Dirty way to remove all inferno traps
		//TODO: Standardize dequip tasks for all Kits
		if(kitInstance instanceof TrenchPyro) {
			((TrenchPyro) kitInstance).infernoTrapRemover.removeInfernoTraps();
		}
		
		clearPotionEffects();
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		this.kitInstance = new TrenchUndefined(this);
	}
	
	public void clearPotionEffects() {
		for(PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}
}
