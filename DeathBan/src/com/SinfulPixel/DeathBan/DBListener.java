package com.SinfulPixel.DeathBan;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.HashMap;

/**
 * Created by Min3 on 2/8/2015.
 */
public class DBListener implements Listener {
    public DeathBan plugin;

    public DBListener(DeathBan p) {
        plugin = p;
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e) {
        System.out.println("0");
        Player player = e.getPlayer();
        if (DeathBan.banned.containsKey(player.getName().toLowerCase())) {
            System.out.println("1");
            if (DeathBan.banned.get(player.getName().toLowerCase()) != null) {
                System.out.println("2");
                long endOfBan = DeathBan.banned.get(player.getName().toLowerCase());
                long now = System.currentTimeMillis();
                long diff = endOfBan - now;
                System.out.println(now+"|"+diff);
                if (diff <= 0) {
                    DeathBan.banned.remove(player.getName().toLowerCase());
                } else {
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "[DeathBan] You are temp-banned for " + OnDeath.getMSG(endOfBan));
                }
            }
        }
    }

    public void tell(Player player, String m) {
        player.sendMessage(m);
    }

    public HashMap<String, Long> getBanned() {
        return DeathBan.banned;
    }

}