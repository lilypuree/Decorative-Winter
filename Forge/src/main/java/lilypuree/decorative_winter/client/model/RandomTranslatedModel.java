package lilypuree.decorative_winter.client.model;

import lilypuree.decorative_winter.client.RandomTranslatedModelBase;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RandomTranslatedModel implements IDynamicBakedModel, RandomTranslatedModelBase {

    private BakedModel baseModel;
    private boolean ambientOcclusion;

    public RandomTranslatedModel(BakedModel baseModel, boolean ambientOcclusion) {
        this.baseModel = baseModel;
        this.ambientOcclusion = ambientOcclusion;
    }
    @Override
    public BakedModel getBaseModel() {
        return baseModel;
    }
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        return getQuadsDefault(state, side, rand);
    }

//    @Override
//    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
//        return baseModel.getParticleTexture(data);
//    }

    @Override
    public boolean useAmbientOcclusion() {
        return ambientOcclusion;
    }

    @Override
    public boolean isGui3d() {
        return baseModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return baseModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return baseModel.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return baseModel.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return baseModel.getOverrides();
    }

    @Override
    public ItemTransforms getTransforms() {
        return baseModel.getTransforms();
    }
}
