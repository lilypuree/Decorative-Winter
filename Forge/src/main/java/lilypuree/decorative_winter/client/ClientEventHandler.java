package lilypuree.decorative_winter.client;

import lilypuree.decorative_winter.DWConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = DWConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    public static void initRenderLayers(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            DWClientSetup.initRenderLayers();
        });
 

    }

//    @SubscribeEvent
//    public static void modelLoaderRegistration(ModelRegistryEvent event) {
////        ModelLoaderRegistry.registerLoader(new ResourceLocation(DWConstants.MODID, "snowyloader"), SnowyBlockModelLoader.INSTANCE);
//        ModelLoaderRegistry.registerLoader(new ResourceLocation(DWConstants.MOD_ID, "randomtranslationloader"), RandomTranslatedModelLoader.INSTANCE);
//    }
}
