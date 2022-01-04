package lilypuree.decorative_winter.client;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class RandomTranslatedUnbakedModel implements UnbakedModel {

    private boolean ambientOcclusion;
    private ResourceLocation modelLoc;

    public RandomTranslatedUnbakedModel(ResourceLocation modelLoc, boolean ambientOcclusion) {
        this.modelLoc = modelLoc;
        this.ambientOcclusion = ambientOcclusion;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Collections.singleton(modelLoc);
    }
    @Override
    public Collection<Material> getMaterials(Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        UnbakedModel unbakedModel = modelGetter.apply(modelLoc);
        return unbakedModel.getMaterials(modelGetter, missingTextureErrors);
    }
    @Nullable
    @Override
    public BakedModel bake(ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ResourceLocation modelLocation) {
        UnbakedModel unbakedModel = bakery.getModel(modelLoc);
        BakedModel bakedModel = unbakedModel.bake(bakery, spriteGetter, modelTransform, modelLocation);
        return new RandomTranslatedBakedModel(bakedModel, ambientOcclusion);
    }
}
