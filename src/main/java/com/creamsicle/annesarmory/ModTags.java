package com.creamsicle.annesarmory;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> REPAIR_KIT_REPAIRABLE = createTag("repair_kit_repairable");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID, name));
        }
    }
}
