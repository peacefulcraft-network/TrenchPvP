package net.peacefulcraft.trenchpvp.menu.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.gamehandle.GameManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.player.Teleports;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchTeams;
import net.peacefulcraft.trenchpvp.menu.GameMenu;
import net.peacefulcraft.trenchpvp.menu.GameMenu.Row;
import net.peacefulcraft.trenchpvp.menu.GameMenu.onClick;

public class UltimateMenu
{
	private enum NextLevelKits {
		WITCH_DOCTOR;
	}
	
	private GameMenu menu = new GameMenu("Ultimate Menu", 3, new onClick() {
		@Override
		public boolean click(Player p, GameMenu menu, Row row, int slot, ItemStack item) {
			if(row.getRow() == 0 || row.getRow() == 2) {
				inventoryClick(p, item);
			}
			return true;
		}
	});
	
	public UltimateMenu() {
		
		
		menu.addButton(menu.getRow(0), 8, new ItemStack(Material.RED_STAINED_GLASS_PANE), "Quit", "Click to Leave Trench!");
		menu.addButton(menu.getRow(2), 8, new ItemStack(Material.BLUE_STAINED_GLASS_PANE), "Regular Classes", "Click to Return to Regular Class Selection!");
	}
	
	private void inventoryClick(Player p, ItemStack item) {
		TrenchPlayer t = TeamManager.findTrenchPlayer(p);
		if(t == null) { return; }
		
		String itemText = item.getItemMeta().getDisplayName().toUpperCase();
		if(itemText.equals("QUIT")) {
			GameManager.quitPlayer(p);
			//TODO: Remove from kitmenu cooldown
		}
		if(itemText.equals("REGULAR CLASSES")) {
			KitMenu kitMenu = new KitMenu();
			menu.close(p);
			kitMenu.menuOpen(p);
		}
		itemText = itemText.replaceAll(" ", "_");
		switch(NextLevelKits.valueOf(itemText)) {
		case WITCH_DOCTOR:
			
		}
	}
	
	public void menuOpen(Player p) {
		menu.open(p);
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
