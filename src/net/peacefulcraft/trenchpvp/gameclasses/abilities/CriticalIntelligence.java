package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSpy;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class CriticalIntelligence extends TrenchAbility
{
	private TrenchSpy k;
	
	public CriticalIntelligence(TrenchSpy k) {
		super(k.getTrenchPlayer(), 5000, "Critical Intelligence");
		
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) ev;
		if(!(e.getRightClicked() instanceof Player)) return false;
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.PAPER)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Critical Intelligence"))) return false;	
		
		TrenchPlayer target = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) e.getRightClicked());
		if(target == null) { return false; }
		if(k.getTrenchPlayer().getPlayerTeam() == target.getPlayerTeam()) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) ev;
		TrenchPlayer target = TrenchPvP.getTrenchManager().findTrenchPlayer((Player) e.getRightClicked());
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(k.getIntelligence().contains(target)) {
			p.sendMessage(ChatColor.RED + "Intelligence on Enemy Has Already Been Collected! Try Another Target!");
		} else {
			int itemIndex = p.getInventory().first(Material.PAPER);
			
			ItemStack paper = p.getInventory().getItem(itemIndex);
			if(paper.getAmount() >= 10) {
				p.sendMessage(ChatColor.RED + "Cannot Carry Any More Intelligence! It's Time To Strike!");
			} else {
				k.getIntelligence().add(target);
				paper.setAmount(paper.getAmount() + 1);
			}
		} 
	}
}
