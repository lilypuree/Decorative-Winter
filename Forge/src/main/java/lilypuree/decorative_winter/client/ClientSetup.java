package lilypuree.decorative_winter.client;

import lilypuree.decorative_winter.DWConstants;
import lilypuree.decorative_winter.client.model.RandomTranslatedModelLoader;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.Registration;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = DWConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void initRenderLayers(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.FLUID_SNOW, RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.FESTIVE_CHAIN, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.WREATH, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.DRY_GRASS, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.DRY_FERN, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.DRY_TALL_GRASS, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.DRY_LARGE_FERN, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.DRY_GRASS_BLOCK, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.THIN_BRANCH, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DWBlocks.SNOWY_THIN_BRANCH, RenderType.cutoutMipped());
    }

    @SubscribeEvent
    public static void modelLoaderRegistration(ModelRegistryEvent event) {
//        ModelLoaderRegistry.registerLoader(new ResourceLocation(DWConstants.MODID, "snowyloader"), SnowyBlockModelLoader.INSTANCE);
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DWConstants.MODID, "randomtranslationloader"), RandomTranslatedModelLoader.INSTANCE);
    }
}
