package com.sinfulpixel.VillageEdit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Min3 on 2/10/2015.
 */
public class VillagerCmd implements CommandExecutor {
    VillageEdit plugin;
    public VillagerCmd(VillageEdit plugin){this.plugin=plugin;}
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(cmd.getName().equalsIgnoreCase("Villager")){
                if(args.length==0){
                    sender.sendMessage("Usage: /Villager <create,reload,info>");
                }
                if(args.length==2){
                    if(args[0].equalsIgnoreCase("create") && sender.hasPermission("Villager.Create")) {
                        try{
                            Location l = p.getLocation();
                            NpcConfig.createNPC(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
                            p.sendMessage(ChatColor.GREEN + "Created Merchant!");
                        }catch(Exception ee){
                            System.out.println("|||=========[ Error Create Merchant Command ]=========|||");
                            ee.printStackTrace();
                            System.out.println("|||=========[ End Error ]=========|||");
                        }
                    }
                    if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("Villager.Reload")){
                        NpcConfig.cacheNPC();
                        sender.sendMessage(ChatColor.GREEN+"VillagerEdit has been reloaded.");
                    }
                    if(args[0].equalsIgnoreCase("info")){
                        sender.sendMessage(ChatColor.GOLD+"oOo______Villager Edit______oOo");
                        sender.sendMessage(ChatColor.GOLD+"Coder: "+ChatColor.DARK_PURPLE+" Min3CraftDud3");
                        sender.sendMessage(ChatColor.GOLD+"Website: "+ChatColor.DARK_PURPLE+" http://www.SinfulPixel.com");
                    }
                }
            }
        }
        return false;
    }
}
