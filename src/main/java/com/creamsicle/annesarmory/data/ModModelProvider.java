
package com.creamsicle.annesarmory.data;

import com.creamsicle.annesarmory.AnnesArmory;
import com.creamsicle.annesarmory.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModModelProvider extends ItemModelProvider {


    public ModModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.REFINED_IRON_INGOT.get());
        basicItem(ModItems.REFINED_IRON_NUGGET.get());
        basicItem(ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get());
        basicItem(ModItems.REPAIR_KIT.get());
        basicItem(ModItems.DIAMONDSTEEL_COMPOUND.get());
        basicItem(ModItems.DIAMONDSTEEL_INGOT.get());
        basicItem(ModItems.DIAMONDSTEEL_NUGGET.get());
        basicItem(ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get());

        basicItem(ModItems.DAGGER_UPGRADE_TEMPLATE.get());
        basicItem(ModItems.BROADSWORD_UPGRADE_TEMPLATE.get());
        basicItem(ModItems.RAPIER_UPGRADE_TEMPLATE.get());

        handheldItem(ModItems.IRON_DAGGER);
        handheldItem(ModItems.DIAMOND_DAGGER);
        handheldItem(ModItems.NETHERITE_DAGGER);
        handheldItem(ModItems.IRON_BROADSWORD);
        handheldItem(ModItems.DIAMOND_BROADSWORD);
        handheldItem(ModItems.NETHERITE_BROADSWORD);
        handheldItem(ModItems.IRON_RAPIER);
        handheldItem(ModItems.DIAMOND_RAPIER);
        handheldItem(ModItems.NETHERITE_RAPIER);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID,"item/" + item.getId().getPath()));
    }
}
