package com.lilypuree.decorative_winter.client.model;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomTranslatedModel implements IDynamicBakedModel {

    private IBakedModel baseModel;

    public RandomTranslatedModel(IBakedModel baseModel) {
        this.baseModel = baseModel;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        long i = MathHelper.getCoordinateRandom(rand.nextInt(), 0, rand.nextInt());
        Vector2f offset = new Vector2f(((i & 15L) / 15.0F - 0.5F) * 0.5F, (((i >> 8 & 15L) / 15.0F) - 0.5F) * 0.5F);
        return baseModel.getQuads(state, side, rand, extraData).stream().map(quad -> getOffsetQuad(quad, offset)).collect(Collectors.toList());
    }

    private BakedQuad getOffsetQuad(BakedQuad quadIn, Vector2f offset) {
        int[] vertexData = quadIn.getVertexData().clone();
        for (int i = 0, j = 0; i < 4; i++, j += DefaultVertexFormats.BLOCK.getIntegerSize()) {
            float x = Float.intBitsToFloat(vertexData[j]);
            float z = Float.intBitsToFloat(vertexData[j + 2]);
            x += offset.x;
            z += offset.y;
            vertexData[j] = Float.floatToIntBits(x);
            vertexData[j + 2] = Float.floatToIntBits(z);
        }
        return new BakedQuad(vertexData, quadIn.getTintIndex(), quadIn.getFace(), quadIn.getSprite(), quadIn.applyDiffuseLighting());
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
        return baseModel.getParticleTexture(data);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return baseModel.isGui3d();
    }

    @Override
    public boolean isSideLit() {
        return baseModel.isSideLit();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return baseModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return baseModel.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return baseModel.getOverrides();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return baseModel.getItemCameraTransforms();
    }
}
