package net.peacefulcraft.trenchpvp.menu.listeners;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchDemoman;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchHeavy;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKits;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchMedic;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPyro;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchScout;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSniper;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSoldier;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchSpy;
import net.peacefulcraft.trenchpvp.gamehandle.GameManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;
import net.peacefulcraft.trenchpvp.menu.GameMenu;
import net.peacefulcraft.trenchpvp.menu.GameMenu.Row;
import net.peacefulcraft.trenchpvp.menu.GameMenu.onClick;

public class KitMenu implements Listener
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private final int COOLDOWN_TIME = 60;
	private GameMenu menu = new GameMenu("Kit Menu", 3, new onClick() {
	    @Override
	    public boolean click(Player p, GameMenu menu, Row row, int slot, ItemStack item) {
	        if(row.getRow() == 0 || row.getRow() == 2){
	            inventoryClick(p, item);
	        }
	        return true;
	    }
	});
	
	public KitMenu() {
		menu.addButton(menu.getRow(0), 0, new ItemStack(Material.LEATHER_BOOTS), "Scout", "Click to Equip The Scout Class!");
		menu.addButton(menu.getRow(2), 0, new ItemStack(Material.IRON_CHESTPLATE), "Soldier", "Click to Equip The Soldier Class!");
		menu.addButton(menu.getRow(0), 2, new ItemStack(Material.FIRE_CHARGE), "Pyro", "Click to Equip The Pyro Class!");
		menu.addButton(menu.getRow(2), 2, new ItemStack(Material.ARROW), "Sniper", "Click to Equip The Sniper Class!");
		menu.addButton(menu.getRow(0), 4, new ItemStack(Material.TNT), "Demoman", "Click to Equip The Demoman Class!");
		menu.addButton(menu.getRow(2), 4, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE), "Medic", "Click to Equip The Medic Class!");
		menu.addButton(menu.getRow(0), 6, new ItemStack(Material.ANVIL), "Heavy", "Click to Equip The Heavy Class!");
		menu.addButton(menu.getRow(2), 6, new ItemStack(Material.CLOCK), "Spy", "Click to Equip The Spy Class!");
		menu.addButton(menu.getRow(0), 8, new ItemStack(Material.RED_STAINED_GLASS_PANE), "Quit", "Click to Leave Trench!");
		menu.addButton(menu.getRow(2), 8, new ItemStack(Material.BLUE_STAINED_GLASS_PANE), "Purchased Classes", "Click to Access Classes Purchased Through The Store!");
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if(!(p.getInventory().getItemInMainHand().getType() == Material.EMERALD)) return;
		
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA +"Kit Menu"))) return;

		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }

		if(cooldown.containsKey(p.getUniqueId())) {
			long timeLeft = ((cooldown.get(p.getUniqueId())/1000) + COOLDOWN_TIME) - (System.currentTimeMillis()/1000);
			if(canUseAgain(p) == true) {
				
				menuOpen(p);
				
			} else if(canUseAgain(p) == false) {
				p.sendMessage(ChatColor.RED + "Can't open menu for " + timeLeft + " seconds!");
			}
		} else {
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			
			menuOpen(p);
			
		}
	}
	
	private boolean canUseAgain(Player player)
	{
		long lastTimeUsed = cooldown.get(player.getUniqueId());
		long timeToWait = TimeUnit.SECONDS.toMillis(COOLDOWN_TIME);
		return (System.currentTimeMillis() - lastTimeUsed) > timeToWait;
 	}
	
	public void menuOpen(Player p) {

		menu.open(p.getPlayer());
		
	}
	
	public void removeCooldown(Player p) {
		cooldown.remove(p.getUniqueId());
	}
	
	private void inventoryClick(Player p, ItemStack item) {
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		String itemText = item.getItemMeta().getDisplayName().toUpperCase();
		if(itemText.equals("QUIT")) {
			GameManager.quitPlayer(p);
			cooldown.remove(p.getUniqueId());
		}
		if(itemText.equals("Purchased Classes")) {
			PurchasedMenu purchasedMenu = new PurchasedMenu();
			menu.close(p);
			purchasedMenu.menuOpen(p);
		}
		
		switch(TrenchKits.valueOf(itemText)){//Check which class was selected (based on 3rd line of class sign)	
		case SCOUT:

			if(TrenchPvP.getTrenchCFG().isScoutEnabled()){
				
				t.equipKit(new TrenchScout(t));
				p.setAllowFlight(true);
				teleportByTeam(t);
				return;
			
			}else
			
			p.sendMessage("Sorry, the Scout class is currently not avalible for use. Please select another class.");
			return;
			
		case SOLDIER:
			
			if(TrenchPvP.getTrenchCFG().isSoldierEnabled()){
				
				t.equipKit(new TrenchSoldier(t));
				teleportByTeam(t);
				return;
			
			}else
			
			p.sendMessage("Sorry, the Soldier class is currently not avalible for use. Please select another class.");
			return;
			
		case PYRO:
			
			if(TrenchPvP.getTrenchCFG().isPyroEnabled()){
				
				t.equipKit(new TrenchPyro(t));
				teleportByTeam(t);
				return;
			
			}else
			
			p.sendMessage("Sorry, the Pyro class is currently not avalible for use. Please select another class.");
			return;
			
		case DEMOMAN:
			
			if(TrenchPvP.getTrenchCFG().isDemomanEnabled()){
				
				t.equipKit(new TrenchDemoman(t));
				teleportByTeam(t);
				return;
			
			}else
			
			p.sendMessage("Sorry, the Demoman class is currently not avalible for use. Please select another class.");
			return;
			
		case HEAVY:
			if(TrenchPvP.getTrenchCFG().isHeavyEnabled()){
			
				t.equipKit(new TrenchHeavy(t));
				teleportByTeam(t);
				return;
			
			}
			
			p.sendMessage("Sorry, the Heavy class is currently not avalible for use. Please select another class.");
			return;
			
		case SNIPER:
			
			if(TrenchPvP.getTrenchCFG().isSniperEnabled()){
				
				t.equipKit(new TrenchSniper(t));
				teleportByTeam(t);
				return;
			
			}
			
			p.sendMessage("Sorry, the Sniper class is currently not avalible for use. Please select another class.");
			return;
			
		case MEDIC:
			if(TrenchPvP.getTrenchCFG().isMedicEnabled()){
				
				t.equipKit(new TrenchMedic(t));
				teleportByTeam(t);
				return;
			}
			
			p.sendMessage("Sorry, the Medic class is currently not avalible for use. Please select another class");
			return;
			
		case SPY:
			if(TrenchPvP.getTrenchCFG().isSpyEnabled()) {
				
				t.equipKit(new TrenchSpy(t));
				teleportByTeam(t);
				return;
			}
			
			p.sendMessage("Sorry, the Medic class is currently not avalible for use. Please select another class");
			return;
		
		default:
			
		}
		
		if(TeamManager.findTrenchPlayer(p).getPlayerTeam() == TrenchTeams.BLUE) {
			p.teleport(Teleports.getBlueSpawn());
		}else {
			p.teleport(Teleports.getRedSpawn());
		}
		p.sendMessage(ChatColor.AQUA + "You are now type " + ChatColor.RED + t.getKitType());
		
	}
	private void teleportByTeam(TrenchPlayer t) {
		if(TeamManager.findTrenchPlayer(t.getPlayer()).getPlayerTeam() == TrenchTeams.BLUE) {
			t.getPlayer().teleport(Teleports.getBlueSpawn());
		}else {
			t.getPlayer().teleport(Teleports.getRedSpawn());
		}
		t.getPlayer().sendMessage(ChatColor.AQUA + "You are now type " + ChatColor.RED + t.getKitType());
	}
}
