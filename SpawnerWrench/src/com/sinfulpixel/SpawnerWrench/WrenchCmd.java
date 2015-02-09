package com.sinfulpixel.SpawnerWrench;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 2/8/2015.
 */
public class WrenchCmd implements CommandExecutor {
    SpawnerWrench plugin;
    public WrenchCmd(SpawnerWrench plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if(cmd.getName().equalsIgnoreCase("wrench")){
            if(args.length==1) {
                if(args[0].equalsIgnoreCase("info")) {
                    sender.sendMessage(ChatColor.GOLD+"oOo______Spawner Wrench______oOo");
                    sender.sendMessage(ChatColor.GOLD+"Coder: "+ChatColor.DARK_PURPLE+" Min3CraftDud3");
                    sender.sendMessage(ChatColor.GOLD+"Website: "+ChatColor.DARK_PURPLE+" http://www.SinfulPixel.com");
                }
            }
            if(args.length==2) {
                if (args[0].equalsIgnoreCase("give")) {
                    if(!sender.hasPermission("Wrench.Give")){sender.sendMessage(ChatColor.RED+"Error: You do not have permissions for this command.");}
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        SpawnerWrench.givePlayerWrench(p);
                    }
                }
            }

        }
        return false;
    }
}
