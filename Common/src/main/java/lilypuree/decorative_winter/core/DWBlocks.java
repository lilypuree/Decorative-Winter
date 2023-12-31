package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.blocks.ChainBlock;
import lilypuree.decorative_blocks.blocks.LatticeBlock;
import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_blocks.blocks.types.VanillaWoodTypes;
import lilypuree.decorative_blocks.platform.Services;
import lilypuree.decorative_blocks.registration.BlockRegistryObject;
import lilypuree.decorative_blocks.registration.RegistrationProvider;
import lilypuree.decorative_winter.DWConstants;
import lilypuree.decorative_winter.blocks.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DWBlocks {
    private static final RegistrationProvider<Block> BLOCK_REGISTRY = RegistrationProvider.get(Registries.BLOCK, DWConstants.MOD_ID);

    public static BlockRegistryObject<Block> FESTIVE_CHAIN;
    public static BlockRegistryObject<Block> WREATH;
    public static BlockRegistryObject<Block> DRY_GRASS_BLOCK;
    public static BlockRegistryObject<Block> DRY_GRASS;
    public static BlockRegistryObject<Block> DRY_TALL_GRASS;
    public static BlockRegistryObject<Block> DRY_FERN;
    public static BlockRegistryObject<Block> DRY_LARGE_FERN;
    public static BlockRegistryObject<Block> THIN_BRANCH;
    public static BlockRegistryObject<Block> SNOWY_THIN_BRANCH;
    public static BlockRegistryObject<LiquidBlock> FLUID_SNOW_BLOCK;
    public static Map<IWoodType, BlockRegistryObject<Block>> SNOWY_PALISADES = new HashMap<>();
    public static Map<IWoodType, BlockRegistryObject<Block>> SNOWY_SEATS = new HashMap<>();

    public static void init() {
        Block.Properties chainProperties = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(4.3F).sound(SoundType.METAL).noOcclusion();
        Block.Properties branchProperty = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().sound(SoundType.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).instabreak().dynamicShape().noOcclusion();
        BlockBehaviour.Properties liquidsnowProperty = Block.Properties.of().liquid().replaceable().noCollission().randomTicks().noLootTable()
                .mapColor(MapColor.SNOW).pushReaction(PushReaction.DESTROY).strength(100.0F);

        FESTIVE_CHAIN = registerBlock("festive_chain", () -> new ChainBlock(chainProperties));
        WREATH = registerBlock("wreath", () -> new LatticeBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
        DRY_GRASS_BLOCK = registerBlock("dry_grass_block", () -> new SnowyGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        DRY_GRASS = registerBlock("dry_grass", () -> new SnowyFoliageBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).mapColor(MapColor.COLOR_LIGHT_GRAY).noOcclusion().dynamicShape()));
        DRY_TALL_GRASS = registerBlock("dry_tall_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        DRY_FERN = registerBlock("dry_fern", () -> new SnowyFoliageBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_LIGHT_GRAY).dynamicShape()));
        DRY_LARGE_FERN = registerBlock("dry_large_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).mapColor(MapColor.COLOR_LIGHT_GRAY)));
        THIN_BRANCH = registerBlock("thin_branch", () -> new BranchBlock(branchProperty));
        SNOWY_THIN_BRANCH = registerBlock("snowy_thin_branch", () -> new BranchBlock(branchProperty));
        FLUID_SNOW_BLOCK = registerBlock("fluid_snow", () -> Services.PLATFORM.createThatchFluidBlock(Registration.STILL_SNOW, liquidsnowProperty));

        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_PALISADES.put(woodTypes, registerBlock(DWNames.snowyPalisade(woodTypes).getPath(), () -> getSnowyPalisade(woodTypes)));
        }
//        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
//            SNOWY_SEATS.put(woodTypes, getSnowySeat(woodTypes));
//        }
    }

    public static Block getSnowyPalisade(IWoodType woodType) {
        return new SnowyPalisadeBlock(woodType.getProperties().strength(2.0F, 4.0F), woodType);
    }

    public static Block getSnowySeat(IWoodType woodType) {
        return new SnowySeatBlock(woodType.getProperties().strength(1.2F), woodType);
    }

    private static <T extends Block> BlockRegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        return BlockRegistryObject.wrap(BLOCK_REGISTRY.register(name, blockSupplier));
    }

}
