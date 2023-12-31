package lilypuree.decorative_winter;


import net.fabricmc.api.ModInitializer;

public class DecorativeWinter implements ModInitializer {

    @Override
    public void onInitialize() {
//        Registration.STILL_SNOW =  new ThatchFluid.Source(Registration.snowReferenceHolder);
//        Registration.FLOWING_SNOW = new ThatchFluid.Flowing(Registration.snowReferenceHolder);
//        Registration.FLUID_SNOW = new FabricThatchFluidBlock(Registration.STILL_SNOW, Registration.snowProperties);
//        Registration.registerBlocks(new DecorativeBlocks.RegistryHelperFabric<>(Registry.BLOCK));
//        Registration.registerItems(new DecorativeBlocks.RegistryHelperFabric<>(Registry.ITEM));
//        Registration.registerFluids(new DecorativeBlocks.RegistryHelperFabric<>(Registry.FLUID));
        ModSetup.init();
        Callbacks.initCallbacks();
    }

}
