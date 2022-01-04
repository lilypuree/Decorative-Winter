package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_winter.DWConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;

public class Registration {
    private static final ResourceLocation snowStillTexture = new ResourceLocation( "block/snow");
    private static final ResourceLocation snowFlowingTexture = new ResourceLocation(DWConstants.MODID, "block/snow_flowing");
    public static final ThatchFluid.FluidReferenceHolder snowReferenceHolder = new ThatchFluid.FluidReferenceHolder(() -> Blocks.POWDER_SNOW, snowStillTexture, snowFlowingTexture, 0xFFFFFF);
    public static FlowingFluid FLOWING_SNOW;
    public static FlowingFluid STILL_SNOW;
    public static Block FLUID_SNOW;

    static {
        snowReferenceHolder.setFlowingFluid(()-> FLOWING_SNOW);
        snowReferenceHolder.setStillFluid(()-> STILL_SNOW);
        snowReferenceHolder.setFluidBlock(()-> FLUID_SNOW);
    }
}
