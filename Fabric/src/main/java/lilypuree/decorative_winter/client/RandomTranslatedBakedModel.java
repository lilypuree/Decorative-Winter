//package lilypuree.decorative_winter.client;
//
//import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
//import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
//import net.minecraft.client.renderer.block.model.BakedQuad;
//import net.minecraft.client.renderer.block.model.ItemOverrides;
//import net.minecraft.client.renderer.block.model.ItemTransforms;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.*;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.BlockAndTintGetter;
//import net.minecraft.world.level.block.state.BlockState;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//import java.util.Random;
//import java.util.function.Supplier;
//
//public class RandomTranslatedBakedModel implements BakedModel,  RandomTranslatedModelBase {
//    private BakedModel baseModel;
//    private boolean ambientOcclusion;
//
//    public RandomTranslatedBakedModel(BakedModel baseModel, boolean ambientOcclusion) {
//        this.baseModel = baseModel;
//        this.ambientOcclusion = ambientOcclusion;
//    }
//    @Override
//    public BakedModel getBaseModel() {
//        return baseModel;
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
//        return getQuadsDefault(state, side, rand);
//    }
//    @Override
//    public boolean useAmbientOcclusion() {
//        return ambientOcclusion;
//    }
//    @Override
//    public boolean isGui3d() {
//        return baseModel.isGui3d();
//    }
//    @Override
//    public boolean usesBlockLight() {
//        return baseModel.usesBlockLight();
//    }
//    @Override
//    public boolean isCustomRenderer() {
//        return baseModel.isCustomRenderer();
//    }
//    @Override
//    public TextureAtlasSprite getParticleIcon() {
//        return baseModel.getParticleIcon();
//    }
//    @Override
//    public ItemTransforms getTransforms() {
//        return baseModel.getTransforms();
//    }
//    @Override
//    public ItemOverrides getOverrides() {
//        return baseModel.getOverrides();
//    }
//}
