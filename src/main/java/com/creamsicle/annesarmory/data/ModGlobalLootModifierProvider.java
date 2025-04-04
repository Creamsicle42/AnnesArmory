package com.creamsicle.annesarmory.data;

import com.creamsicle.annesarmory.AnnesArmory;
import com.creamsicle.annesarmory.item.ModItems;
import com.creamsicle.annesarmory.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, AnnesArmory.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {

        // Add iron template to armorer villagers
        add("iron_template_from_village", new AddItemModifier( new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.7f).build()
        },
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()
        ));

        // Add iron template to dungeons
        add("iron_template_from_dungeon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.6f).build()
        },
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()
        ));

        // Add iron template to mineshaft
        add("iron_template_from_mineshaft", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        },
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()
        ));

        // Add iron template to jungle temple
        add("iron_template_from_jungle_temple", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()
        },
                ModItems.REFINED_IRON_UPGRADE_TEMPLATE.get()
        ));

        // Add diamond template to jungle temple
        add("diamond_template_from_jungle_temple", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()
        },
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get()
        ));

        // Add diamond template to trial chamber intersection
        add("diamond_template_from_trial_chamber_inter", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/trial_chambers/intersection")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()
        },
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get()
        ));

        // Add diamond template to deep dark
        add("diamond_template_from_deep_dark", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build()
        },
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get()
        ));

        // Add diamond template to nether bridge
        add("diamond_template_from_nether_bridge", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        },
                ModItems.DIAMONDSTEEL_UPGRADE_TEMPLATE.get()
        ));

        // Add dagger template to jungle temple
        add("dagger_template_from_jungle_temple", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build()
        },
                ModItems.DAGGER_UPGRADE_TEMPLATE.get()
        ));

    }
}
