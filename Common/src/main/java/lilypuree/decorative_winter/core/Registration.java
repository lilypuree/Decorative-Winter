package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_blocks.platform.Services;
import lilypuree.decorative_blocks.registration.RegistrationProvider;
import lilypuree.decorative_blocks.registration.RegistryObject;
import lilypuree.decorative_winter.DWConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

public class Registration {

    private static final RegistrationProvider<Fluid> FLUID_REGISTRY = RegistrationProvider.get(Registries.FLUID, Constants.MOD_ID);

    private static final ResourceLocation snowStillTexture = new ResourceLocation("block/snow");
    private static final ResourceLocation snowFlowingTexture = new ResourceLocation(DWConstants.MOD_ID, "block/snow_flowing");
    public static final ThatchFluid.FluidReferenceHolder snowReferenceHolder;
    public static RegistryObject<FlowingFluid> FLOWING_SNOW;
    public static RegistryObject<FlowingFluid> STILL_SNOW;

    static {
        snowReferenceHolder = new ThatchFluid.FluidReferenceHolder(() -> Blocks.POWDER_SNOW, () -> DWBlocks.FLUID_SNOW_BLOCK.get(), () -> FLOWING_SNOW.get(), () -> STILL_SNOW.get(), snowStillTexture, snowFlowingTexture, 0xFFFFFF);
        FLOWING_SNOW = FLUID_REGISTRY.register("flowing_snow", () -> Services.PLATFORM.createThatchFlowingFluid(snowReferenceHolder));
        STILL_SNOW = FLUID_REGISTRY.register("fluid_snow", () -> Services.PLATFORM.createThatchStillFluid(snowReferenceHolder));

    }

    public static void init() {
    }

    public static void addToTab(CreativeModeTab.Output output) {
        output.accept(DWItems.FESTIVE_CHAIN::get);
        output.accept(DWItems.WREATH::get);
        output.accept(DWItems.THIN_BRANCH::get);
        output.accept(DWItems.SNOWY_THIN_BRANCH::get);
        DWItems.SNOWY_PALISADES.values().forEach(regObject -> output.accept(regObject::get));
    }
    
    public static FlowingFluid getFlowingSnow(){
        return FLOWING_SNOW.get();
    }

    public static FlowingFluid getStillSnow(){
        return STILL_SNOW.get();
    }
}
