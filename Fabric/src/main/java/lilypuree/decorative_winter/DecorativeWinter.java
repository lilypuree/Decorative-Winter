package lilypuree.decorative_winter;


import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.DWItems;
import lilypuree.decorative_winter.core.DWNames;
import lilypuree.decorative_winter.core.Registration;
import lilypuree.decorative_winter.core.factory.BlockSuppliers;
import lilypuree.decorative_winter.core.setup.ModSetup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;

public class DecorativeWinter implements ModInitializer {

    @Override
    public void onInitialize() {
        registerBlocks();
        registerItems();
        register();
        ModSetup.init();
        Callbacks.initCallbacks();
    }

    public void registerBlocks() {
        DWBlocks.init();
        if (Registration.STILL_SNOW == null) {
            Registration.STILL_SNOW = new ThatchFluid.Source(Registration.snowReferenceHolder);
            Registration.FLOWING_SNOW = new ThatchFluid.Flowing(Registration.snowReferenceHolder);
        }
        Registration.FLUID_SNOW = BlockSuppliers.FLUID_SNOW.get();
        Registry.register(Registry.BLOCK, DWNames.STILL_SNOW, Registration.FLUID_SNOW);
        Registry.register(Registry.BLOCK, DWNames.SNOWY_THIN_BRANCH, DWBlocks.SNOWY_THIN_BRANCH);
        Registry.register(Registry.BLOCK, DWNames.THIN_BRANCH, DWBlocks.THIN_BRANCH);
        Registry.register(Registry.BLOCK, DWNames.FESTIVE_CHAIN, DWBlocks.FESTIVE_CHAIN);
        Registry.register(Registry.BLOCK, DWNames.WREATH, DWBlocks.WREATH);
        Registry.register(Registry.BLOCK, DWNames.DRY_GRASS_BLOCK, DWBlocks.DRY_GRASS_BLOCK);
        Registry.register(Registry.BLOCK, DWNames.DRY_GRASS , DWBlocks.DRY_GRASS);
        Registry.register(Registry.BLOCK, DWNames.DRY_FERN, DWBlocks.DRY_FERN);
        Registry.register(Registry.BLOCK, DWNames.DRY_TALL_GRASS, DWBlocks.DRY_TALL_GRASS);
        Registry.register(Registry.BLOCK, DWNames.DRY_LARGE_FERN, DWBlocks.DRY_LARGE_FERN);

        DWBlocks.SNOWY_PALISADES.forEach((wood, block) -> Registry.register(Registry.BLOCK, DWNames.snowyPalisade(wood), block));
    }

    public static void registerItems() {
        DWItems.init();
        Registry.register(Registry.ITEM, DWNames.SNOWY_THIN_BRANCH, DWItems.SNOWY_THIN_BRANCH);
        Registry.register(Registry.ITEM, DWNames.THIN_BRANCH, DWItems.THIN_BRANCH);
        Registry.register(Registry.ITEM, DWNames.FESTIVE_CHAIN,DWItems.FESTIVE_CHAIN);
        Registry.register(Registry.ITEM, DWNames.WREATH, DWItems.WREATH);
        Registry.register(Registry.ITEM, DWNames.DRY_GRASS_BLOCK, DWItems.DRY_GRASS_BLOCK);
        Registry.register(Registry.ITEM, DWNames.DRY_GRASS , DWItems.DRY_GRASS);
        Registry.register(Registry.ITEM, DWNames.DRY_FERN, DWItems.DRY_FERN);
        Registry.register(Registry.ITEM, DWNames.DRY_TALL_GRASS, DWItems.DRY_TALL_GRASS);
        Registry.register(Registry.ITEM, DWNames.DRY_LARGE_FERN, DWItems.DRY_LARGE_FERN);
        Registry.register(Registry.ITEM, DWNames.FROSTY_WAND, DWItems.FROSTY_WAND);

        DWItems.SNOWY_PALISADES.forEach((wood, item) -> Registry.register(Registry.ITEM, DWNames.snowyPalisade(wood), item));
    }

    public static void register() {
        if (Registration.STILL_SNOW == null) {
            Registration.STILL_SNOW = new ThatchFluid.Source(Registration.snowReferenceHolder);
            Registration.FLOWING_SNOW = new ThatchFluid.Flowing(Registration.snowReferenceHolder);
        }
        Registry.register(Registry.FLUID, DWNames.FLOWING_SNOW, Registration.FLOWING_SNOW);
        Registry.register(Registry.FLUID, DWNames.STILL_SNOW, Registration.STILL_SNOW);
    }
}
