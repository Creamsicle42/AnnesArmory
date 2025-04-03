package com.creamsicle.annesarmory.data;

import com.creamsicle.annesarmory.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.REPAIR_KIT_REPAIRABLE)
                .add(Items.STONE_AXE)
                .add(Items.STONE_SWORD)
                .add(Items.STONE_PICKAXE)
                .add(Items.STONE_SHOVEL)
                .add(Items.STONE_HOE);
    }
}
