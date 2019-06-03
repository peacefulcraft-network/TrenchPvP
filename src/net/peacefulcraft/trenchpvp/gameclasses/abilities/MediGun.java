package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.MedicStat;

public class MediGun extends TrenchAbility{

	private TrenchKit k;
	
	public MediGun(TrenchKit k){
		super(k.getTrenchPlayer(), 3000);
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature() {
		
		Player p = k.getTrenchPlayer().getPlayer();
		
		//Check that it's a relevant click event
		if(!(this.getSourceEvent() instanceof PlayerInteractEntityEvent)) { return false; }	
		PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) this.getSourceEvent();
		
		//Check if clicked player
		if(!(e.getRightClicked() instanceof Player)) return false;

		//Check if clicked with medi gun item
		if(!(p.getInventory().getItemInMainHand().getType() == Material.REDSTONE_BLOCK)) { return false; }
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "Medi Gun")) { return false; }
		
		TrenchPlayer healTarget = TeamManager.findTrenchPlayer((Player) e.getRightClicked());
		if(healTarget == null) { return false; }
		if(!(k.getTrenchPlayer().getPlayerTeam() == healTarget.getPlayerTeam())) { return false; }
		
		return canUseAbility();		
		
	}

	@Override
	public void triggerAbility() {
		
		//Requires a target so triggering offhand is not a good idea.
		if(!(this.getSourceEvent() instanceof PlayerInteractEntityEvent)) { return; }
		PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) this.getSourceEvent();
		
		Location tLoc = e.getRightClicked().getLocation();
		Vector vec = new Vector(tLoc.getX(), tLoc.getY(), tLoc.getZ());
		e.getPlayer().launchProjectile(Snowball.class, vec);	
		
		//Give target player health, 1 heart UNLESS full health, then over heal 2 hearts
		Damageable patient = (Damageable) e.getRightClicked();
		if((patient.getHealth() + 2) > 20){
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 16), true);
			TrenchPvP.getStatTracker().track(k.getTrenchPlayer().getPlayer().getUniqueId(), MedicStat.medic_damage_healed, 4);
		}else{
			patient.setHealth((patient.getHealth() + 2));
			TrenchPvP.getStatTracker().track(k.getTrenchPlayer().getPlayer().getUniqueId(), MedicStat.medic_damage_healed, 2);
		}
		
	}
	
}
