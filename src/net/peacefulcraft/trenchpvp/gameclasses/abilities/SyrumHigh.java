package net.peacefulcraft.trenchpvp.gameclasses.abilities;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import net.peacefulcraft.trenchpvp.gameclasses.classConfigurations.TrenchKit;

public class SyrumHigh extends TrenchAbility
{
	private TrenchKit k;
	
	private final int EFFECT_TIME = 300;
	
	public SyrumHigh(TrenchKit k) {
		super(k.getTrenchPlayer(), 25000);
		this.k = k;
	}

	@Override
	public boolean abilitySignature(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		
		if(!(p.getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE)) return false;
		if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Syrum High"))) return false;
		
		return true;
	}

	@Override
	public void triggerAbility(Event ev)
	{
		Player p = k.getTrenchPlayer().getPlayer();
		Random rand = new Random();
		int effectNum = rand.nextInt(4);
		
		if(effectNum == 0) {
			p.sendMessage(ChatColor.BLUE + "The syrum was mixed wrong! Try again next time");
			return;
		}
		
		boolean effectB = rand.nextBoolean();
		if(effectB) {

			for(int i=0; i<=effectNum;i++) {
				int effect = rand.nextInt(4);
				
				switch(effect) {

				case 1:
					p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, EFFECT_TIME, 2));
				case 2:
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, EFFECT_TIME, 3));
				case 3:
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, EFFECT_TIME, 3));
				case 4:
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, EFFECT_TIME, 3));
				}
			}
			p.sendMessage(ChatColor.BLUE + "The syrum rolled in your favor!");
			return;
		} else {
			
			for(int i=0; i<=effectNum;i++) {
				int effect = rand.nextInt(4);
				
				switch(effect) {

				case 1:
					p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, EFFECT_TIME, 3));
				case 2:
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, EFFECT_TIME, 3));
				case 3:
					p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, EFFECT_TIME, 2));
				case 4:
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, EFFECT_TIME, 4));
				}
			}
		}
		p.sendMessage(ChatColor.BLUE + "The syrum was not kind to you this time");
		return;
	}
}
