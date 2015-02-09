package com.SinfulPixel.DeathBan;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

/**
 * Created by Min3 on 2/8/2015.
 */
public class BanCmd implements CommandExecutor {
    DeathBan plugin;
    public BanCmd(DeathBan plugin){this.plugin=plugin;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("unban") && sender.hasPermission("DeathBan.Unban")){
            if(args.length!=1){
                System.out.println("Error: /unban <player>");
                plugin.checkHash();
                return true;
            }
            String target = args[0];
            if(getBanned().containsKey(target.toLowerCase())){
                getBanned().remove(target.toLowerCase());
                plugin.getServer().getPlayerExact(target).setBanned(false);
                System.out.println("[DeathBan] The player " + args[0] + " is now un-banned.");
                return true;
            }else{
                System.out.println("Error: The player " + args[0] + " isn't banned.");
                return true;
            }
        }
        return false;
    }
    public HashMap<String, Long> getBanned(){
        return DeathBan.banned;
    }
}
