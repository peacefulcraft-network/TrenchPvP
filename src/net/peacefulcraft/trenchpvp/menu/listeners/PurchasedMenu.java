package net.peacefulcraft.trenchpvp.menu.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchAdrenalineJunkie;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchPigKing;
import net.peacefulcraft.trenchpvp.gamehandle.Announcer;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchPlayer;
import net.peacefulcraft.trenchpvp.menu.GameMenu;
import net.peacefulcraft.trenchpvp.menu.GameMenu.Row;
import net.peacefulcraft.trenchpvp.menu.GameMenu.onClick;

public class PurchasedMenu
{
	private enum PurchasedKits {
		ADRENALINE_JUNKIE, PIG_KING, NTH_ENTITY, 
		JUNIOR_COMMUNITY_MANAGER, ELEMENTALIST, DUOLINGO_BIRD;
	}
	
	private GameMenu menu = new GameMenu("Purchased Menu", 3, new onClick() {
	    @Override
	    public boolean click(Player p, GameMenu menu, Row row, int slot, ItemStack item) {
	        if(row.getRow() == 0 || row.getRow() == 2){
	            inventoryClick(p, item);
	        }
	        return true;
	    }
	});
	
	public PurchasedMenu() {
		menu.addButton(menu.getRow(0), 0, new ItemStack(Material.GLASS_BOTTLE), "Adrenaline Junkie", "Click to Equip The Adrenaline Junkie Class!");
		menu.addButton(menu.getRow(2), 0, new ItemStack(Material.PORKCHOP), "Pig King", "Click to Equip The Pig King Class!");
		//menu.addButton(menu.getRow(0), 2, new ItemStack(Material.GRAY_BANNER), "Nth Entity", "Click to Equip The Nth Entity Class!");
		menu.addButton(menu.getRow(0), 2, new ItemStack(Material.GRAY_BANNER), "Nth Entity", "Coming Soon....");
		//menu.addButton(menu.getRow(2), 2, new ItemStack(Material.TURTLE_EGG), "Junior Community Manager", "Click to Equip The Junior Community Manager Class!");
		menu.addButton(menu.getRow(2), 2, new ItemStack(Material.TURTLE_EGG), "Junior Community Manager", "Coming Soon....");
		//menu.addButton(menu.getRow(0), 4, new ItemStack(Material.DARK_OAK_SAPLING), "Elementalist", "Click to Equip The Elementalist Class!");
		menu.addButton(menu.getRow(0), 4, new ItemStack(Material.DARK_OAK_SAPLING), "Elementalist", "Coming Soon....");
		
		menu.addButton(menu.getRow(0), 8, new ItemStack(Material.RED_STAINED_GLASS_PANE), "Quit", "Click to Leave Trench!");
		menu.addButton(menu.getRow(2), 8, new ItemStack(Material.BLUE_STAINED_GLASS_PANE), "Regular Classes", "Click to Return to Regular Class Selection!");
	}
	
	private void inventoryClick(Player p, ItemStack item) {
		TrenchPlayer t = TrenchPvP.getTrenchManager().findTrenchPlayer(p);
		if(t == null) { return; }
		
		String itemText = item.getItemMeta().getDisplayName().toUpperCase();
		if(itemText.equals("QUIT")) {
			t.getArena().playerLeave(p);
			//TODO: Remove from kitmenu cooldown
		}
		if(itemText.equals("REGULAR CLASSES")) {
			KitMenu kitMenu = new KitMenu();
			menu.close(p);
			kitMenu.menuOpen(p);
		}
		itemText = itemText.replaceAll(" ", "_");
		
		
		/*
			Check that the enum is valid before we try taking the
			.valueOf. Eventually we should probably look at the 
			name of the inventory and check it matches "Purchased Kits" 
			as that's faster than this and we'll just assume that inv.
			will only have PurchasedKit enum types. if it doesn't something is wrong
		*/
		boolean invalid = true;
		for(PurchasedKits key : PurchasedKits.values()) {
			if(itemText.equalsIgnoreCase( key.toString() )) {
				invalid = false;
				break;
			}
		}
		
		if(invalid)
			return;
		
		/*
		  End of Check
		*/
					
					
		switch(PurchasedKits.valueOf(itemText)) {
		case ADRENALINE_JUNKIE:
			if(p.hasPermission("trenchpvp.class.adrenaline_junky")) {
				t.equipKit(new TrenchAdrenalineJunkie(t));
				teleportByTeam(t);
			}else {
				sendKitBuyMessage(p);
			}
			return;
			
		case PIG_KING:
			if(p.hasPermission("trenchpvp.class.pig_king")) {
				t.equipKit(new TrenchPigKing(t));
				teleportByTeam(t);
			}else {
				sendKitBuyMessage(p);
			}
			return;
		case NTH_ENTITY:
			sendKitDisabledMessage(p);
			return;
//			t.equipKit(new TrenchNthEntity(t));
//			teleportByTeam(t);
//			return;
		case JUNIOR_COMMUNITY_MANAGER:
			sendKitDisabledMessage(p);
			return;
//			t.equipKit(new TrenchJuniorCommunityManager(t));
//			teleportByTeam(t);
//			return;
		case ELEMENTALIST:
			sendKitDisabledMessage(p);
			return;
//			t.equipKit(new TrenchElementalist(t));
//			teleportByTeam(t);
//			return;
		}
	}
	
	public void menuOpen(Player p) {
		menu.open(p);
	}
	
	private void teleportByTeam(TrenchPlayer t) {
		TrenchPvP.getTrenchManager().getCurrentArena().teleportToSpawn(t);
		t.getPlayer().sendMessage(ChatColor.AQUA + "You are now type " + ChatColor.RED + t.getKitType());
	}
	
	private void sendKitBuyMessage(Player p) {
		p.sendMessage(Announcer.getTrenchPrefix() + "You must purchase this kit in the kit shop on minigames to use it.");
		p.sendMessage(Announcer.getTrenchPrefix() + "Use points earned through playing Minigames to buy things in the shop.");
	}
	
	private void sendKitDisabledMessage(Player p) {
		p.sendMessage(Announcer.getTrenchPrefix() + "Sorry, this kit is not available right now.");
	}
}
