package lilypuree.decorative_winter;

import lilypuree.decorative_blocks.fluid.ForgeThatchFluid;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.DWItems;
import lilypuree.decorative_winter.core.DWNames;
import lilypuree.decorative_winter.core.Registration;
import lilypuree.decorative_winter.core.factory.BlockSuppliers;
import lilypuree.decorative_winter.client.ClientSetup;
import lilypuree.decorative_winter.core.setup.ModSetup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(DWConstants.MODID)
public class DecorativeWinter {

    public DecorativeWinter() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener((FMLCommonSetupEvent e) -> {
            ModSetup.init();
        });
        modBus.addListener(ClientSetup::initRenderLayers);

        modBus.addGenericListener(Block.class, this::registerBlocks);
        modBus.addGenericListener(Item.class, this::registerItems);
        modBus.addGenericListener(Fluid.class, this::registerFluids);
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        DWBlocks.init();
        if (Registration.STILL_SNOW == null) {
            Registration.STILL_SNOW = new ForgeThatchFluid.Source(Registration.snowReferenceHolder);
            Registration.FLOWING_SNOW = new ForgeThatchFluid.Flowing(Registration.snowReferenceHolder);
        }
        Registration.FLUID_SNOW = BlockSuppliers.FLUID_SNOW.get();

        IForgeRegistry<Block> registry = event.getRegistry();
        registry.registerAll(
                Registration.FLUID_SNOW.setRegistryName(DWNames.STILL_SNOW),
                DWBlocks.FESTIVE_CHAIN.setRegistryName(DWNames.FESTIVE_CHAIN),
                DWBlocks.WREATH.setRegistryName(DWNames.WREATH),
                DWBlocks.DRY_GRASS_BLOCK.setRegistryName(DWNames.DRY_GRASS_BLOCK),
                DWBlocks.DRY_GRASS.setRegistryName(DWNames.DRY_GRASS),
                DWBlocks.DRY_TALL_GRASS.setRegistryName(DWNames.DRY_TALL_GRASS),
                DWBlocks.DRY_FERN.setRegistryName(DWNames.DRY_FERN),
                DWBlocks.DRY_LARGE_FERN.setRegistryName(DWNames.DRY_LARGE_FERN),
                DWBlocks.THIN_BRANCH.setRegistryName(DWNames.THIN_BRANCH),
                DWBlocks.SNOWY_THIN_BRANCH.setRegistryName(DWNames.SNOWY_THIN_BRANCH)
        );
        DWBlocks.SNOWY_PALISADES.forEach((wood, block) -> registry.register(block.setRegistryName(DWNames.snowyPalisade(wood))));
//        DWBlocksForge.SNOWY_SEATS.forEach((wood, block) -> registry.register(block.setRegistryName(DWNames.snowySeat(wood))));
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        DWItems.init();
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                DWItems.FROSTY_WAND.setRegistryName(DWNames.FROSTY_WAND),
                DWItems.FESTIVE_CHAIN.setRegistryName(DWNames.FESTIVE_CHAIN),
                DWItems.WREATH.setRegistryName(DWNames.WREATH),
                DWItems.DRY_GRASS_BLOCK.setRegistryName(DWNames.DRY_GRASS_BLOCK),
                DWItems.DRY_GRASS.setRegistryName(DWNames.DRY_GRASS),
                DWItems.DRY_TALL_GRASS.setRegistryName(DWNames.DRY_TALL_GRASS),
                DWItems.DRY_FERN.setRegistryName(DWNames.DRY_FERN),
                DWItems.DRY_LARGE_FERN.setRegistryName(DWNames.DRY_LARGE_FERN),
                DWItems.THIN_BRANCH.setRegistryName(DWNames.THIN_BRANCH),
                DWItems.SNOWY_THIN_BRANCH.setRegistryName(DWNames.SNOWY_THIN_BRANCH)
        );
        DWItems.SNOWY_PALISADES.forEach((wood, block) -> registry.register(block.setRegistryName(DWNames.snowyPalisade(wood))));
//        DWItemsForge.SNOWY_SEATS.forEach((wood, block) -> registry.register(block.setRegistryName(DWNames.snowySeat(wood))));
    }

    public void registerFluids(RegistryEvent.Register<Fluid> event) {
        if (Registration.STILL_SNOW == null) {
            Registration.STILL_SNOW = new ForgeThatchFluid.Source(Registration.snowReferenceHolder);
            Registration.FLOWING_SNOW = new ForgeThatchFluid.Flowing(Registration.snowReferenceHolder);
        }
        event.getRegistry().registerAll(
                Registration.STILL_SNOW.setRegistryName(DWNames.STILL_SNOW),
                Registration.FLOWING_SNOW.setRegistryName(DWNames.FLOWING_SNOW)
        );
    }
}
