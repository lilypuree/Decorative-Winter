package lilypuree.decorative_winter.core.setup;

import lilypuree.decorative_blocks.CommonAPI;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.Registration;
import lilypuree.decorative_winter.mixin.FireBlockInvoker;
import net.minecraft.world.level.block.Blocks;

public class ModSetup {

    public static void init() {
        CommonAPI.addThatchlikeFluid(Registration.snowReferenceHolder);

        FireBlockInvoker invoker = ((FireBlockInvoker) ((Object) Blocks.FIRE));
        invoker.invokeSetFlammable(DWBlocks.WREATH, 5, 20);
        invoker.invokeSetFlammable(DWBlocks.DRY_GRASS, 60, 80);
        invoker.invokeSetFlammable(DWBlocks.DRY_TALL_GRASS, 60, 80);
        invoker.invokeSetFlammable(DWBlocks.DRY_GRASS_BLOCK, 60, 80);
        invoker.invokeSetFlammable(DWBlocks.DRY_FERN, 60, 80);
        invoker.invokeSetFlammable(DWBlocks.DRY_LARGE_FERN, 60, 80);
    }
}
