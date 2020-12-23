package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_blocks.datagen.LootTables;
import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_blocks.datagen.types.WoodDecorativeBlockTypes;
import com.lilypuree.decorative_winter.setup.Registration;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class WinterLootTables extends LootTables {

    public WinterLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public void act(DirectoryCache cache) {
        for (IWoodType wood : VanillaWoodTypes.values()) {
            for (WoodDecorativeBlockTypes type : Registration.supportedTypes) {
                lootTables.put(Registration.getSnowyWoodDecorativeBlock(wood, type),
                        snowyBlock(Registration.getSnowyWoodDecorativeBlock(wood, type), com.lilypuree.decorative_blocks.setup.Registration.getWoodDecorativeBlock(wood, type)));
            }
            lootTables.put(Registration.getBranchBlock(wood), BlockLootTableAccessor.droppingWithSilkTouch(Registration.getBranchBlock(wood), Items.STICK));
        }
        addBlockLoot(Registration.FESTIVE_CHAIN.get());
        addBlockLoot(Registration.WREATH.get());
        lootTables.put(Registration.DRY_GRASS_BLOCK.get(), BlockLootTableAccessor.droppingWithSilkTouch(Registration.DRY_GRASS_BLOCK.get(), Blocks.DIRT));
        lootTables.put(Registration.DRY_GRASS.get(), BlockLootTableAccessor.onlyWithShears(Registration.DRY_GRASS.get()));
        lootTables.put(Registration.DRY_FERN.get(), BlockLootTableAccessor.onlyWithShears(Registration.DRY_FERN.get()));
        lootTables.put(Registration.DRY_TALL_GRASS.get(), BlockLootTableAccessor.noSeedsTall(Registration.DRY_TALL_GRASS.get(), Registration.DRY_GRASS.get()));
        lootTables.put(Registration.DRY_GRASS.get(), BlockLootTableAccessor.onlyWithShears(Registration.DRY_GRASS.get()));
        lootTables.put(Registration.DRY_LARGE_FERN.get(), BlockLootTableAccessor.noSeedsTall(Registration.DRY_LARGE_FERN.get(), Registration.DRY_FERN.get()));

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    public void addBlockLoot(Block block) {
        lootTables.put(block, BlockLootTableAccessor.dropping(block));
    }

    public void addBlockLoot(Block block, Block droppingBlock) {
        lootTables.put(block, BlockLootTableAccessor.dropping(droppingBlock));
    }

    public static LootTable.Builder snowyBlock(Block block, Block droppingBlock) {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(droppingBlock))
                .addEntry(ItemLootEntry.builder(Items.SNOWBALL).acceptCondition(BlockStateProperty.builder(block).fromProperties(
                        StatePropertiesPredicate.Builder.newBuilder().withBoolProp(BlockStateProperties.SNOWY, true)))));
    }

}
