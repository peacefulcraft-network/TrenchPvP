package net.peacefulcraft.trenchpvp.gamehandle.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchUndefined;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchArena;

public class TrenchPlayer{
	private Player p;
		public Player getPlayer() {return this.p;}
	
	private TrenchTeam team;
		public TrenchTeam getPlayerTeam() { return this.team; }
	
	protected TrenchKit kitInstance;
		public TrenchKit getKit() { return kitInstance; }
		public TrenchKits getKitType() { return kitInstance.getKitType(); }
	
	private TrenchArena arena;
		public TrenchArena getArena() { return arena; }
		public void setArena(TrenchArena arena) { this.arena = arena; }
		
	private String partyName;
		public String getPartyName() { return partyName; }
		public void setPartyName(String name) {
			this.partyName = name;
			//TODO: update file
		}
	private String lastInvite;
		public String getLastInvite() { return lastInvite; }
		public void setLastInvite(String invite) {
			this.lastInvite = invite;
			//TODO: update file
		}
		
	public TrenchPlayer(Player user, TrenchArena arena, TrenchTeam team){
		this.p = user;
		this.arena = arena;
		this.team = team;
		kitInstance = new TrenchUndefined(this);
		
		partyName = "";
		lastInvite = "";
	}
	
	public Boolean isInParty() {
		if(partyName.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public void equipKit(TrenchKit k) {
		dequipKits();
		clearPotionEffects();
		this.kitInstance = k;
		k.equip();
		p.setGameMode(GameMode.ADVENTURE);
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
		p.setFlying(false);
		p.setAllowFlight(false);
		this.kitInstance = new TrenchUndefined(this);
	}
	
	public void clearPotionEffects() {
		for(PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}
}
