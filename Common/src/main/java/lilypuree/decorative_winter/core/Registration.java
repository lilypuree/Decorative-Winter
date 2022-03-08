package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.core.RegistryHelper;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_winter.DWConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class Registration {
    private static final ResourceLocation snowStillTexture = new ResourceLocation("block/snow");
    private static final ResourceLocation snowFlowingTexture = new ResourceLocation(DWConstants.MODID, "block/snow_flowing");
    public static final ThatchFluid.FluidReferenceHolder snowReferenceHolder = new ThatchFluid.FluidReferenceHolder(() -> Blocks.POWDER_SNOW, snowStillTexture, snowFlowingTexture, 0xFFFFFF);
    public static final Material snowMaterial = (new Material.Builder(MaterialColor.SNOW)).noCollider().nonSolid().replaceable().liquid().build();
    public static final BlockBehaviour.Properties snowProperties = BlockBehaviour.Properties.of(snowMaterial).noCollission().randomTicks().strength(100).noDrops().noOcclusion();
    public static FlowingFluid FLOWING_SNOW;
    public static FlowingFluid STILL_SNOW;
    public static Block FLUID_SNOW;

    static {
        snowReferenceHolder.setFlowingFluid(() -> FLOWING_SNOW);
        snowReferenceHolder.setStillFluid(() -> STILL_SNOW);
        snowReferenceHolder.setFluidBlock(() -> FLUID_SNOW);
    }

    public static void registerBlocks(RegistryHelper<Block> helper) {
        DWBlocks.init();
        helper.register(FLUID_SNOW, DWNames.STILL_SNOW);
        helper.register(DWBlocks.FESTIVE_CHAIN, DWNames.FESTIVE_CHAIN);
        helper.register(DWBlocks.WREATH, DWNames.WREATH);
        helper.register(DWBlocks.DRY_GRASS_BLOCK, DWNames.DRY_GRASS_BLOCK);
        helper.register(DWBlocks.DRY_GRASS, DWNames.DRY_GRASS);
        helper.register(DWBlocks.DRY_TALL_GRASS, DWNames.DRY_TALL_GRASS);
        helper.register(DWBlocks.DRY_FERN, DWNames.DRY_FERN);
        helper.register(DWBlocks.DRY_LARGE_FERN, DWNames.DRY_LARGE_FERN);
        helper.register(DWBlocks.THIN_BRANCH, DWNames.THIN_BRANCH);
        helper.register(DWBlocks.SNOWY_THIN_BRANCH, DWNames.SNOWY_THIN_BRANCH);
        DWBlocks.SNOWY_PALISADES.forEach((wood, block) -> helper.register(block, DWNames.snowyPalisade(wood)));
//        DWBlocks.SNOWY_SEATS.forEach((wood, block)-> helper.register(block, DWNames.snowySeat(wood)));
    }

    public static void registerItems(RegistryHelper<Item> helper) {
        DWItems.init();
        helper.register(DWItems.FROSTY_WAND, DWNames.FROSTY_WAND);
        helper.register(DWItems.FESTIVE_CHAIN, DWNames.FESTIVE_CHAIN);
        helper.register(DWItems.WREATH, DWNames.WREATH);
        helper.register(DWItems.DRY_GRASS_BLOCK, DWNames.DRY_GRASS_BLOCK);
        helper.register(DWItems.DRY_GRASS, DWNames.DRY_GRASS);
        helper.register(DWItems.DRY_TALL_GRASS, DWNames.DRY_TALL_GRASS);
        helper.register(DWItems.DRY_FERN, DWNames.DRY_FERN);
        helper.register(DWItems.DRY_LARGE_FERN, DWNames.DRY_LARGE_FERN);
        helper.register(DWItems.THIN_BRANCH, DWNames.THIN_BRANCH);
        helper.register(DWItems.SNOWY_THIN_BRANCH, DWNames.SNOWY_THIN_BRANCH);
        DWItems.SNOWY_PALISADES.forEach((wood, item)-> helper.register(item, DWNames.snowyPalisade(wood)));
//        DWItems.SNOWY_SEATS.forEach((wood, item)-> helper.register(item, DWNames.snowySeat(wood)));
    }

    public static void registerFluids(RegistryHelper<Fluid> helper){
        helper.register(STILL_SNOW, DWNames.STILL_SNOW);
        helper.register(FLOWING_SNOW, DWNames.FLOWING_SNOW);
    }
}
