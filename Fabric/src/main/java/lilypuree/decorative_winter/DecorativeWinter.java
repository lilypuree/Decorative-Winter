package lilypuree.decorative_winter;


import lilypuree.decorative_blocks.DecorativeBlocks;
import lilypuree.decorative_blocks.FabricThatchFluidBlock;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_winter.core.Registration;
import lilypuree.decorative_winter.core.setup.ModSetup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;

public class DecorativeWinter implements ModInitializer {

    @Override
    public void onInitialize() {
        Registration.STILL_SNOW =  new ThatchFluid.Source(Registration.snowReferenceHolder);
        Registration.FLOWING_SNOW = new ThatchFluid.Flowing(Registration.snowReferenceHolder);
        Registration.FLUID_SNOW = new FabricThatchFluidBlock(Registration.STILL_SNOW, Registration.snowProperties);
        Registration.registerBlocks(new DecorativeBlocks.RegistryHelperFabric<>(Registry.BLOCK));
        Registration.registerItems(new DecorativeBlocks.RegistryHelperFabric<>(Registry.ITEM));
        Registration.registerFluids(new DecorativeBlocks.RegistryHelperFabric<>(Registry.FLUID));
        ModSetup.init();
        Callbacks.initCallbacks();
    }

}
