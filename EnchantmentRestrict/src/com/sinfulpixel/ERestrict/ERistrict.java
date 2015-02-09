package com.sinfulpixel.ERestrict;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Min3 on 2/9/2015.
 */
public class ERistrict extends JavaPlugin implements Listener{
    /*                    ENABLE PLUGIN                       */
    public void onEnable(){
        getLogger().info("Enchantment Restrict is now enabled.");

        FileConfiguration config = this.getConfig();
    	
    	/*                    DIAMOND STUFF                       */
        config.addDefault("Diamond Sword", false);
        config.addDefault("Diamond Axe", false);
        config.addDefault("Diamond Shovel", false);
        config.addDefault("Diamond Pickaxe", false);
        config.addDefault("Diamond Helmet", false);
        config.addDefault("Diamond Chestplate", false);
        config.addDefault("Diamond Leggings", false);
        config.addDefault("Diamond Boots", false);
      	/*--------------------------------------------------------*/
      	
      	/*                    IRON STUFF                       */
        config.addDefault("Iron Sword", false);
        config.addDefault("Iron Axe", false);
        config.addDefault("Iron Shovel", false);
        config.addDefault("Iron Pickaxe", false);
        config.addDefault("Iron Helmet", false);
        config.addDefault("Iron Chestplate", false);
        config.addDefault("Iron Leggings", false);
        config.addDefault("Iron Boots", false);
      	/*--------------------------------------------------------*/
      	
      	/*                    GOLD STUFF                       */
        config.addDefault("Gold Sword", false);
        config.addDefault("Gold Axe", false);
        config.addDefault("Gold Shovel", false);
        config.addDefault("Gold Pickaxe", false);
        config.addDefault("Gold Helmet", false);
        config.addDefault("Gold Chestplate", false);
        config.addDefault("Gold Leggings", false);
        config.addDefault("Gold Boots", false);
      	/*--------------------------------------------------------*/
      	
      	/*                    STONE STUFF                       */
        config.addDefault("Stone Sword", false);
        config.addDefault("Stone Axe", false);
        config.addDefault("Stone Shovel", false);
        config.addDefault("Stone Pickaxe", false);
    	/*--------------------------------------------------------*/
      	
      	/*                    CHAINMAIL STUFF                       */
        config.addDefault("Chainmail Helmet", false);
        config.addDefault("Chainmail Chestplate", false);
        config.addDefault("Chainmail Leggings", false);
        config.addDefault("Chainmail Boots", false);
    	/*--------------------------------------------------------*/
      	
      	/*                    WOODEN STUFF                       */
        config.addDefault("Wooden Sword", false);
        config.addDefault("Wooden Axe", false);
        config.addDefault("Wooden Shovel", false);
        config.addDefault("Wooden Pickaxe", false);
    	/*--------------------------------------------------------*/
      	
      	/*                    LEATHER STUFF                       */
        config.addDefault("Leather Helmet", false);
        config.addDefault("Leather Chestplate", false);
        config.addDefault("Leather Leggings", false);
        config.addDefault("Leather Boots", false);
    	/*--------------------------------------------------------*/
      	
      	/*                    BOOKS                       */
        config.addDefault("Enable Books", false);
    	/*--------------------------------------------------------*/
    	
    	/*                    BOW                       */
        config.addDefault("Bow", false);
    	/*--------------------------------------------------------*/
    	
    	/*                    FISHING ROD                       */
        config.addDefault("Fishing Rod", false);
    	/*--------------------------------------------------------*/
    	
    	/*                    ENCHANTMENTS                       */
        config.addDefault("Arrow Damage", false);
        config.addDefault("Arrow Fire", false);
        config.addDefault("Arrow Infinite", false);
        config.addDefault("Arrow Knockback", false);
        config.addDefault("Damage All", false);
        config.addDefault("Damage Arthropods", false);
        config.addDefault("Damage Undead", false);
        config.addDefault("Dig Speed", false);
        config.addDefault("Durability", false);
        config.addDefault("Fire Aspect", false);
        config.addDefault("Knockback", false);
        config.addDefault("Loot Bonus Blocks", false);
        config.addDefault("Loot Bonus Mobs", false);
        config.addDefault("Oxygen", false);
        config.addDefault("Protection Enviromental", false);
        config.addDefault("Protection Explosions", false);
        config.addDefault("Protection Fall", false);
        config.addDefault("Protection Fire", false);
        config.addDefault("Protection Projectile", false);
        config.addDefault("Silk Touch", false);
        config.addDefault("Thorns", false);
        config.addDefault("Water Worker", false);
		/*--------------------------------------------------------*/

    	/*                    MAX- LEVELS                       */
        config.addDefault("Knockback Max Level", 1);
        config.addDefault("Arrow Damage Max Level", 1);
        config.addDefault("Arrow Fire Max Level", 1);
        config.addDefault("Arrow Knockback Max Level", 1);
        config.addDefault("Silk Touch Max Level", 1);
        config.addDefault("Damage All Max Level", 1);
        config.addDefault("Damage Arthropods Max Level", 1);
        config.addDefault("Damage Undead Max Level", 1);
        config.addDefault("Dig Speed Max Level", 1);
        config.addDefault("Durability Max Level", 1);
        config.addDefault("Fire Aspect Max Level", 1);
        config.addDefault("Loot Mobs Max Level", 1);
        config.addDefault("Loot Blocks Max Level", 1);
        config.addDefault("Protection Max Level",1);
        config.addDefault("Feather Falling Max Level",1);
    	/*--------------------------------------------------------*/

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
        config.options().copyDefaults(true);
        saveConfig();
    }
	/*--------------------------------------------------------*/



    /*                    DISABLE PLUGIN                       */
    public void onDisable() {
        getLogger().info("Enchantment Restrict is now disabled.");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
	/*--------------------------------------------------------*/


    /*                    ENCHANT EVENT                       */
    @EventHandler
    public void onPlayerEnchant(PrepareItemEnchantEvent event){  
    	
    	/*                    SWORDS                       */
        Boolean diamond_sword = getConfig().getBoolean("Diamond Sword");
        Boolean iron_sword = getConfig().getBoolean("Iron Sword");
        Boolean gold_sword = getConfig().getBoolean("Gold Sword");
        Boolean stone_sword = getConfig().getBoolean("Stone Sword");
        Boolean wooden_sword = getConfig().getBoolean("Wooden Sword");
    	/*--------------------------------------------------------*/
    	
    	/*                    PICKAXES                      */
        Boolean diamond_pickaxe = getConfig().getBoolean("Diamond Pickaxe");
        Boolean iron_pickaxe = getConfig().getBoolean("Iron Pickaxe");
        Boolean gold_pickaxe = getConfig().getBoolean("Gold Pickaxe");
        Boolean stone_pickaxe = getConfig().getBoolean("Stone Pickaxe");
        Boolean wooden_pickaxe = getConfig().getBoolean("Wooden Pickaxe");
        Boolean bow = getConfig().getBoolean("Bow");
        Boolean fishing = getConfig().getBoolean("Fishing Rod");
    	/*--------------------------------------------------------*/
    	
    	/*                    AXES                       */
        Boolean diamond_axe = getConfig().getBoolean("Diamond Axe");
        Boolean iron_axe = getConfig().getBoolean("Iron Axe");
        Boolean gold_axe = getConfig().getBoolean("Gold Axe");
        Boolean stone_axe = getConfig().getBoolean("Stone Axe");
        Boolean wooden_axe = getConfig().getBoolean("Wooden Axe");
    	/*--------------------------------------------------------*/
    	
    	/*                    SHOVELS                       */
        Boolean diamond_shovel = getConfig().getBoolean("Diamond Shovel");
        Boolean iron_shovel = getConfig().getBoolean("Iron Shovel");
        Boolean gold_shovel = getConfig().getBoolean("Gold Shovel");
        Boolean stone_shovel = getConfig().getBoolean("Stone Shovel");
        Boolean wooden_shovel = getConfig().getBoolean("Wooden Shovel");
    	/*--------------------------------------------------------*/
    	
    	/*                    HELMETS                       */
        Boolean diamond_helmet = getConfig().getBoolean("Diamond Helmet");
        Boolean iron_helmet = getConfig().getBoolean("Iron Helmet");
        Boolean gold_helmet = getConfig().getBoolean("Gold Helmet");
        Boolean chainmail_helmet = getConfig().getBoolean("Chainmail Helmet");
        Boolean leather_helmet = getConfig().getBoolean("Leather Helmet");
      	/*--------------------------------------------------------*/
      	
      	/*                    CHESTPLATES                       */
        Boolean diamond_chestplate = getConfig().getBoolean("Diamond Chestplate");
        Boolean iron_chestplate = getConfig().getBoolean("Iron Chestplate");
        Boolean gold_chestplate = getConfig().getBoolean("Gold Chestplate");
        Boolean chainmail_chestplate = getConfig().getBoolean("Chainmail Chestplate");
        Boolean leather_chestplate = getConfig().getBoolean("Leather Chestplate");
      	/*--------------------------------------------------------*/
      	
      	/*                    LEGGINGS                       */
        Boolean diamond_leggings = getConfig().getBoolean("Diamond Leggings");
        Boolean iron_leggings = getConfig().getBoolean("Iron Leggings");
        Boolean gold_leggings = getConfig().getBoolean("Gold Leggings");
        Boolean chainmail_leggings = getConfig().getBoolean("Chainmail Leggings");
        Boolean leather_leggings = getConfig().getBoolean("Leather Leggings");
      	/*--------------------------------------------------------*/
      	
      	/*                    BOOTS                       */
        Boolean diamond_boots = getConfig().getBoolean("Diamond Boots");
        Boolean iron_boots = getConfig().getBoolean("Iron Boots");
        Boolean gold_boots = getConfig().getBoolean("Gold Boots");
        Boolean chainmail_boots = getConfig().getBoolean("Chainmail Boots");
        Boolean leather_boots = getConfig().getBoolean("Leather Boots");
      	/*--------------------------------------------------------*/
      	

      	/*                    ENCHANT SWORDS                       */
        if(event.getItem().getType() == Material.DIAMOND_SWORD && diamond_sword == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_SWORD && gold_sword == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_SWORD && iron_sword == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.STONE_SWORD && stone_sword == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.WOOD_SWORD && wooden_sword == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.BOW && bow == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.FISHING_ROD && fishing == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
       	/*                    ENCHANT AXES                       */
        if(event.getItem().getType() == Material.DIAMOND_AXE && diamond_axe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_AXE && gold_axe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_AXE && iron_axe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.STONE_AXE && stone_axe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.WOOD_AXE && wooden_axe == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/

       	/*                    ENCHANT SHOVELS                       */
        if(event.getItem().getType() == Material.DIAMOND_SPADE && diamond_shovel == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_SPADE && gold_shovel == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_SPADE && iron_shovel == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.STONE_SPADE && stone_shovel == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.WOOD_SPADE && wooden_shovel == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
       	/*                    ENCHANT PICKAXES                       */
        if(event.getItem().getType() == Material.DIAMOND_PICKAXE && diamond_pickaxe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_PICKAXE && gold_pickaxe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_PICKAXE && iron_pickaxe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.STONE_PICKAXE && stone_pickaxe == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.WOOD_PICKAXE && wooden_pickaxe == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
       	/*                    ENCHANT HELMETS                       */
        if(event.getItem().getType() == Material.DIAMOND_HELMET && diamond_helmet == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_HELMET && gold_helmet == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_HELMET && iron_helmet == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.CHAINMAIL_HELMET && chainmail_helmet == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.LEATHER_HELMET && leather_helmet == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
       	/*                    ENCHANT CHESTPLATES                       */
        if(event.getItem().getType() == Material.DIAMOND_CHESTPLATE && diamond_chestplate == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_CHESTPLATE && gold_chestplate == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_CHESTPLATE && iron_chestplate == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.CHAINMAIL_CHESTPLATE && chainmail_chestplate == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.LEATHER_CHESTPLATE && leather_chestplate == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
       	/*                    ENCHANT LEGGINGS                       */
        if(event.getItem().getType() == Material.DIAMOND_LEGGINGS && diamond_leggings == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_LEGGINGS && gold_leggings == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_LEGGINGS && iron_leggings == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.CHAINMAIL_LEGGINGS && chainmail_leggings == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.LEATHER_LEGGINGS && leather_leggings == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
       	/*                    ENCHANT BOOTS                       */
        if(event.getItem().getType() == Material.DIAMOND_BOOTS && diamond_boots == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.GOLD_BOOTS && gold_boots == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.IRON_BOOTS && iron_boots == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.CHAINMAIL_BOOTS && chainmail_boots == false){
            event.setCancelled(true);
        }

        if(event.getItem().getType() == Material.LEATHER_BOOTS && leather_boots == false){
            event.setCancelled(true);
        }
       	/*--------------------------------------------------------*/
       	
    	/*                    ENCHANT BOOKS                       */

        if(event.getEnchanter().hasPermission("ERestrict.Admin") || event.getEnchanter().hasPermission("ERestrict.EnchantBooks") || event.getEnchanter().isOp()) {
            event.setCancelled(false);
        }
       	/*--------------------------------------------------------*/
    }
    
    /*                    INVENTORY CLICK EVENT (ANVIL REPAIR)                     */

    @EventHandler
    public static void onInventoryClickAnvil(InventoryClickEvent event){
        if(!event.isCancelled()){
            HumanEntity entity = event.getWhoClicked();
            Player player = (Player)entity;
            Inventory inventory = event.getInventory();
            if(inventory instanceof AnvilInventory){
                AnvilInventory anvil = (AnvilInventory)inventory;
                InventoryView view = event.getView();
                int rawSlot = event.getRawSlot();
                if(rawSlot == view.convertSlot(rawSlot)){
                    if(rawSlot == 2){
                        ItemStack[] items = anvil.getContents();
                        ItemStack item1 = items[0];
                        ItemStack item2 = items[1];
                        if(item1 != null && item2 != null){
                            int id1 = item1.getTypeId();
                            int id2 = item2.getTypeId();
                            if(id1 != 0 && id1 == id2){
                                ItemStack item3 = event.getCurrentItem();
                                if(item3 != null){
                                    ItemMeta meta = item3.getItemMeta();
                                    if(meta != null){
                                        if(meta instanceof Repairable){
                                            Repairable repairable = (Repairable)meta;
                                            int repairCost = repairable.getRepairCost();
                                            if(player.getLevel() >= repairCost){
                                                if(event.getWhoClicked().hasPermission("ERestrict.Admin") || event.getWhoClicked().hasPermission("ERestrict.Repair") || event.getWhoClicked().isOp()){
                                                    event.setCancelled(false);
                                                }else{
                                                    event.setCancelled(true);
                                                }
                                            }else{
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    
	/*--------------------------------------------------------*/

    /*                    MOVE EVENT (BOOKS)                     */
    @EventHandler
    public void onMoveBooks(PlayerMoveEvent event){
        Boolean books = getConfig().getBoolean("Enable Books");
        if(!event.getPlayer().isOp() || !event.getPlayer().hasPermission("ERestrict.Admin") || books == false){
            if(event.getPlayer().getInventory().contains(Material.ENCHANTED_BOOK)){
                event.getPlayer().getInventory().remove(Material.ENCHANTED_BOOK);
                event.getPlayer().sendMessage(ChatColor.RED + "Removed Enchanted Book!");
            }
        }else {}
    }
    /*--------------------------------------------------------*/

    /*                    MOVE EVENT (ENCHANTMENTS)                     */
    @EventHandler
    public void onMoveEnchantments(PlayerMoveEvent event){
        try {
            ItemStack item = event.getPlayer().getItemInHand();
    	
    	/*                    ENCHANTMENTS                       */
            Boolean arrow_damage = getConfig().getBoolean("Arrow Damage");
            Boolean arrow_fire = getConfig().getBoolean("Arrow Fire");
            Boolean arrow_infinite = getConfig().getBoolean("Arrow Infinite");
            Boolean arrow_knockback = getConfig().getBoolean("Arrow Knockback");
            Boolean damage_all = getConfig().getBoolean("Damage All");
            Boolean damage_arthropods = getConfig().getBoolean("Damage Arthropods");
            Boolean damage_undead = getConfig().getBoolean("Damage Undead");
            Boolean dig_speed = getConfig().getBoolean("Dig Speed");
            Boolean durability = getConfig().getBoolean("Durability");
            Boolean fire_aspect = getConfig().getBoolean("Fire Aspect");
            Boolean knockback = getConfig().getBoolean("Knockback");
            Boolean loot_blocks = getConfig().getBoolean("Loot Bonus Blocks");
            Boolean loot_mobs = getConfig().getBoolean("Loot Bonus Mobs");
            Boolean oxygen = getConfig().getBoolean("Oxygen");
            Boolean protection_enviromental = getConfig().getBoolean("Protection Enviromental");
            Boolean protection_explosions = getConfig().getBoolean("Protection Explosions");
            Boolean protection_fall = getConfig().getBoolean("Protection Fall");
            Boolean protection_fire = getConfig().getBoolean("Protection Fire");
            Boolean protection_projectile = getConfig().getBoolean("Protection Projectile");
            Boolean silk_touch = getConfig().getBoolean("Silk Touch");
            Boolean thorns = getConfig().getBoolean("Thorns");
            Boolean water_worker = getConfig().getBoolean("Water Worker");
            Boolean depth_strider = getConfig().getBoolean("Depth Strider");
            Boolean luck = getConfig().getBoolean("Luck");
            Boolean lure = getConfig().getBoolean("Lure");

            Integer knockbackLevel = getConfig().getInt("Knockback Max Level");
            Integer arrow_damageLevel = getConfig().getInt("Arrow Damage Max Level");
            Integer arrow_fireLevel = getConfig().getInt("Arrow Fire Max Level");
            Integer silk_touchLevel = getConfig().getInt("Silk Touch Max Level");
            Integer arrow_knockbackLevel = getConfig().getInt("Arrow Knockback Max Level");
            Integer damage_allLevel = getConfig().getInt("Damage All Max Level");
            Integer damage_arthropodsLevel = getConfig().getInt("Damage Arthropods Max Level");
            Integer damage_undeadLevel = getConfig().getInt("Damage Undead Max Level");
            Integer dig_speedLevel = getConfig().getInt("Dig Speed Max Level");
            Integer durabilityLevel = getConfig().getInt("Durability Max Level");
            Integer fire_aspectLevel = getConfig().getInt("Fire Aspect Max Level");
            Integer loot_mobsLevel = getConfig().getInt("Loot Mobs Max Level");
            Integer loot_blocksLevel = getConfig().getInt("Loot Blocks Max Level");
            Integer protLevel = getConfig().getInt("Protection Max Level");
            Integer featherFallLevel = getConfig().getInt("Feather Falling Max Level");
		/*--------------------------------------------------------*/
            if(item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)>protLevel && !event.getPlayer().isOp()){
                item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,protLevel);
            }
            if(item.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS)>protLevel && !event.getPlayer().isOp()){
                item.removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
                item.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS,protLevel);
            }
            if(item.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)>protLevel && !event.getPlayer().isOp()){
                item.removeEnchantment(Enchantment.PROTECTION_FIRE);
                item.addEnchantment(Enchantment.PROTECTION_FIRE,protLevel);
            }
            if(item.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE)>protLevel && !event.getPlayer().isOp()){
                item.removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
                item.addEnchantment(Enchantment.PROTECTION_PROJECTILE,protLevel);
            }
            if(item.getEnchantmentLevel(Enchantment.PROTECTION_FALL)>featherFallLevel && !event.getPlayer().isOp()){
                item.removeEnchantment(Enchantment.PROTECTION_FALL);
                item.addEnchantment(Enchantment.PROTECTION_FALL,protLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) > loot_blocksLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
                item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, loot_blocksLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) > loot_mobsLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);
                item.addEnchantment(Enchantment.LOOT_BONUS_MOBS, loot_mobsLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.FIRE_ASPECT) > fire_aspectLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.FIRE_ASPECT);
                item.addEnchantment(Enchantment.FIRE_ASPECT, fire_aspectLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.DURABILITY) > durabilityLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DURABILITY);
                item.addEnchantment(Enchantment.DURABILITY, durabilityLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.DIG_SPEED) > dig_speedLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DIG_SPEED);
                item.addEnchantment(Enchantment.DIG_SPEED, dig_speedLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) > damage_undeadLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DAMAGE_UNDEAD);
                item.addEnchantment(Enchantment.DAMAGE_UNDEAD, damage_undeadLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > damage_allLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DAMAGE_ALL);
                item.addEnchantment(Enchantment.DAMAGE_ALL, damage_allLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) > damage_arthropodsLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DAMAGE_ARTHROPODS);
                item.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, damage_arthropodsLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.KNOCKBACK) > knockbackLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.KNOCKBACK);
                item.addEnchantment(Enchantment.KNOCKBACK, knockbackLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) > arrow_damageLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_DAMAGE);
                item.addEnchantment(Enchantment.ARROW_DAMAGE, arrow_damageLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.ARROW_FIRE) > arrow_fireLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_FIRE);
                item.addEnchantment(Enchantment.ARROW_FIRE, arrow_fireLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) > arrow_knockbackLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_KNOCKBACK);
                item.addEnchantment(Enchantment.ARROW_KNOCKBACK, arrow_knockbackLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) > arrow_damageLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_DAMAGE);
                item.addEnchantment(Enchantment.ARROW_DAMAGE, arrow_damageLevel);
            }
            if (item.getEnchantmentLevel(Enchantment.SILK_TOUCH) > silk_touchLevel && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.SILK_TOUCH);
                item.addEnchantment(Enchantment.SILK_TOUCH, silk_touchLevel);
            }

            if (arrow_damage == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_DAMAGE);
            }
            if (arrow_fire == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_FIRE);
            }
            if (arrow_infinite == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_INFINITE);
            }
            if (arrow_knockback == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.ARROW_KNOCKBACK);
            }
            if (damage_all == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DAMAGE_ALL);
            }
            if (damage_arthropods == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DAMAGE_ARTHROPODS);
            }
            if (damage_undead == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DAMAGE_UNDEAD);
            }
            if (dig_speed == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DIG_SPEED);
            }
            if (durability == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.DURABILITY);
            }
            if (fire_aspect == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.FIRE_ASPECT);
            }
            if (knockback == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.KNOCKBACK);
            }
            if (loot_blocks == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
            }
            if (loot_mobs == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);
            }
            if (silk_touch == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.SILK_TOUCH);
            }
            if (luck == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.LUCK);
            }
            if (lure == false && !event.getPlayer().isOp()) {
                item.removeEnchantment(Enchantment.LURE);
            }
            if (oxygen == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.OXYGEN) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.OXYGEN)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.OXYGEN) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.OXYGEN)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.OXYGEN);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.OXYGEN);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.OXYGEN);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.OXYGEN);
                }
            }

            if (protection_enviromental == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                }
            }
            if (protection_explosions == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.OXYGEN) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.OXYGEN)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.OXYGEN) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.OXYGEN)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
                }
            }
            if (protection_fall == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_FALL) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.PROTECTION_FALL)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.PROTECTION_FALL) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.PROTECTION_FALL)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.PROTECTION_FALL);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.PROTECTION_FALL);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.PROTECTION_FALL);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.PROTECTION_FALL);
                }
            }
            if (protection_fire == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_FIRE) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.PROTECTION_FIRE)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.PROTECTION_FIRE) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.PROTECTION_FIRE)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.PROTECTION_FIRE);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.PROTECTION_FIRE);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.PROTECTION_FIRE);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.PROTECTION_FIRE);
                }
            }
            if (protection_projectile == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_PROJECTILE) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.PROTECTION_PROJECTILE)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.PROTECTION_PROJECTILE) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.PROTECTION_PROJECTILE)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
                }
            }
            if (water_worker == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.WATER_WORKER) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.WATER_WORKER)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.WATER_WORKER) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.WATER_WORKER)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.WATER_WORKER);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.WATER_WORKER);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.WATER_WORKER);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.WATER_WORKER);
                }
            }
            if (thorns == false && !event.getPlayer().isOp()) {
                if (event.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.THORNS) || event.getPlayer().getInventory().getLeggings().containsEnchantment(Enchantment.THORNS)
                        || event.getPlayer().getInventory().getChestplate().containsEnchantment(Enchantment.THORNS) || event.getPlayer().getInventory().getHelmet().containsEnchantment(Enchantment.THORNS)) {
                    event.getPlayer().getInventory().getBoots().removeEnchantment(Enchantment.THORNS);
                    event.getPlayer().getInventory().getLeggings().removeEnchantment(Enchantment.THORNS);
                    event.getPlayer().getInventory().getChestplate().removeEnchantment(Enchantment.THORNS);
                    event.getPlayer().getInventory().getHelmet().removeEnchantment(Enchantment.THORNS);
                }
            }
          }catch(Exception ii){}
    }
    /*--------------------------------------------------------*/

    /*                    PICKUP ITEM EVENT                       */
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event){
        Boolean books = getConfig().getBoolean("Enable Books");
        if(!event.getPlayer().isOp() || !event.getPlayer().hasPermission("ERestrict.Admin") || books == false){
            if(event.getPlayer().getInventory().contains(Material.ENCHANTED_BOOK)){
                event.getPlayer().getInventory().remove(Material.ENCHANTED_BOOK);
                event.getPlayer().sendMessage(ChatColor.RED + "Removed Enchanted Book!");
            }
        }else {}
    }
    /*--------------------------------------------------------*/

    /*                    INVENTORY INTERACT EVENT                       */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Boolean books = getConfig().getBoolean("Enable Books");
        if(!event.getWhoClicked().isOp() || !event.getWhoClicked().hasPermission("ERestrict.Admin") || books == false){
            if(event.getWhoClicked().getInventory().contains(Material.ENCHANTED_BOOK)){
                event.getWhoClicked().getInventory().remove(Material.ENCHANTED_BOOK);
                ((CommandSender) event.getWhoClicked()).sendMessage(ChatColor.RED + "Removed Enchanted Book!");
            }
        }else {}
    }
    /*--------------------------------------------------------*/

    /*                    COMMANDS                       */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player)sender;
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("ERestrict");
        ItemStack item = player.getItemInHand();
    	
    	 /*                    RELOAD COMMAND                       */
        if(cmd.getName().equalsIgnoreCase("erreload")){
            if(player.hasPermission("ERestrict.Admin") || player.isOp()){
                plugin.reloadConfig();
                player.sendMessage("Enchantment Restrict successfully reloaded!");
            }
        }
	
 	 /*                    LIBRARY COMMAND                       */
        if(cmd.getName().equalsIgnoreCase("table")){
            if(player.hasPermission("ERestrict.Admin") || player.isOp()){
                Location loc = player.getLocation();
                Block block = loc.getBlock();
                loc.getBlock().setType(Material.ENCHANTMENT_TABLE);

                Block playerBlock = player.getLocation().getBlock();
                Block blockUnder = playerBlock.getRelative(BlockFace.DOWN);
                blockUnder.setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.EAST).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.WEST).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.NORTH).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.SOUTH).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).setType(Material.BOOKSHELF);
                blockUnder.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).setType(Material.BOOKSHELF);

                Location loca = player.getLocation();
                Location location = new Location(player.getWorld(), loca.getX(), loca.getY() + 1, loca.getZ());
                player.teleport(location);
            }
        }
	
		/*                    UNENCHANT COMMAND                       */
        if(cmd.getName().equalsIgnoreCase("unenchant")){
            if(player.hasPermission("ERestrict.Admin") || player.isOp() || player.hasPermission("ERestrict.Unenchant")){

                item.removeEnchantment(Enchantment.ARROW_DAMAGE);
                item.removeEnchantment(Enchantment.ARROW_FIRE);
                item.removeEnchantment(Enchantment.ARROW_INFINITE);
                item.removeEnchantment(Enchantment.ARROW_KNOCKBACK);
                item.removeEnchantment(Enchantment.DAMAGE_ALL);
                item.removeEnchantment(Enchantment.DAMAGE_ARTHROPODS);
                item.removeEnchantment(Enchantment.DAMAGE_UNDEAD);
                item.removeEnchantment(Enchantment.DIG_SPEED);
                item.removeEnchantment(Enchantment.DURABILITY);
                item.removeEnchantment(Enchantment.FIRE_ASPECT);
                item.removeEnchantment(Enchantment.KNOCKBACK);
                item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
                item.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);
                item.removeEnchantment(Enchantment.OXYGEN);
                item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                item.removeEnchantment(Enchantment.PROTECTION_EXPLOSIONS);
                item.removeEnchantment(Enchantment.PROTECTION_FALL);
                item.removeEnchantment(Enchantment.PROTECTION_FIRE);
                item.removeEnchantment(Enchantment.PROTECTION_PROJECTILE);
                item.removeEnchantment(Enchantment.SILK_TOUCH);
                item.removeEnchantment(Enchantment.THORNS);
                item.removeEnchantment(Enchantment.WATER_WORKER);

                player.sendMessage(ChatColor.GREEN + "Successfully unenchanted " + player.getItemInHand().getType());
            }
        }
        return true;
    }
    /*--------------------------------------------------------*/
}
