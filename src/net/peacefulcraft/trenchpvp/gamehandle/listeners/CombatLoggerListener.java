package net.peacefulcraft.trenchpvp.gamehandle.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.peacefulcraft.trenchpvp.TrenchPvP;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager;
import net.peacefulcraft.trenchpvp.gamehandle.TeamManager.PlayerWideExecutor;
import net.peacefulcraft.trenchpvp.gamehandle.player.TrenchPlayer;

public class CombatLoggerListener implements Listener {
    
    //private static HashMap<TrenchPlayer,CombatLogger> loggers = new HashMap<>();

    private static HashMap<TrenchPlayer, EntityDamageEvent> damageMap;

    private static HashMap<TrenchPlayer, Integer> taskMap;

    @EventHandler
    public void damageListener(EntityDamageEvent ev) {
        //If entity is not player
        if(!(ev.getEntity() instanceof Player)) { return; }
        
        //If player is in the game
        Player p = (Player)ev.getEntity();
        TrenchPlayer t = TeamManager.findTrenchPlayer(p);
        if(t == null) { return; }

        //If player has outstanding task id we cancel
        if(taskMap.containsKey(t)) {
            int id = taskMap.get(t);
            Bukkit.getServer().getScheduler().cancelTask(id);
        }

        //Adding to damage map and player data
        damageMap.put(t, ev);
        t.setCombatLogged(true);

        int id = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
            TrenchPvP.getPluginInstance(), 
            new Runnable(){
                @Override
                public void run() {
                    //After 5 seconds we remove from map and clear player data
                    damageMap.remove(t);
                    taskMap.remove(t);
                    t.setCombatLogged(false);
                }
            }, 100);   
        
        //If register wasn't failure we put
        if(id != -1) {
            taskMap.put(t, id);
        }
    }

    @EventHandler 
    public void deathListener(PlayerDeathEvent ev) {
        Player p = ev.getEntity();

        //If player is in game
        TrenchPlayer t = TeamManager.findTrenchPlayer(p);
        if(t == null) { return; }

        //If player is combat logged we remove
        if(taskMap.containsKey(t)) {
            taskMap.remove(t);
            damageMap.remove(t);
            t.setCombatLogged(false);
        }
    }

    public static void clearAll() {
        taskMap.clear();
        damageMap.clear();
        TeamManager.ExecuteOnAllPlayers(new PlayerWideExecutor(){

            @Override
            public void execute(TrenchPlayer t) {
                t.setCombatLogged(false);
            }
            
        });
    }
}