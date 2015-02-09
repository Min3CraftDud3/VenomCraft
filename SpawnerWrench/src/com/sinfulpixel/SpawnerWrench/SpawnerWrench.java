package com.sinfulpixel.SpawnerWrench;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Min3 on 2/8/2015.
 */
public class SpawnerWrench extends JavaPlugin implements Listener {
    CfgMgr cfg = new CfgMgr(this);
    public void onEnable(){
        CfgMgr.createConfig();
        CfgMgr.cacheMobs();
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("wrench").setExecutor(new WrenchCmd(this));
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.isCancelled()){return;}
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if(b.getType() != Material.MOB_SPAWNER){return;}
        short eID = getSpawnerEntityID(b);
        String name = getEntityName(b);
        if(e.getBlock().getType().equals(Material.MOB_SPAWNER)){
            if(e.getPlayer().getItemInHand().hasItemMeta()) {
                if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Wrench")) {
                    if(CfgMgr.mobs.get(name)) {
                        e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), newSpawnerItem(eID, name));
                        e.getPlayer().getInventory().remove(e.getPlayer().getItemInHand());
                    }else{
                        b.getDrops().clear();
                    }
                }else{
                    e.getPlayer().sendMessage(ChatColor.RED+"You must your a "+ ChatColor.GOLD+"Wrench "+ChatColor.RED+"to mine a spawner.");
                    b.getDrops().clear();
                }
            }
        }
    }
    public short getSpawnerEntityID(Block block){
        BlockState bs = block.getState();
        if(!(bs instanceof CreatureSpawner)){return 0;}
        return ((CreatureSpawner)bs).getSpawnedType().getTypeId();
    }
    public String getEntityName(Block block){
        BlockState bs = block.getState();
        if(!(bs instanceof CreatureSpawner)){return null;}
        return ((CreatureSpawner)bs).getSpawnedType().getName();
    }
    public ItemStack newSpawnerItem(short entityID,String name){
        ItemStack item = new ItemStack(Material.MOB_SPAWNER,1,entityID);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY+name+" Spawner");
        item.setDurability(entityID);
        item.setItemMeta(meta);
        return item;
    }
    public static void givePlayerWrench(Player p){
        ArrayList<String> lrWrench = new ArrayList<String>();
        lrWrench.add(ChatColor.DARK_PURPLE+"Used to mine a spawner 1x use.");
        ItemStack wrench = new ItemStack(Material.GOLD_PICKAXE,1);
        ItemMeta wm = wrench.getItemMeta();
        wm.setDisplayName(ChatColor.GOLD+"Wrench");
        wm.setLore(lrWrench);
        wrench.setItemMeta(wm);
        wrench.addEnchantment(Enchantment.SILK_TOUCH,1);
        p.getInventory().addItem(wrench);
    }
}
