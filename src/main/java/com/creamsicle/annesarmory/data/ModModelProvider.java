
package com.creamsicle.annesarmory.data;

import com.creamsicle.annesarmory.AnnesArmory;
import com.creamsicle.annesarmory.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModModelProvider extends ItemModelProvider {


    public ModModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.REFINED_IRON_INGOT.get());
        basicItem(ModItems.REFINED_IRON_NUGGET.get());
    }
}
