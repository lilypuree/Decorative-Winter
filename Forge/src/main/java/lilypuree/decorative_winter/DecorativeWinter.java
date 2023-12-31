package lilypuree.decorative_winter;

import lilypuree.decorative_winter.client.ClientEventHandler;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.DWItems;
import lilypuree.decorative_winter.core.DWNames;
import lilypuree.decorative_winter.core.Registration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

@Mod(DWConstants.MOD_ID)
public class DecorativeWinter {

    public DecorativeWinter() {
        Registration.init();
        DWBlocks.init();
        DWItems.init();
        
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener((FMLCommonSetupEvent e) -> {
            DWCommon.init();
        });
        modBus.addListener(ClientEventHandler::initRenderLayers);

        modBus.addListener(this::onRegisterEvent);
    }

    private void onRegisterEvent(RegisterEvent event) {
        if (event.getRegistryKey() == ForgeRegistries.FLUID_TYPES.get().getRegistryKey()) {
            registerFluidTypes(event.getForgeRegistry());
        }
    }
    private void registerFluidTypes(IForgeRegistry<FluidType> registry) {
        registry.register(DWNames.STILL_SNOW, Registration.getStillSnow().getFluidType());
        registry.register(DWNames.FLOWING_SNOW, Registration.getFlowingSnow().getFluidType());
    }
}
