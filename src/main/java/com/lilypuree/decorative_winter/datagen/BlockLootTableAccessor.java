package com.lilypuree.decorative_winter.datagen;

import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;

public class BlockLootTableAccessor extends BlockLootTables {
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));

    public static <T> T withExplosionDecayWithoutImmuneCheck(IItemProvider item, ILootFunctionConsumer<T> function) {
        return function.acceptFunction(ExplosionDecay.builder());
    }

    public static LootTable.Builder droppingItemWithFortune(Block block, Item item) {
        return BlockLootTables.droppingItemWithFortune(block, item);
    }

    public static LootTable.Builder droppingSlab(Block slab) {
        return BlockLootTables.droppingSlab(slab);
    }

    public static LootTable.Builder dropping(IItemProvider item) {
        return BlockLootTables.dropping(item);
    }

    public static LootTable.Builder droppingWithSilkTouch(Block block, LootEntry.Builder<?> builder) {
        return BlockLootTables.dropping(block, SILK_TOUCH, builder);
    }

    public static LootTable.Builder droppingWithSilkTouch(Block block, IItemProvider noSilkTouch) {
        return BlockLootTables.droppingWithSilkTouch(block, noSilkTouch);
    }

    public static LootTable.Builder onlyWithShears(IItemProvider block){
        return BlockLootTables.onlyWithShears(block);
    }

    public static LootTable.Builder noSeedsTall(Block block, Block sheared) {
        LootEntry.Builder<?> builder = ItemLootEntry.builder(sheared).acceptFunction(SetCount.builder(ConstantRange.of(2)))
                .acceptCondition(SHEARS);
        return LootTable.builder().addLootPool(LootPool.builder().addEntry(builder)
                        .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                        .acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.builder().block(BlockPredicate.Builder.createBuilder().setBlock(block).setStatePredicate(StatePropertiesPredicate.Builder.newBuilder().withProp(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0))))
                .addLootPool(LootPool.builder().addEntry(builder)
                        .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
                        .acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.builder().block(BlockPredicate.Builder.createBuilder().setBlock(block).setStatePredicate(StatePropertiesPredicate.Builder.newBuilder().withProp(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }
}