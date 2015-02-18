package com.sinfulpixel.VillageEdit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by Min3 on 2/9/2015.
 */
public class VillageEdit extends JavaPlugin {
    NpcConfig npc = new NpcConfig(this);
    public void onEnable(){
        try {
            MakeDir();
            NpcConfig.createCfg();
            NpcConfig.cacheNPC();
        }catch(Exception e){
            System.out.println("|||=========[ Plugin Startup Task Error ]=========|||");
            e.printStackTrace();
            System.out.println("|||=========[ End Error ]=========|||");
        }
    getCommand("villager").setExecutor(new VillagerCmd(this));
    }
    public void onDisable(){
        Bukkit.getScheduler().cancelAllTasks();
    }
    private void MakeDir(){
        File dir = this.getDataFolder();
        if(!dir.exists()){
            boolean res = dir.mkdir();
        }
    }
}
