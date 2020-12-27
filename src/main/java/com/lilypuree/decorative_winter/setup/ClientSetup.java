package com.lilypuree.decorative_winter.setup;

import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_winter.DecorativeWinter;
import com.lilypuree.decorative_winter.client.model.RandomTranslatedModelLoader;
import com.lilypuree.decorative_winter.client.model.SnowyBlockModelLoader;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(Registration.FESTIVE_CHAIN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.WREATH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.DRY_GRASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.DRY_FERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.DRY_TALL_GRASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.DRY_LARGE_FERN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.DRY_GRASS_BLOCK.get(), RenderType.getCutout());
        for (IWoodType woodType : VanillaWoodTypes.values()) {
//            RenderTypeLookup.setRenderLayer(Registration.getSnowySeatBlock(woodType), RenderType.getCutout());
//            RenderTypeLookup.setRenderLayer(Registration.getPalisadeBlock(woodType), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(Registration.getBranchBlock(woodType), RenderType.getCutout());
        }
    }

    @SubscribeEvent
    public static void modelLoaderRegister(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DecorativeWinter.MODID, "snowyloader"), SnowyBlockModelLoader.INSTANCE);
        ModelLoaderRegistry.registerLoader(new ResourceLocation(DecorativeWinter.MODID, "randomtranslationloader"), RandomTranslatedModelLoader.INSTANCE);
    }
}
