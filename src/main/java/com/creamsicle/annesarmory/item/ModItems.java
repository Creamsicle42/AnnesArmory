package com.creamsicle.annesarmory.item;

import com.creamsicle.annesarmory.AnnesArmory;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {

    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static Component upgradeApplyComponent(String tier) {
        return Component.translatable(
                        Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID, "smithing_template.%s_upgrade.applies_to".formatted(tier)))
                )
                .withStyle(DESCRIPTION_FORMAT);
    }
    private static Component upgradeIngredientComponent(String tier) {
        return Component.translatable(
                        Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID, "smithing_template.%s_upgrade.ingredients".formatted(tier)))
                )
                .withStyle(DESCRIPTION_FORMAT);
    }
    private static Component upgradeTitle(String tier) {
        return Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID, "%s_upgrade".formatted(tier))))
                .withStyle(TITLE_FORMAT);
    }

    private static Component upgradeBaseComponent(String tier) {
        return Component.translatable(
                        Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID, "smithing_template.%s_upgrade.base_slot_description".formatted(tier)))
                );
    }
    private static Component upgradeAdditionComponent(String tier) {
        return Component.translatable(
                Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(AnnesArmory.MOD_ID, "smithing_template.%s_upgrade.additions_slot_description".formatted(tier)))
        );
    }
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");
    private static List<ResourceLocation> createUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }
    private static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AnnesArmory.MOD_ID);

    public static final RegistryObject<Item> REFINED_IRON_INGOT = ITEMS.register("refined_iron_ingot",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REFINED_IRON_NUGGET = ITEMS.register("refined_iron_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REFINED_IRON_UPGRADE_TEMPLATE = ITEMS.register("refined_iron_upgrade_template",
            () -> new SmithingTemplateItem(
                    upgradeApplyComponent("refined_iron"),
                    upgradeIngredientComponent("refined_iron"),
                    upgradeTitle("refined_iron"),
                    upgradeBaseComponent("refined_iron"),
                    upgradeAdditionComponent("refined_iron"),
                    createUpgradeIconList(),
                    createUpgradeMaterialList()
                    )
            );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
