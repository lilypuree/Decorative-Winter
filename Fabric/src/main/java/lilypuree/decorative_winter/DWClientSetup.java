package lilypuree.decorative_winter;


import lilypuree.decorative_blocks.ClientInitializer;
import lilypuree.decorative_winter.client.RandomTranslatedModelProvider;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.Registration;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class DWClientSetup implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.FESTIVE_CHAIN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.DRY_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.DRY_FERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.DRY_TALL_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.DRY_LARGE_FERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.DRY_GRASS_BLOCK, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.THIN_BRANCH, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(DWBlocks.SNOWY_THIN_BRANCH, RenderType.cutoutMipped());

        registerCustomSnowyModel("dry_fern");
        registerCustomSnowyModel("snowy_dry_fern");
        registerCustomSnowyModel("dry_grass");
        registerCustomSnowyModel("snowy_dry_grass");

        ClientInitializer.registerThatchlike(Registration.snowReferenceHolder);
    }


    public ResourceLocation blockLoc(String name) {
        return new ResourceLocation(DWConstants.MODID, "block/" + name);
    }

    public void registerCustomSnowyModel(String name) {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm ->
                new RandomTranslatedModelProvider(blockLoc(name), blockLoc("custom/" + name), false));
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm ->
                new RandomTranslatedModelProvider(blockLoc(name + "_with_snow"), blockLoc("custom/" + name), true));

    }
}
