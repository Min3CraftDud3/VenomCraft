package com.sinfulpixel.Merchants;

import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.MerchantRecipe;
import net.minecraft.server.v1_7_R4.MerchantRecipeList;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Field;

/**
 * Created by Min3 on 2/10/2015.
 */
public class Trades {
        public static void setTrades(Villager v, Inventory inv)
        {
            EntityVillager villager = ((CraftVillager)v).getHandle();
            try
            {
                Field recipes = villager.getClass().getDeclaredField("bu");
                recipes.setAccessible(true);

                MerchantRecipeList list = new MerchantRecipeList();

                for (int i = 0; i < 9; i++) {
                    if ((inv.getItem(i) != null) && (inv.getItem(i + 18) != null)) {
                        if (inv.getItem(i + 9) != null) {
                            ItemStack item1 = CraftItemStack.asNMSCopy(inv
                                    .getItem(i));
                            ItemStack item2 = CraftItemStack.asNMSCopy(inv
                                    .getItem(i + 9));
                            ItemStack item3 = CraftItemStack.asNMSCopy(inv
                                    .getItem(i + 18));
                            list.a(new MerchantRecipe(item1, item2, item3));
                        } else {
                            ItemStack item1 = CraftItemStack.asNMSCopy(inv
                                    .getItem(i));
                            ItemStack item3 = CraftItemStack.asNMSCopy(inv
                                    .getItem(i + 18));
                            list.a(new MerchantRecipe(item1, item3));
                        }
                    }
                }

                recipes.set(villager, list);
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }