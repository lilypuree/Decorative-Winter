package lilypuree.decorative_winter.client;

import lilypuree.decorative_blocks.platform.Services;
import lilypuree.decorative_winter.core.DWBlocks;
import net.minecraft.client.renderer.RenderType;

public class DWClientSetup {

    public static void initRenderLayers() {
        Services.PLATFORM.setRenderLayer(DWBlocks.WREATH.get(), RenderType.cutoutMipped());
        Services.PLATFORM.setRenderLayer(DWBlocks.FLUID_SNOW_BLOCK.get(), RenderType.solid());
//        Services.PLATFORM.setRenderLayer(DWBlocks.DRY_GRASS.get(), RenderType.cutout());
//        Services.PLATFORM.setRenderLayer(DWBlocks.DRY_FERN.get(), RenderType.cutout());
//        Services.PLATFORM.setRenderLayer(DWBlocks.DRY_TALL_GRASS.get(), RenderType.cutout());
//        Services.PLATFORM.setRenderLayer(DWBlocks.DRY_LARGE_FERN.get(), RenderType.cutout());
//        Services.PLATFORM.setRenderLayer(DWBlocks.DRY_GRASS_BLOCK.get(), RenderType.cutoutMipped());
        Services.PLATFORM.setRenderLayer(DWBlocks.THIN_BRANCH.get(), RenderType.cutoutMipped());
        Services.PLATFORM.setRenderLayer(DWBlocks.SNOWY_THIN_BRANCH.get(), RenderType.cutoutMipped());
    }
}
