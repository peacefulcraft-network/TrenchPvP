package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class TrenchParty
{
	private String name;
		public String getName() { return name; }
	private ArrayList<TrenchPlayer> members;
		public ArrayList<TrenchPlayer> getMembers() { return members; }
	private TrenchPlayer leader;
		public TrenchPlayer getLeader() { return leader; }
		public void setLeader(TrenchPlayer leader) { this.leader = leader; }
	private HashMap<TrenchPlayer, TrenchPlayer> inviteMap;
		public HashMap<TrenchPlayer, TrenchPlayer> getInviteMap() {return inviteMap; }
	
	private FileConfiguration partyConfig;
	
	public TrenchParty(String name) {
		this.name = name;
		members = new ArrayList<>();
		leader = null;
		
		inviteMap = new HashMap<>();
	}
	
	public void createParty(TrenchPlayer leader) {
		if(leader == null) {
			return;
		} else {
			setLeader(leader);
			getMembers().add(leader);
		}
	}
	
	public void updateParty() {
		Hashtable<String, Object> partyTable = new Hashtable<String, Object>();
		ArrayList<String> str = new ArrayList<String>();
		
		for(TrenchPlayer member : getMembers()) {
			str.add(member.getPlayer().getUniqueId().toString());
		}
		partyTable.put("members", str);
		partyTable.put("leader", getLeader().getPlayer().getUniqueId());
		partyTable.put("inviteMap", getInviteMap());
		
		partyConfig.set(getName(), partyTable);
	}
	
	public void leaveParty(TrenchPlayer p) {
		if(p.isInParty()) {
			if(getMembers().size() == 1 && p == TrenchPvP.getPartyManager().getParty(p.getPartyName()).getLeader()) {
				removeParty();
			} else {
				getMembers().remove(p);
				p.setPartyName("");
			}
		}
		updateParty();
	}
	
	public void removeParty() {
		TrenchPvP.getPartyManager().getListParties().remove(getName());
		//TODO: Remove party from file
		for(TrenchPlayer member : getMembers()) {
			member.setPartyName("");
		}
	}
	
	public void invitePlayer(TrenchPlayer invitedBy, TrenchPlayer invitedPlayer) {
		if(invitedPlayer.getPlayer().isOnline()) {
			inviteMap.put(invitedPlayer, invitedBy);
			invitedPlayer.setLastInvite(invitedBy.getPartyName());
			
			updateParty();
		} else {
			invitedBy.getPlayer().sendMessage(ChatColor.GOLD + "Cannot send invite to offline player.");
		}
	}
	
	public boolean cancelInvite(TrenchPlayer invitedPlayer) {
		if(!(inviteMap.containsKey(invitedPlayer))) {
			return false;
		} else {
			TrenchPlayer invitedBy = inviteMap.get(invitedPlayer);
			
			invitedBy.getPlayer().sendMessage(ChatColor.GOLD + "Invite to " + ChatColor.BLUE + "" + invitedPlayer + ChatColor.GOLD + " cancelled.");
			invitedPlayer.getPlayer().sendMessage(ChatColor.GOLD + "Most revent invite has been cancelled.");
			
			invitedPlayer.setLastInvite("");
			inviteMap.remove(invitedPlayer);
			
			updateParty();
			return true;
		}
	}
	
	public void acceptInvite(TrenchPlayer invitedPlayer, Boolean accepted) {
		TrenchPlayer invitedBy = inviteMap.get(invitedPlayer);
		if(accepted) {
			invitedBy.getPlayer().sendMessage(ChatColor.BLUE + "" + invitedPlayer + ChatColor.GOLD + " has accepted your party invite.");
			invitedPlayer.getPlayer().sendMessage(ChatColor.GOLD + "You have accepted " + ChatColor.BLUE + "" + invitedBy + ChatColor.GOLD + "'s invite.");
			
			inviteMap.remove(invitedBy);
			invitedPlayer.setLastInvite("");
			
			getMembers().add(invitedPlayer);
			
			invitedPlayer.setPartyName(getName());
			
			updateParty();
		}
	}
	
	public void denyInvite(TrenchPlayer invitedPlayer) {
		TrenchPlayer invitedBy = inviteMap.get(invitedPlayer);
		invitedBy.getPlayer().sendMessage(ChatColor.BLUE + "" + invitedPlayer + ChatColor.GOLD + " has denied your party invite.");
		invitedPlayer.getPlayer().sendMessage(ChatColor.GOLD + "You have denied " + ChatColor.BLUE + "" + invitedBy + ChatColor.GOLD + "'s invited.");
		
		invitedPlayer.setLastInvite("");
		inviteMap.remove(invitedPlayer);
		
		updateParty();
	}
	
	public void ExecuteOnAllMembers(PlayerWideExecutor ex) {
		for(TrenchPlayer t : getMembers()) {
			ex.execute(t);
		}
	}
	
}
