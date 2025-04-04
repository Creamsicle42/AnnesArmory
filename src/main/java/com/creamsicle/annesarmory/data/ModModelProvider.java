
package com.creamsicle.annesarmory.data;

import com.creamsicle.annesarmory.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
    }
}
