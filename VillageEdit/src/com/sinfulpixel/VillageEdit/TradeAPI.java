package com.sinfulpixel.VillageEdit;


import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.MerchantRecipe;
import net.minecraft.server.v1_7_R4.MerchantRecipeList;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Villager;

import java.lang.reflect.Field;

/**
 * Created by Min3 on 2/9/2015.
 */
public final class TradeAPI {
    public static void ClearTrades(Villager v){
        try{
            EntityVillager entityVillager = ((CraftVillager)v).getHandle();
            Field recipies = entityVillager.getClass().getDeclaredField("bu");
            recipies.setAccessible(true);
            MerchantRecipeList list = new MerchantRecipeList();
            recipies.set(entityVillager,list);
        } catch (Exception e) {
            System.out.println("|||=========[ Error Clearing Trades ]=========|||");
            e.printStackTrace();
            System.out.println("|||=========[ End Error ]=========|||");
        }
    }
    public static void addTrade(Villager v, VillagerTrade villagerTrade){
        EntityVillager entityVillager = ((CraftVillager)v).getHandle();
        try{
            Field recipes = entityVillager.getClass().getDeclaredField("bu");
            recipes.setAccessible(true);
            MerchantRecipeList list = (MerchantRecipeList)recipes.get(entityVillager);
            if(VillagerTrade.hasItem2(villagerTrade)){
                ItemStack item1 = CraftItemStack.asNMSCopy(VillagerTrade.getItem1(villagerTrade));
                ItemStack item2 = CraftItemStack.asNMSCopy(VillagerTrade.getItem2(villagerTrade));
                ItemStack rewardItem = CraftItemStack.asNMSCopy(VillagerTrade.getRewardItem(villagerTrade));
                list.a(new MerchantRecipe(item1,item2,rewardItem));
            }else{
                ItemStack item1 = CraftItemStack.asNMSCopy(VillagerTrade.getItem1(villagerTrade));
                ItemStack rewardItem = CraftItemStack.asNMSCopy(VillagerTrade.getRewardItem(villagerTrade));
                list.a(new MerchantRecipe(item1,rewardItem));
            }
            recipes.set(entityVillager,list);
        }catch(Exception e){
            System.out.println("|||=========[ Error Adding Trade ]=========|||");
            e.printStackTrace();
            System.out.println("|||=========[ End Error ]=========|||");
        }
    }
}
