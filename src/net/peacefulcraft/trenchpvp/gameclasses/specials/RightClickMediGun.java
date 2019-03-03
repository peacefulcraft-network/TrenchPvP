package net.peacefulcraft.trenchpvp.gameclasses.specials;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchClass;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchMedic;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchTeam;

public class RightClickMediGun implements Listener {
	
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent e){
		//Check if clicked player
		if(!(e.getRightClicked() instanceof Player)) return;

		//Check if clicked with medi gun item
		if(!(e.getPlayer().getItemInHand().getType() == Material.REDSTONE_BLOCK)) return;
		if(!(e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == "Medi Gun")) return;

		//Get trenchplayer reference location in array
		int sendIndex = TrenchPlayer.findTrenchPlayer(e.getPlayer());
		int recIndex = TrenchPlayer.findTrenchPlayer((Player)e.getRightClicked());
		
		//Check to make sure both players in trench
		if(sendIndex == -1) return;
		if(recIndex ==  -1) return;

		//Get TrenchPlayer reference
		TrenchPlayer sender = TrenchTeam.trenchPlayers[sendIndex];
		TrenchPlayer receive = TrenchTeam.trenchPlayers[recIndex];
		
		//Check if sender is medic
		if(!(sender.getPlayerClass() == TrenchClass.MEDIC)) return;

		//Check on same team
		if(!(sender.getPlayerTeam() == receive.getPlayerTeam())) return;

		//MediGun medigun = ((TrenchMedic) sender).getMediGun();
		
		//TODO:BETTER SYSTEM
		//Check if not fired in last 30 ticks
		//if(!(MinecraftServer.currentTick >= medigun.getFire() + 30)) return;
		
		//Update last fire tick
		//medigun.updateFire();
		
		//Snowball projectile. Possibly remove b/c garbage
		Location tLoc = e.getRightClicked().getLocation();
		Vector vec = new Vector(tLoc.getX(), tLoc.getY(), tLoc.getZ());
		e.getPlayer().launchProjectile(Snowball.class, vec);	
		
		//Give target player health, 1 heart UNLESS full health, then over heal 2 hearts
		Damageable patient = (Damageable) e.getRightClicked();
		if((patient.getHealth() + 2) > 20){
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 16), true);
		}else{
			patient.setHealth((patient.getHealth() + 2));
		}
	}
}
