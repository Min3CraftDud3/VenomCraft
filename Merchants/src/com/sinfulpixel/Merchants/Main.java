package com.sinfulpixel.Merchants;

import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
    ArrayList<Chunk> loadedChunks = new ArrayList();

    public void onEnable() {
        Bukkit.getServer().getPluginManager()
                .registerEvents(this, this);
        int j;
        int i;
        for (Iterator localIterator = Bukkit.getWorlds().iterator(); localIterator.hasNext(); i < j)
        {
            World worlds = (World)localIterator.next();
            Chunk[] arrayOfChunk;
            j = (arrayOfChunk = worlds.getLoadedChunks()).length; i = 0; continue; Chunk chunks = arrayOfChunk[i];
            checkChunk(chunks);

            i++;
        }

        Bukkit.getServer().getScheduler()
                .scheduleSyncRepeatingTask(this, new Runnable() {
                    public void run() {
                        for (Merchant m : Merchant.merchantList) {
                            Villager v = Merchant.getVillager(m);
                            Location currentLoc = v.getLocation();
                            Location defaultLoc = Merchant.getLocation(m);

                            if (((currentLoc.getBlockX() != defaultLoc
                                    .getBlockX() ? 1 : 0) | (
                                    currentLoc.getBlockY() != defaultLoc
                                            .getBlockY() ? 1 : 0) | (
                                    currentLoc.getBlockZ() != defaultLoc
                                            .getBlockZ() ? 1 : 0)) != 0)
                            {
                                v.teleport(defaultLoc);
                            }
                        }
                    }
                }
                        , 0L, 3L);
    }

    void checkChunk(Chunk chunk) {
        for (BlockState blockStates : chunk.getTileEntities()) {
            Block block = blockStates.getBlock();
            if ((block.getType() == Material.CHEST) &&
                    (block.getRelative(BlockFace.UP).getType() == Material.SIGN_POST)) {
                Sign s = (Sign)block.getRelative(BlockFace.UP).getState();
                if ((s.getLine(0).equalsIgnoreCase("[merchant]")) &&
                        (!s.getLine(1).isEmpty())) {
                    Chest c = (Chest)block.getState();
                    Inventory i = Bukkit.createInventory(null,
                            InventoryType.CHEST);
                    i.setContents(c.getBlockInventory().getContents());
                    Villager v = (Villager)chunk.getWorld().spawnEntity(
                            block.getLocation().add(0.5D, 0.0D, 0.5D),
                            EntityType.VILLAGER);
                    Merchant.newMerchant(v, c, s, i);
                }
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (label.equalsIgnoreCase("merchant")) {
            Player player = (Player)sender;
            boolean validCommand = false;
            ArrayList<Block> blocksInSight = new ArrayList();
            for (Block blocks : player.getLineOfSight(null, 10))
                blocksInSight.add(blocks);
            Sign s;
            Inventory i;
            for (Block blocks : blocksInSight) {
                if (blocks.getType() == Material.CHEST) {
                    s = (Sign)blocks.getRelative(BlockFace.UP).getState();
                    if ((s.getLine(0).equalsIgnoreCase("[merchant]")) &&
                            (!s.getLine(1).isEmpty())) {
                        Chest c = (Chest)blocks.getState();
                        i = Bukkit.createInventory(null,
                                InventoryType.CHEST);
                        i.setContents(c.getBlockInventory().getContents());
                        Villager v = (Villager)player.getWorld().spawnEntity(
                                blocks.getLocation().add(0.5D, 0.0D, 0.5D),
                                EntityType.VILLAGER);
                        Merchant.newMerchant(v, c, s, i);
                        player.sendMessage(ChatColor.GREEN +
                                "Merchant created!");
                        validCommand = true;
                    }
                }
            }

            if (!validCommand) {
                Entity lookingAt = null;
                label435: for (s = player.getNearbyEntities(6.0D, 6.0D, 6.0D).iterator(); s.hasNext();i.hasNext()){
                    Entity nearbyEntities = (Entity)s.next();
                    if ((nearbyEntities.getType() != EntityType.VILLAGER) ||
                            (!Merchant.isMerchant((Villager)nearbyEntities))) break label435;
                    i = blocksInSight.iterator(); continue; Block blocks = (Block)i.next();

                    if ((blocks.equals(nearbyEntities.getLocation()
                            .getBlock()) |
                            blocks.equals(((Villager)nearbyEntities)
                                    .getEyeLocation().getBlock()))) {
                        lookingAt = nearbyEntities;
                    }

                }

                if (lookingAt != null) {
                    removeMerchant((Villager)lookingAt, player);
                    validCommand = true;
                }
            }
            if (!validCommand) {
                player.sendMessage(ChatColor.RED +
                        "No chest or merchant in sight.");
            }
        }
        return false;
    }

    @EventHandler
    private void onEntityDamage(EntityDamageEvent event) {
        if ((event.getEntity().getType() == EntityType.VILLAGER) &&
                (Merchant.isMerchant((Villager)event.getEntity())))
        {
            event.setCancelled(true);
        }
    }

    static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        if ((Integer.parseInt(s) <= 5) && (Integer.parseInt(s) >= 0)) {
            return true;
        }
        return false;
    }

    public void onDisable()
    {
        for (Merchant m : Merchant.merchantList) {
            Merchant.getVillager(m).remove();
            Merchant.getVillager(m).getWorld()
                    .getBlockAt(Merchant.getVillager(m).getLocation())
                    .setType(Material.CHEST);
            Chest chest = (Chest)Merchant.getVillager(m).getWorld()
                    .getBlockAt(Merchant.getVillager(m).getLocation())
                    .getState();
            chest.getBlockInventory().setContents(
                    Merchant.getInventory(m).getContents());
            chest.getBlock().getRelative(BlockFace.UP)
                    .setType(Material.SIGN_POST);
            Sign sign = (Sign)chest.getBlock().getRelative(BlockFace.UP)
                    .getState();
            sign.setLine(0, SignData.getLine0(Merchant.getSignData(m)));
            sign.setLine(1, SignData.getLine1(Merchant.getSignData(m)));
            sign.setLine(2, SignData.getLine2(Merchant.getSignData(m)));
            sign.setLine(3, SignData.getLine3(Merchant.getSignData(m)));
            sign.update();
        }
    }

    @EventHandler
    private void onInteract(PlayerInteractEntityEvent event)
    {
        if ((event.getRightClicked().getType() == EntityType.VILLAGER) &&
                (Merchant.isMerchant((Villager)event.getRightClicked())) &&
                (event.getPlayer().isSneaking()) &&
                (event.getPlayer().getGameMode() == GameMode.CREATIVE) &&
                (event.getPlayer().hasPermission("merchant.admin"))) {
            event.setCancelled(true);
            if (event.getPlayer().getItemInHand().getType() == Material.WOOL) {
                boolean changedColour = false;

                byte data = event.getPlayer().getItemInHand().getData()
                        .getData();
                if (data == 12) {
                    Merchant.setProfession((Villager)event.getRightClicked(),
                            0);
                    changedColour = true;
                } else if (data == 0) {
                    Merchant.setProfession((Villager)event.getRightClicked(),
                            1);
                    changedColour = true;
                } else if (((data == 10 ? 1 : 0) | (data == 2 ? 1 : 0) | (data == 6 ? 1 : 0)) != 0) {
                    Merchant.setProfession((Villager)event.getRightClicked(),
                            2);
                    changedColour = true;
                } else if (((data == 15 ? 1 : 0) | (data == 7 ? 1 : 0)) != 0) {
                    Merchant.setProfession((Villager)event.getRightClicked(),
                            3);
                    changedColour = true;
                } else if (data == 8) {
                    Merchant.setProfession((Villager)event.getRightClicked(),
                            4);
                    changedColour = true;
                } else if (((data == 5 ? 1 : 0) | (data == 13 ? 1 : 0)) != 0) {
                    Merchant.setProfession((Villager)event.getRightClicked(),
                            5);
                    changedColour = true;
                }
                if (changedColour)
                    event.getPlayer().sendMessage(
                            ChatColor.GREEN + "Profession updated!");
                else
                    event.getPlayer().sendMessage(
                            ChatColor.RED + "Invalid profession colour.");
            }
            else
            {
                event.getPlayer().openInventory(
                        Merchant.getInventory(
                                (Merchant)Merchant.villagerMerchants
                                        .get((Villager)event.getRightClicked())));
            }
        }
    }

    @EventHandler
    private void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.CHEST) {
            BlockState bs = event.getBlock().getRelative(BlockFace.UP)
                    .getState();
            if ((bs instanceof Sign)) {
                Sign s = (Sign)event.getBlock().getRelative(BlockFace.UP)
                        .getState();
                if ((s.getLine(0).equalsIgnoreCase("[merchant]")) &&
                        (!s.getLine(1).isEmpty())) {
                    event.setCancelled(true);
                    if (event.getPlayer().hasPermission("merchant.admin")) {
                        event.getBlock().getRelative(BlockFace.UP)
                                .setType(Material.AIR);
                        Chest chest = (Chest)event.getBlock().getState();
                        chest.getBlockInventory().clear();
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(
                                ChatColor.GREEN + "Merchant removed.");
                    }
                }
            }
        }
    }

    @EventHandler
    private void onCloseInventory(InventoryCloseEvent event) {
        for (Merchant merchants : Merchant.merchantList)
            if ((Merchant.getInventory(merchants).equals(event.getInventory())) &&
                    (event.getPlayer().hasPermission("merchant.admin"))) {
                Trades.setTrades(Merchant.getVillager(merchants),
                        event.getInventory());
                ((CommandSender)event.getPlayer()).sendMessage(ChatColor.GREEN +
                        "Trades Updated.");
            }
    }

    private void removeMerchant(Villager v, Player player)
    {
        if (Merchant.villagerMerchants.containsKey(v)) {
            Merchant m = (Merchant)Merchant.villagerMerchants.get(v);

            v.getWorld().getBlockAt(v.getLocation())
                    .setType(Material.CHEST);
            Chest chest = (Chest)v.getWorld()
                    .getBlockAt(v.getLocation())
                    .getState();
            chest.getBlockInventory().setContents(
                    Merchant.getInventory(m).getContents());
            chest.getBlock().getRelative(BlockFace.UP)
                    .setType(Material.SIGN_POST);
            Sign sign = (Sign)chest.getBlock().getRelative(BlockFace.UP)
                    .getState();
            sign.setLine(0, SignData.getLine0(Merchant.getSignData(m)));
            sign.setLine(1, SignData.getLine1(Merchant.getSignData(m)));
            sign.setLine(2, SignData.getLine2(Merchant.getSignData(m)));
            sign.setLine(3, SignData.getLine3(Merchant.getSignData(m)));
            sign.update();
            Merchant.merchantList.remove(m);
            v.remove();

            player.sendMessage(ChatColor.GREEN + "Merchant reset!");
        }
    }

    @EventHandler
    private void onTarget(EntityTargetEvent event) {
        if ((event.getTarget() != null) &&
                (event.getTarget().getType() == EntityType.VILLAGER) &&
                (Merchant.isMerchant((Villager)event.getTarget())))
            event.setCancelled(true);
    }

    @EventHandler
    private void onChunkLoad(ChunkLoadEvent event)
    {
        if (!this.loadedChunks.contains(event.getChunk())) {
            checkChunk(event.getChunk());
            this.loadedChunks.add(event.getChunk());
        }
    }
}