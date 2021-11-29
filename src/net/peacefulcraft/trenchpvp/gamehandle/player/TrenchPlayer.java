package net.peacefulcraft.trenchpvp.gamehandle.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchUndefined;

public class TrenchPlayer{
	private Player p;
		public Player getPlayer() {return this.p;}
	
	private TrenchTeams team;
		public TrenchTeams getPlayerTeam() { return this.team; }
	
	protected TrenchKit kitInstance;
		public TrenchKit getKit() { return kitInstance; }
		public TrenchKits getKitType() { return kitInstance.getKitType(); }

	/**
	 * Determines if player is able to open menu / log out safely 
	 */
	private Boolean combatLogged;
		public Boolean isCombatLogged() { return this.combatLogged; }
		public void setCombatLogged(Boolean b) { this.combatLogged = b; }
		
	public TrenchPlayer(Player user, TrenchTeams team){
		this.p = user;
		this.team = team;
		kitInstance = new TrenchUndefined(this);
	}
	
	public void equipKit(TrenchKit k) {
		
		dequipKits();
		clearPotionEffects();
		this.kitInstance = k;
		k.initConfig();
		k.equip();
		p.setGameMode(GameMode.ADVENTURE);
	}
	
	public void dequipKits() {		
		clearPotionEffects();
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		kitInstance.dinitConfig();
		
		this.kitInstance = new TrenchUndefined(this);
	}
	
	public void clearPotionEffects() {
		for(PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}
}
