package com.creamsicle.annesarmory.data;


import com.creamsicle.annesarmory.item.ModItems;
import com.creamsicle.annesarmory.recipes.RepairKitRepair;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }


    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        SpecialRecipeBuilder.special(RepairKitRepair::new)
                        .save(pRecipeOutput, "annesarmory:repair");


        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(Items.IRON_INGOT),
                RecipeCategory.MISC,
                ModItems.REFINED_IRON_NUGGET.get(),
                0.2f,
                200
        )
                .unlockedBy("criteria", has(Items.IRON_INGOT))
                .save(pRecipeOutput, "annesarmory:refined_iron_blasting");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REFINED_IRON_INGOT.get())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ModItems.REFINED_IRON_NUGGET.get())
                .unlockedBy("criteria", has(ModItems.REFINED_IRON_NUGGET.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REFINED_IRON_NUGGET.get(), 9)
                .requires(ModItems.REFINED_IRON_INGOT.get())
                .unlockedBy("criteria", has(ModItems.REFINED_IRON_INGOT.get()))
                .save(pRecipeOutput);



        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.STONE_SWORD,
                Items.IRON_SWORD,
                ModItems.REFINED_IRON_INGOT.get(),
                "stone_sword_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.STONE_AXE,
                Items.IRON_AXE,
                ModItems.REFINED_IRON_INGOT.get(),
                "stone_axe_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.STONE_SHOVEL,
                Items.IRON_SHOVEL,
                ModItems.REFINED_IRON_INGOT.get(),
                "stone_shovel_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.STONE_HOE,
                Items.IRON_HOE,
                ModItems.REFINED_IRON_INGOT.get(),
                "stone_hoe_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.STONE_PICKAXE,
                Items.IRON_PICKAXE,
                ModItems.REFINED_IRON_INGOT.get(),
                "stone_pickaxe_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.LEATHER_BOOTS,
                Items.IRON_BOOTS,
                ModItems.REFINED_IRON_INGOT.get(),
                "leather_boots_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.LEATHER_LEGGINGS,
                Items.IRON_LEGGINGS,
                ModItems.REFINED_IRON_INGOT.get(),
                "leather_leggings_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.LEATHER_CHESTPLATE,
                Items.IRON_CHESTPLATE,
                ModItems.REFINED_IRON_INGOT.get(),
                "leather_chestplate_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(),
                Items.LEATHER_HELMET,
                Items.IRON_HELMET,
                ModItems.REFINED_IRON_INGOT.get(),
                "leather_helmet_upgrade",
                pRecipeOutput
        );

    }

    private static void smithingUpgrade(Item template, Item base, Item result, Item otherIng, String name, RecipeOutput pRecipeOutput) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(template),
                        Ingredient.of(base),
                        Ingredient.of(otherIng),
                        RecipeCategory.TOOLS,
                        result
                ).unlocks("criteria", has(template))
                .save(pRecipeOutput, "annesarmory:%s".formatted(name));
    }

}
