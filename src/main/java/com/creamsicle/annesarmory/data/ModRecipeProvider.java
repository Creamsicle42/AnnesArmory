package com.creamsicle.annesarmory.data;


import com.creamsicle.annesarmory.item.ModItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }


    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(Items.STONE_SWORD),
                        Ingredient.of(ModItems.REFINED_IRON_INGOT.get()),
                        RecipeCategory.TOOLS,
                        Items.IRON_SWORD
                ).unlocks("criteria", has(ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()))
                .save(pRecipeOutput, "stone_sword_transmute");

    }


}
