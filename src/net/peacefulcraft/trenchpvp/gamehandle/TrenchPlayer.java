package net.peacefulcraft.trenchpvp.gamehandle;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchAdrenalineJunkie;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchDemoman;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchDuolingoBird;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchElementalist;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchHeavy;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchJuniorCommunityManager;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchMedic;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchNthEntity;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPigKing;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchScout;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSniper;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSoldier;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSpy;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchUndefined;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchWitchDoctor;
import net.peacefulcraft.trenchpvp.gamehandle.arena.TrenchArena;

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
		
		// Equip the compass
		this.kitInstance = new TrenchUndefined(this);
		dequipKits();
		
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
	
	public void equipKit(TrenchKits k) {
		dequipKits();
		clearPotionEffects();

		switch(k){
			case SCOUT:

				this.kitInstance = new TrenchScout(this);
				p.setAllowFlight(true);
				
			break;case SOLDIER:
				
				this.kitInstance = new TrenchSoldier(this);
				
			break;case PYRO:
					
				this.kitInstance = new TrenchPyro(this);
				
			break;case DEMOMAN:
				
				this.kitInstance = new TrenchDemoman(this);
				
			break;case HEAVY:
				
				this.kitInstance = new TrenchHeavy(this);
			
			break;case SNIPER:
			
				this.kitInstance = new TrenchSniper(this);
				
			break;case MEDIC:
				
				this.kitInstance = new TrenchMedic(this);
			
			break;case SPY:
				
				this.kitInstance = new TrenchSpy(this);
			
			break;case ADRENALINE_JUNKIE:

				this.kitInstance = new TrenchAdrenalineJunkie(this);

			break;case DUOLINGO_BIRD:

				this.kitInstance = new TrenchDuolingoBird(this);

			break;case ELEMENTALIST:
			
				this.kitInstance = new TrenchElementalist(this);

			break;case JUNIOR_COMMUNITY_MANAGER:

				this.kitInstance = new TrenchJuniorCommunityManager(this);

			break;case NTH_ENTITY:
			
				this.kitInstance = new TrenchNthEntity(this);

			break;case PIG_KING:
			
				this.kitInstance = new TrenchPigKing(this);

			break;case UNASSIGNED:

				this.kitInstance = new TrenchUndefined(this);

			break;case WITCH_DOCTOR:

				this.kitInstance = new TrenchWitchDoctor(this);

			break;
		}

		this.kitInstance.initConfig();
		this.kitInstance.equip();
		p.setGameMode(GameMode.ADVENTURE);
	}
	
	public void dequipKits() {		
		clearPotionEffects();
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		kitInstance.dinitConfig();
		
		this.kitInstance = new TrenchUndefined(this);
		kitInstance.equip();
	}
	
	public void clearPotionEffects() {
		for(PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}
}
