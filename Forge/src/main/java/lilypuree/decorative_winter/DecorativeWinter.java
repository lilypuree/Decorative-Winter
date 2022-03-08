package lilypuree.decorative_winter;

import lilypuree.decorative_blocks.DecorativeBlocks;
import lilypuree.decorative_blocks.fluid.ForgeThatchFluid;
import lilypuree.decorative_blocks.fluid.ForgeThatchFluidBlock;
import lilypuree.decorative_winter.core.Registration;
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

@Mod(DWConstants.MODID)
public class DecorativeWinter {

    public DecorativeWinter() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener((FMLCommonSetupEvent e) -> {
            ModSetup.init();
        });
        modBus.addListener(ClientSetup::initRenderLayers);

        modBus.addGenericListener(Block.class, this::registerBlocks);
        modBus.addGenericListener(Item.class, (RegistryEvent.Register<Item> event) -> Registration.registerItems(new DecorativeBlocks.RegistryHelperForge<>(event.getRegistry())));
        modBus.addGenericListener(Fluid.class, this::registerFluids);
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        Registration.FLUID_SNOW = new ForgeThatchFluidBlock(() -> Registration.STILL_SNOW, Registration.snowProperties);
        Registration.registerBlocks(new DecorativeBlocks.RegistryHelperForge<>(event.getRegistry()));
    }

    public void registerFluids(RegistryEvent.Register<Fluid> event) {
        Registration.STILL_SNOW = new ForgeThatchFluid.Source(Registration.snowReferenceHolder);
        Registration.FLOWING_SNOW = new ForgeThatchFluid.Flowing(Registration.snowReferenceHolder);
        Registration.registerFluids(new DecorativeBlocks.RegistryHelperForge<>(event.getRegistry()));
    }
}
