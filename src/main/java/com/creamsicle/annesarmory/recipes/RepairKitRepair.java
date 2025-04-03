package com.creamsicle.annesarmory.recipes;

import com.creamsicle.annesarmory.ModTags;
import com.creamsicle.annesarmory.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class RepairKitRepair implements CraftingRecipe {

    private final CraftingBookCategory category;

    public RepairKitRepair(CraftingBookCategory craftingBookCategory) {
        super();
        category = craftingBookCategory;
    }

    @Override
    public boolean matches(CraftingInput pInput, Level pLevel) {
        //System.out.println("Doing repair kit test");
        if (pInput.ingredientCount() != 3) {
            //System.out.println("Improper item count");
            return false;
        }

        ItemStack repairKit = null;
        ItemStack repairItem = null;
        ItemStack tool = null;

        for (ItemStack item : pInput.items()) {

            if (item.getItem() == ModItems.REPAIR_KIT.get()) {
                repairKit = item;
                //System.out.println("Found repair kit");
            }
            else if (item.is(ModTags.Items.REPAIR_KIT_REPAIRABLE)) {
                tool = item;
                //System.out.println("Found tool " + tool.getDisplayName().getString());
            }
            else if (!item.isEmpty()) {
                repairItem = item;
                //System.out.println("Found repair item " + repairItem.getDisplayName().getString());
            }

        }

        if (repairKit == null || repairItem == null || tool == null) {
            return false;
        }

        if (!tool.getItem().isValidRepairItem(tool, repairItem)) {
            return false;
        }

        return true;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput pInput) {
        NonNullList<ItemStack> out = NonNullList.create();
        for(ItemStack item : pInput.items()) {
            if (item.getItem() == ModItems.REPAIR_KIT.get()) {
                ItemStack n = item.copy();
                if (n.getMaxDamage() - 1 == n.getDamageValue()) {
                    out.add(ItemStack.EMPTY);
                } else {
                    n.setDamageValue(n.getDamageValue() + 1);
                    out.add(n);
                }
            } else {
                out.add(ItemStack.EMPTY);
            }
        }
        return out;
    }

    @Override
    public ItemStack assemble(CraftingInput pInput, HolderLookup.Provider pRegistries) {
        ItemStack repairKit = null;
        ItemStack repairItem = null;
        ItemStack tool = null;

        for (ItemStack item : pInput.items()) {
            if (item.getItem() == ModItems.REPAIR_KIT.get()) {
                repairKit = item;
            }
            else if (item.is(ModTags.Items.REPAIR_KIT_REPAIRABLE)) {
                tool = item;
            }
            else if (!item.isEmpty()) {
                repairItem = item;
            }

        }

        ItemStack newTool = tool.copy();

        int curDamage = newTool.getDamageValue();
        int maxDamage = newTool.getMaxDamage();
        int maxRepair = maxDamage - curDamage;
        int idealRepair = maxDamage / 3;
        int doRepair = Math.min(idealRepair, maxRepair);
        newTool.setDamageValue(curDamage - doRepair);

        return newTool;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight > 3;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return new ItemStack(ModItems.REPAIR_KIT.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.REPAIR_KIT_REPAIR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingBookCategory category() {
        return category;
    }

    public static class Serializer extends SimpleCraftingRecipeSerializer<RepairKitRepair> {

        public Serializer() {
            super(RepairKitRepair::new);
        }
    }
}
