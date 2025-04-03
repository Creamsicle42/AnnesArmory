package com.creamsicle.annesarmory;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AnvilRecipeManager {
    @SubscribeEvent
    public static void updateAnvil(AnvilUpdateEvent event) {
        //System.out.println("The anvil event is working");
        //System.out.println("Setting name to " + event.getName());

        int costTotal = 0;
        ItemStack leftItem = event.getLeft().copy();
        ItemStack rightItem = event.getRight().copy();

        // Check if working with a single item, or a tool and a modifier item...
        if (leftItem.isEmpty() ^ rightItem.isEmpty()) {
            // Hey I used xor outside of a comp-sci class, what are the odds
            anvilSingleItem(
                    rightItem.isEmpty() ? leftItem : rightItem,
                    event
            );
            return;
        }

        if (EnchantmentHelper.canStoreEnchantments(leftItem) && !rightItem.isEmpty()) {
            anvilToolUse(leftItem, rightItem, event);
            return;
        }


        System.out.println("No applicable overrides");
    }

    private static void anvilSingleItem(ItemStack singleItem, AnvilUpdateEvent event) {
        // If has name component, and name sent is null or blank, clear name
        if (event.getName() != null && event.getName().isEmpty() && singleItem.has(DataComponents.CUSTOM_NAME)) {
            singleItem.remove(DataComponents.CUSTOM_NAME);
            event.setOutput(singleItem);
            event.setCost(1);
            event.setMaterialCost(0);
        }else if (event.getName() != null && !event.getName().isEmpty() && !event.getName().equals(singleItem.getDisplayName().getString())) {
            singleItem.set(DataComponents.CUSTOM_NAME, Component.literal(event.getName()));
            event.setOutput(singleItem);
            event.setCost(1);
            event.setMaterialCost(0);
        }
    }

    private static void anvilToolUse(ItemStack toolItem, ItemStack modifierItem, AnvilUpdateEvent event) {
        boolean didSomething = false;

        // Process item renaming
        String nameSub = event.getName();
        if (nameSub != null && !nameSub.isEmpty()) {
            if (!toolItem.getDisplayName().getString().equals(nameSub)) {
                toolItem.set(DataComponents.CUSTOM_NAME, Component.literal(nameSub));
                didSomething = true;
            }

        }

        if (nameSub != null && nameSub.isEmpty() && toolItem.has(DataComponents.CUSTOM_NAME)) {
            didSomething = true;
            toolItem.remove(DataComponents.CUSTOM_NAME);
        }

        if (toolItem.getItem() == Items.ENCHANTED_BOOK && modifierItem.getItem() == Items.ENCHANTED_BOOK) {
            event.setCost(1);
            event.setMaterialCost(1);

            ItemEnchantments modEnch = EnchantmentHelper.getEnchantmentsForCrafting(modifierItem);
            ItemEnchantments.Mutable toolEnch = new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(toolItem));
            for (Holder<Enchantment> e : modEnch.keySet()) {


                boolean incompFlag = false;

                for (Holder<Enchantment> e2 : toolEnch.keySet()) {
                    if (!Enchantment.areCompatible(e, e2)) {
                        incompFlag = true;
                    }
                }

                if (incompFlag) {
                    continue;
                }

                //System.out.println("Adding ench to book " + e.getRegisteredName());
                if (modEnch.getLevel(e) > toolEnch.getLevel(e)) {
                    toolEnch.set(e, modEnch.getLevel(e));
                    didSomething = true;
                }
            }

            EnchantmentHelper.setEnchantments(toolItem, toolEnch.toImmutable());
            event.setOutput(toolItem);
            if (!didSomething) {
                event.setCanceled(true);
            }
            return;
        }

        if (modifierItem.getItem() == Items.ENCHANTED_BOOK) {
            System.out.println("Doing book ench");
            int preEnchantCost = curveEnchCost(getItemEnchantSum(toolItem));

            ItemEnchantments modEnch = EnchantmentHelper.getEnchantmentsForCrafting(modifierItem);
            ItemEnchantments toolEnch = toolItem.getEnchantments();
            for (Holder<Enchantment> e : modEnch.keySet()) {
                System.out.println("Adding ench " + e.getRegisteredName());
                if (!e.value().canEnchant(toolItem)) {
                    continue;
                }

                boolean incompFlag = false;

                for (Holder<Enchantment> e2 : toolEnch.keySet()) {
                    if (!Enchantment.areCompatible(e, e2)) {
                        incompFlag = true;
                    }
                }

                if (incompFlag) {
                    continue;
                }

                toolItem.enchant(e, modEnch.getLevel(e));
                didSomething = true;

            }

            int postEnchantCost = curveEnchCost(getItemEnchantSum(toolItem));

            event.setOutput(toolItem);
            int costDelta = postEnchantCost - preEnchantCost;
            event.setCost(costDelta > 0 ? costDelta : 1);
            event.setMaterialCost(1);
            event.setCanceled(!didSomething);
            return;
        }

        // TODO: Process repair
        if (toolItem.getItem().isValidRepairItem(toolItem, modifierItem) && toolItem.isDamaged()) {
            toolItem.setDamageValue(0);
            event.setMaterialCost(1);
            event.setCost(1);
            event.setOutput(toolItem);
            didSomething = true;
            event.setCanceled(!didSomething);
        }
        event.setCanceled(!didSomething);
    }

    static int getItemEnchantSum(ItemStack stack) {
        ItemEnchantments ench = stack.getEnchantments();
        int sum = 0;

        for (Holder<Enchantment> e : ench.keySet()) {
            sum += ench.getLevel(e);
        }

        return sum;
    }

    static int curveEnchCost(int baseLevel) {

        return (int)Math.ceil( Math.pow(baseLevel, 1.5) / 2.0);
    }
}

