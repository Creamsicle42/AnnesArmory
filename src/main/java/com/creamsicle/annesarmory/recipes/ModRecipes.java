package com.creamsicle.annesarmory.recipes;

import com.creamsicle.annesarmory.AnnesArmory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AnnesArmory.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, AnnesArmory.MOD_ID);

    public static final RegistryObject<RecipeSerializer<RepairKitRepair>> REPAIR_KIT_REPAIR_SERIALIZER =
            SERIALIZERS.register("crafting_special_repair", RepairKitRepair.Serializer::new);
    public static final RegistryObject<RecipeType<RepairKitRepair>> REPAIR_KIT_REPAIR_TYPE =
            TYPES.register("crafting_special_repair", () -> new RecipeType<RepairKitRepair>() {
                @Override
                public String toString() {
                    return "crafting_special_repair";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
