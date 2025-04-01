package com.creamsicle.annesarmory.data;


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


    protected ModRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.STONE),
                Ingredient.of(Items.STONE_SWORD),
                Ingredient.of(Items.IRON_INGOT),
                RecipeCategory.TOOLS,
                Items.IRON_SWORD
        ).unlocks("criteria", has(Items.STONE_SWORD))
                .save(output, "stone_sword_transmute");
    }

    public static class ModRecipeRunner extends RecipeProvider.Runner {

        protected ModRecipeRunner(PackOutput pPackOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
            super(pPackOutput, pRegistries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
            return new ModRecipeProvider(pRegistries, pOutput);
        }

        @Override
        public String getName() {
            return "annesarmoryrecipebuilder";
        }
    }
}
