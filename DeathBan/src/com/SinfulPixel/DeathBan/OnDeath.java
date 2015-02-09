package com.SinfulPixel.DeathBan;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

/**
 * Created by Min3 on 2/8/2015.
 */
public class OnDeath implements Listener {
    DeathBan plugin;
    public OnDeath(DeathBan plugin){this.plugin=plugin;}
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        long endOfBan = System.currentTimeMillis() + BanUnit.getTicks("hour", 3);
        long now = System.currentTimeMillis();
        long diff = endOfBan - now;
        Player p = (Player)e.getEntity();
        if(e.getEntity().getKiller() instanceof Player) {
            Player k = (Player) e.getEntity().getKiller();
            if (diff > 0) {
                setBanned(p.getDisplayName().toLowerCase(), endOfBan);
                String message = getMSG(endOfBan);
                e.setDeathMessage("[DeathBan]" + p.getDisplayName() + " was killed by " + k.getDisplayName() +" with "+getItemName(k));
                p.kickPlayer("[DeathBan] You are temp-banned for " + message);
            }
        }else{
            if (diff > 0) {
                setBanned(p.getDisplayName().toLowerCase(), endOfBan);
                String message = getMSG(endOfBan);
                p.kickPlayer("[DeathBan] You are temp-banned for " + message);
            }
        }
    }
    public static String getItemName(Player p){
        if(p.getItemInHand().hasItemMeta()){
            return p.getItemInHand().getItemMeta().getDisplayName();
        }
        return p.getItemInHand().getType().name();
    }
    public HashMap<String, Long> getBanned(){
        return DeathBan.banned;
    }

    public void setBanned(String name, long end){
        getBanned().put(name, end);
    }

    public static String getMSG(long endOfBan){
        String message = "";

        long now = System.currentTimeMillis();
        long diff = endOfBan - now;
        int seconds = (int) (diff / 1000);

        if(seconds >= 60*60*24){
            int days = seconds / (60*60*24);
            seconds = seconds % (60*60*24);

            message += days + " Day(s) ";
        }
        if(seconds >= 60*60){
            int hours = seconds / (60*60);
            seconds = seconds % (60*60);

            message += hours + " Hour(s) ";
        }
        if(seconds >= 60){
            int min = seconds / 60;
            seconds = seconds % 60;

            message += min + " Minute(s) ";
        }
        if(seconds >= 0){
            message += seconds + " Second(s) ";
        }

        return message;
    }
}
