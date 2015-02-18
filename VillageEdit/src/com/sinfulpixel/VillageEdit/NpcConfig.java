package com.sinfulpixel.VillageEdit;


import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

/**
 * Created by Min3 on 2/9/2015.
 */
public class NpcConfig {
    static VillageEdit plugin;
    public static ArrayList<Location> iloc = new ArrayList<Location>();
    public static HashMap<UUID,Location> iVillager = new HashMap<UUID,Location>();
    public static int freeze;
    public NpcConfig(VillageEdit plugin){this.plugin=plugin;}

    public static ItemStack itemFromCfg(String s){
        ItemStack item = null;
        File cfg = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Villagers.yml");
        if(cfg.exists()) {
         FileConfiguration fc = YamlConfiguration.loadConfiguration(cfg);
            List<String> enc = fc.getStringList(s+".Enchantments");
            item = new ItemStack(Material.getMaterial(s+"Material"),Integer.parseInt(s+".Amount"));
            for(String st:enc) {
                String[] sp = st.split(",");
                item.addUnsafeEnchantment(Enchantment.getByName(sp[0]),Integer.parseInt(sp[1]));
            }
        }
        return item;
    }

    public static void createCfg() {
        HashMap<Enchantment,Integer> enc = new HashMap<Enchantment,Integer>();
        enc.put(Enchantment.DAMAGE_ALL, 2);
        enc.put(Enchantment.DURABILITY, 3);
        ArrayList<String> ss = new ArrayList<>();
        for(Enchantment e:enc.keySet()){
            ss.add(e.getName()+","+enc.get(e));
        }
        try {
            File dir = new File(plugin.getDataFolder() + File.separator + "data");
            if (!dir.exists()) {
                boolean res = dir.mkdir();
            }
            File cfg = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Villagers.yml");
            if (!cfg.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(cfg);
                fc.set("Villager.Default.Location","world,100,100,100");
                fc.set("Villager.Default.Item.1.Material","DIAMOND_SWORD");
                fc.set("Villager.Default.Item.1.Amount",1);
                fc.set("Villager.Default.Item.1.Name", ChatColor.AQUA+"Sharp Sword");
                fc.set("Villager.Default.Item.1.Enchantments",ss);
                fc.set("Villager.Default.Item.2.Material","DIAMOND_SWORD");
                fc.set("Villager.Default.Item.2.Amount",1);
                fc.set("Villager.Default.Item.2.Name", ChatColor.AQUA+"Dull Sword");
                fc.set("Villager.Default.Item.2.Enchantments",ss);
                fc.set("Villager.Default.Reward.Material","GOLD_SWORD");
                fc.set("Villager.Default.Reward.Amount",1);
                fc.set("Villager.Default.Reward.Name", ChatColor.AQUA+"Magic Sword");
                fc.set("Villager.Default.Reward.Enchantments",ss);
                fc.save(cfg);
            }
        }catch(Exception e){
            System.out.println("|||=========[ Error Creating Default Config File ]=========|||");
            e.printStackTrace();
            System.out.println("|||=========[ End Error ]=========|||");
        }
    }
    public static void createNPC(String s){
        try {
            File cfg = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Villagers.yml");
            if (cfg.exists()) {
                FileConfiguration fc = YamlConfiguration.loadConfiguration(cfg);
                int i = getLastNPC(fc);
                fc.set("Villager." + i + ".Location", s);
                fc.set("Villager." + i + ".Item.1", "Edit Me");
                fc.set("Villager." + i + ".Item.2", "Edit Me");
                fc.set("Villager." + i + ".Reward", "Edit Me");
                fc.save(cfg);
            }
            cacheNPC();
        }catch(Exception e){
            System.out.println("|||=========[ Error Creating NPC ]=========|||");
            e.printStackTrace();
            System.out.println("|||=========[ End Error ]=========|||");
        }
    }
    public static Integer getLastNPC(FileConfiguration fc){
        Set<String> st = fc.getConfigurationSection("Villager").getKeys(false);
        int size = st.size();
        return size+1;
    }
    public static void cacheNPC(){
        File cfg = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "Villagers.yml");
        List<String> locs = new ArrayList<>();
        if(cfg.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(cfg);
            for(int i=0;i<=fc.getConfigurationSection("Villager").getKeys(false).size();i++){
                locs.add(fc.getString("Villager."+i+".Location"));
            }
        }
        if(locs != null) {
            for (String s : locs) {
                if (s!=null && !s.isEmpty() && !s.equals("")) {
                    String[] t = s.split(",");
                    iloc.add(new Location(Bukkit.getWorld(t[0]), Double.parseDouble(t[1]), Double.parseDouble(t[2]), Double.parseDouble(t[3])));
                    System.out.println("Caching Villager at Location: " + t[1] + "," + t[2] + "," + t[3]);
                }
            }
        }
        createActiveNPC();
    }
    public static void createActiveNPC(){
        System.out.println("Creating Active NPC's");
        for(Location l:iloc){
            Villager villager = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
            villager.setCustomName(ChatColor.GREEN+"Merchant");
            villager.setCustomNameVisible(true);
            villager.setNoDamageTicks(Integer.MAX_VALUE);
            villager.setRemoveWhenFarAway(false);
            villager.setAdult();
            villager.setProfession(Villager.Profession.PRIEST);
            iVillager.put(villager.getUniqueId(), villager.getLocation());
        }
        createTrades();
    }
    public static void createTrades(){
        System.out.println("Creating Villager Trades");
        int i = iVillager.size();
        int c = 0;
        while(c<i) {
            for (UUID u : iVillager.keySet()) {
                for (World w : Bukkit.getWorlds()) {
                    for (Entity e : w.getEntities()) {
                        if (e.getUniqueId().equals(u)) {
                            if (e instanceof Villager) {
                                Villager v = (Villager) e;
                                TradeAPI.ClearTrades(v);
                                if(itemFromCfg("Villager."+c+"Item.1") != null && itemFromCfg("Villager."+c+"Item.2")!=null && itemFromCfg("Villager."+c+".Reward")!=null) {
                                    ItemStack item1 = itemFromCfg("Villager." + c + ".Item.1");
                                    ItemStack item2 = itemFromCfg("Villager." + c + ".Item.2");
                                    ItemStack reward = itemFromCfg("Villager." + c + ".Reward");
                                    VillagerTrade trade = new VillagerTrade(item1, item2, reward);
                                    TradeAPI.addTrade(v,trade);
                                }else if(itemFromCfg("Villager."+c+"Item.1") != null && itemFromCfg("Villager."+c+"Item.2")==null && itemFromCfg("Villager."+c+".Reward")!=null){
                                    ItemStack item1 = itemFromCfg("Villager." + c + ".Item.1");
                                    ItemStack reward = itemFromCfg("Villager." + c + ".Reward");
                                    VillagerTrade trade = new VillagerTrade(item1, reward);
                                    TradeAPI.addTrade(v,trade);
                                }
                                c++;
                            }
                        }
                    }
                }
            }
        }
        freezeBanker();
    }
    public static void freezeBanker(){
        System.out.println("Starting Villager Freeze Task");
        freeze = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,new Runnable(){
            public void run() {
                for (UUID u : iVillager.keySet()) {
                    for (Entity e : Bukkit.getWorld("world").getEntities()) {
                        if (e.getUniqueId().equals(u)) {
                            Location l = iVillager.get(u);
                            e.teleport(new Location(l.getWorld(),l.getX(),l.getWorld().getHighestBlockYAt((int)l.getX(),(int)l.getZ()),l.getZ()));
                        }
                    }
                }
            }
        },0,0);
    }

}
