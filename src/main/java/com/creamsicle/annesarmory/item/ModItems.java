package com.creamsicle.annesarmory.item;

import com.creamsicle.annesarmory.AnnesArmory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AnnesArmory.MOD_ID);

    public static final RegistryObject<Item> REFINED_IRON_INGOT = ITEMS.register("refined_iron",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REFINED_IRON_NUGGET = ITEMS.register("refined_iron_nugget",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
