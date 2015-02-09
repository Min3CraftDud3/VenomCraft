package com.sinfulpixel.SpawnerWrench;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Min3 on 2/8/2015.
 * Configuration Manager
 */
public class CfgMgr {
    static SpawnerWrench plugin;
    public static HashMap<String,Boolean> mobs = new HashMap<>();
    public CfgMgr(SpawnerWrench plugin){this.plugin=plugin;}
    public static void createConfig(){
        try {
            File cfg = new File(plugin.getDataFolder()+File.separator+ "config.yml");
            if (!cfg.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(cfg);
                fc.set("SpawnerWrench.Author", "Min3CraftDud3");
                fc.set("SpawnerWrench.Website", "http://www.SinfulPixel.com");
                fc.set("SpawnerWrench.Mobs.Warning", "======[ Mob / Enabled ]======");
                for (EntityType e : EntityType.values()) {
                    fc.set("SpawnerWrench.Mobs." + e.getName(), true);
                }
                fc.save(cfg);
            }
        }catch(IOException e){e.printStackTrace();}
    }
    public static void cacheMobs(){
        File cfg = new File(plugin.getDataFolder() +File.separator+ "config.yml");
        if (cfg.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(cfg);
            for(EntityType en : EntityType.values()){
                mobs.put(en.getName(),fc.getBoolean("SpawnerWrench.Mobs."+en.getName()));
                System.out.println(en.getName()+"|"+fc.getBoolean("SpawnerWrench.Mobs."+en.getName()));
            }
        }
    }
}
