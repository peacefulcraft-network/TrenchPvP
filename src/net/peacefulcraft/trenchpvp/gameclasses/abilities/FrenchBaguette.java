package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class FrenchBaguette extends TrenchAbility
{
	private TrenchKit k;
	private final int EFFECT_TIME = 180;
	
	public FrenchBaguette(TrenchKit k) {
		super(k.getTrenchPlayer(), 0, "French Baguette");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		try {
			PlayerDeathEvent e = (PlayerDeathEvent) ev;
			TrenchPlayer trenchKiller = TeamManager.findTrenchPlayer(e.getEntity().getKiller());
			
			if(!(trenchKiller.getKitType() == TrenchKits.DUOLINGO_BIRD)) { return false; }
			
			Player p = trenchKiller.getPlayer();
			
			if(!(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD)) { return false; }
			if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("French Baguette"))) { return false; }
			
			return true;
 		}catch(ClassCastException ex) {
			TrenchPvP.logWarning("Error Matching Onsal event. Incompatible event loop " + ev.getClass());
			return false;
		}
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, EFFECT_TIME, 2));
	}
}
