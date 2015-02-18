package com.sinfulpixel.Merchants;

import net.minecraft.server.v1_7_R4.EntityVillager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftVillager;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class Merchant
{
    static ArrayList<Merchant> merchantList = new ArrayList();
    static HashMap<Villager, Merchant> villagerMerchants = new HashMap();
    private Villager villager;
    private Location location;
    private Inventory inventory;
    private SignData signData;

    public Merchant(Villager villager, Location location, Inventory inventory, SignData signData)
    {
        this.location = location;
        this.inventory = inventory;
        this.signData = signData;
        this.villager = villager;
    }

    public static Location getLocation(Merchant merchant) {
        return merchant.location;
    }

    public static Inventory getInventory(Merchant merchant) {
        return merchant.inventory;
    }

    public static SignData getSignData(Merchant merchant) {
        return merchant.signData;
    }

    public static Villager getVillager(Merchant merchant) {
        return merchant.villager;
    }

    public static int getProffesion(Merchant merchant)
    {
        if (getVillager(merchant).getProfession() != null) {
            return getVillager(merchant).getProfession().getId();
        }
        return 5;
    }

    public static boolean isMerchant(Villager villager)
    {
        return villagerMerchants.containsKey(villager);
    }

    public static void setProfession(Villager villager, int profession) {
        EntityVillager v = ((CraftVillager)villager).getHandle();
        v.setProfession(profession);
        SignData.setLine2(
                getSignData((Merchant)villagerMerchants.get(villager)),
                profFromInt(profession));
    }
    public static String profFromInt(int i){
        String s = "";
        switch(i){
            case 0:
                s = "BLACKSMITH";
            break;
            case 1:
                s = "BUTCHER";
                break;
            case 2:
                s = "FARMER";
                break;
            case 3:
                s="LIBRARIAN";
                break;
            case 4:
                s="PRIEST";
                break;
        }
        return s;
    }
    public static void newMerchant(Villager v, Chest c, Sign s, Inventory i) {
        v.setCustomName(s.getLine(1).replaceAll("&", "§"));
        v.setCustomNameVisible(true);
        v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999,
                999));

        int profession = 0;

        if ((!s.getLine(2).isEmpty()) && (Main.isInteger(s.getLine(2)))) {
            profession = Integer.parseInt(s.getLine(2));
        }

        Merchant m = new Merchant(v, v.getLocation(), i, new SignData(
                s.getLine(0), s.getLine(1), s.getLine(2), ""));
        s.getBlock().setType(Material.AIR);
        c.getBlockInventory().clear();
        v.getLocation().getBlock().setType(Material.AIR);
        merchantList.add(m);
        villagerMerchants.put(v, m);

        Trades.setTrades(v, getInventory(m));
        setProfession(v, profession);
    }
}