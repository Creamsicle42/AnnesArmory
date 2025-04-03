package com.creamsicle.annesarmory.item;

import com.creamsicle.annesarmory.AnnesArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AnnesArmory.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ANNES_SMITHING_TAB = CREATIVE_MODE_TABS.register("annes_armory",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.REFINED_IRON_INGOT.get()))
                    .title(Component.translatable("creativetab.annesarmory.annesarmory_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.REPAIR_KIT.get());
                        output.accept(ModItems.REFINED_IRON_INGOT.get());
                        output.accept(ModItems.REFINED_IRON_NUGGET.get());
                        output.accept(ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get());

                    })
                    .build()
            );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
