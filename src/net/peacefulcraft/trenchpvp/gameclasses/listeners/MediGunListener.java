package net.peacefulcraft.trenchpvp.gameclasses.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
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

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehande.TeamManager;
import net.peacefulcraft.trenchpvp.gamehande.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.stats.TrenchStats.MedicStat;

public class MediGunListener implements Listener {
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();//Creating cooldown
	private final int COOLDOWN_TIME = 3;
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		//Check if clicked player
		if(!(e.getRightClicked() instanceof Player)) return;

		//Check if clicked with medi gun item
		if(!(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.REDSTONE_BLOCK)) return;
		if(!(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "Medi Gun")) return;

		TrenchPlayer healer = TeamManager.findTrenchPlayer(e.getPlayer());
		TrenchPlayer healTarget = TeamManager.findTrenchPlayer((Player) e.getRightClicked());
		if(healer == null || healTarget == null) { return; }
		
		
		//Check if sender is medic
		if(!(healer.getKitType() == TrenchKits.MEDIC)) return;
		//Check on same team
		if(!(healer.getPlayerTeam() == healTarget.getPlayerTeam())) return;
		
		if(cooldown.containsKey(p.getUniqueId()))
		{
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true)
			{
				fireMediGun(p, e);
				p.sendMessage(ChatColor.RED + "Ability is now on cooldown for " + COOLDOWN_TIME + " seconds.");
			}
			else if(canUseAgain(p) == false)
			{
				p.sendMessage(ChatColor.RED + "Ability is on cooldown for " + timeLeft + " seconds!");
			}
		}
		else
		{
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			fireMediGun(p, e);
		}
		
	}
	private void fireMediGun(Player p, PlayerInteractEntityEvent e) {
		//Snowball projectile. Possibly remove b/c garbage
		Location tLoc = e.getRightClicked().getLocation();
		Vector vec = new Vector(tLoc.getX(), tLoc.getY(), tLoc.getZ());
		e.getPlayer().launchProjectile(Snowball.class, vec);	
		
		//Give target player health, 1 heart UNLESS full health, then over heal 2 hearts
		Damageable patient = (Damageable) e.getRightClicked();
		if((patient.getHealth() + 2) > 20){
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 16), true);
			TrenchPvP.getStatTracker().track(p.getUniqueId(), MedicStat.medic_damage_healed, 4);
		}else{
			patient.setHealth((patient.getHealth() + 2));
			TrenchPvP.getStatTracker().track(p.getUniqueId(), MedicStat.medic_damage_healed, 2);
		}
		
	}
	public boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
}
