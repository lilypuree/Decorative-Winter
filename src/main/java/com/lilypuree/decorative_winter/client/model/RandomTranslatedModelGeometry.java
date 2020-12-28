package com.lilypuree.decorative_winter.client.model;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class RandomTranslatedModelGeometry implements IModelGeometry<RandomTranslatedModelGeometry> {

    private boolean ambientOcclusion;
    private ResourceLocation modelLoc;

    public RandomTranslatedModelGeometry(ResourceLocation resourceLocation, boolean ambientOcclusion) {
        modelLoc = resourceLocation;
        this.ambientOcclusion = ambientOcclusion;
    }

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        IUnbakedModel unbakedModel = bakery.getUnbakedModel(modelLoc);
        IBakedModel bakedModel = unbakedModel.bakeModel(bakery, spriteGetter, modelTransform, modelLocation);
        return new RandomTranslatedModel(bakedModel, ambientOcclusion);
    }


    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        IUnbakedModel unbakedModel = modelGetter.apply(modelLoc);
        return unbakedModel.getTextures(modelGetter, missingTextureErrors);
    }
}
