package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.SpyStat;

public class SpeedShot extends TrenchAbility{

	private TrenchKit k;
	
	public SpeedShot(TrenchKit k) {
		super(k.getTrenchPlayer(), 16000, "Speed Shot");
		 
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev) {
		
		try {
			
			PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) ev;
			Player p = e.getPlayer();
			//Checks item in main hand is 
			if(!(p.getInventory().getItemInMainHand().getType() == Material.CHORUS_FLOWER)) { return false; }
			if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Speed Shot"))) { return false; }
			
			return true;
		
		}catch(ClassCastException ex) {
			
			TrenchPvP.logWarning("Error Matching SpeedShot event. Incompatible event loop " + ev.getClass());
			return false;
		}
			
	}

	@Override
	public void triggerAbility(Event ev) {
		
		try {
			
			PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) ev;
			
			TrenchPlayer spy, target;
			spy = TrenchPvP.getTrenchManager().findTrenchPlayer(e.getPlayer());
			target = TrenchPvP.getTrenchManager().findTrenchPlayer((Player)e.getRightClicked());
			
			if(!(spy.getPlayerTeam() == target.getPlayerTeam())) return;
			target.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 140, 4));
			e.getPlayer().sendMessage(ChatColor.RED + "Ability is now on cooldown for 16 seconds.");
			
			TrenchPvP.getStatTracker().track(spy.getPlayer().getUniqueId(), SpyStat.spy_speed_shot_usage, 1);
			
		}catch(ClassCastException ex) {
			
			TrenchPvP.logWarning("Error Matching SpeedShot event. Incompatible event loop " + ev.getClass());
			return;
			
		}
		
	}

}
