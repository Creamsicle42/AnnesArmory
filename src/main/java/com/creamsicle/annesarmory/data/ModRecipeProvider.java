package com.creamsicle.annesarmory.data;


import com.creamsicle.annesarmory.item.ModItems;
import com.creamsicle.annesarmory.recipes.RepairKitRepair;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }


    @Override
    protected void buildRecipes(@NotNull RecipeOutput pRecipeOutput) {

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



        nineBlockStorageRecipes(pRecipeOutput, RecipeCategory.MISC, ModItems.REFINED_IRON_NUGGET.get(), RecipeCategory.MISC, ModItems.REFINED_IRON_INGOT.get());
        nineBlockStorageRecipes(pRecipeOutput, RecipeCategory.MISC, ModItems.DIAMONDSTEEL_NUGGET.get(), RecipeCategory.MISC, ModItems.DIAMONDSTEEL_INGOT.get());
        oreBlasting(pRecipeOutput, List.of(ModItems.DIAMONDSTEEL_COMPOUND.get()), RecipeCategory.MISC, ModItems.DIAMONDSTEEL_NUGGET.get(), 0.2f, 200, "");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMONDSTEEL_COMPOUND.get())
                .pattern(" n ")
                .pattern("nan")
                .pattern(" n ")
                .define('n', ModItems.REFINED_IRON_NUGGET.get())
                .define('a', Items.DIAMOND)
                .unlockedBy("criteria", has(Items.DIAMOND))
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


        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_SWORD,
                Items.DIAMOND_SWORD,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_sword_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_AXE,
                Items.DIAMOND_AXE,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_axe_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_SHOVEL,
                Items.DIAMOND_SHOVEL,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_shovel_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_HOE,
                Items.DIAMOND_HOE,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "diamond_hoe_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_PICKAXE,
                Items.DIAMOND_PICKAXE,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_pickaxe_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_BOOTS,
                Items.DIAMOND_BOOTS,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "diamond_boots_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_LEGGINGS,
                Items.DIAMOND_LEGGINGS,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_leggings_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_CHESTPLATE,
                Items.DIAMOND_CHESTPLATE,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_chestplate_upgrade",
                pRecipeOutput
        );
        smithingUpgrade(
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(),
                Items.IRON_HELMET,
                Items.DIAMOND_HELMET,
                ModItems.DIAMONDSTEEL_INGOT.get(),
                "iron_helmet_upgrade",
                pRecipeOutput
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get(), 2)
                .pattern("sis")
                .pattern("sps")
                .pattern("sss")
                .define('s', Items.STONE)
                .define('i', Items.IRON_INGOT)
                .define('p', ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get())
                .unlockedBy("criteria", has(ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get(), 2)
                .pattern("sis")
                .pattern("sps")
                .pattern("sss")
                .define('s', Items.IRON_INGOT)
                .define('i', Items.DIAMOND)
                .define('p', ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get())
                .unlockedBy("criteria", has(ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get()))
                .save(pRecipeOutput);

        removeRecipe("iron_axe", pRecipeOutput);
        removeRecipe("iron_pickaxe", pRecipeOutput);
        removeRecipe("iron_hoe", pRecipeOutput);
        removeRecipe("iron_shovel", pRecipeOutput);
        removeRecipe("iron_sword", pRecipeOutput);
        removeRecipe("iron_boots", pRecipeOutput);
        removeRecipe("iron_leggings", pRecipeOutput);
        removeRecipe("iron_chestplate", pRecipeOutput);
        removeRecipe("iron_helmet", pRecipeOutput);

        removeRecipe("diamond_axe", pRecipeOutput);
        removeRecipe("diamond_pickaxe", pRecipeOutput);
        removeRecipe("diamond_hoe", pRecipeOutput);
        removeRecipe("diamond_shovel", pRecipeOutput);
        removeRecipe("diamond_sword", pRecipeOutput);
        removeRecipe("diamond_boots", pRecipeOutput);
        removeRecipe("diamond_leggings", pRecipeOutput);
        removeRecipe("diamond_chestplate", pRecipeOutput);
        removeRecipe("diamond_helmet", pRecipeOutput);

    }

    private static void removeRecipe(String recipe, RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, Items.BARRIER)
                .requires(Items.BARRIER)
                .unlockedBy("condition", has(Items.BARRIER))
                .save(recipeOutput, recipe);
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
