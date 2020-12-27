package com.lilypuree.decorative_winter.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowyBakedModel implements IDynamicBakedModel {

    public static ModelProperty<Integer> LAYERS = new ModelProperty<>();
    private TextureAtlasSprite snowTexture;
    private boolean isAO;
    private boolean isGui3d;
    private boolean isSideLit;
    private ItemOverrideList overrideList;
    private ItemCameraTransforms cameraTransforms;

    public SnowyBakedModel(TextureAtlasSprite snowTexture, boolean isAO, boolean isGui3d, boolean isSideLit, ItemOverrideList itemOverrideList, ItemCameraTransforms itemCameraTransforms) {
        this.snowTexture = snowTexture;
        this.isAO = isAO;
        this.isGui3d = isGui3d;
        this.isSideLit = isSideLit;
        this.overrideList = itemOverrideList;
        this.cameraTransforms = itemCameraTransforms;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return getQuads(state, side, rand, EmptyModelData.INSTANCE);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        List<BakedQuad> quads = new ArrayList<>();
        int layers;
        if (extraData.hasProperty(LAYERS)) {
            layers = extraData.getData(LAYERS);
        } else {
            layers = 1;
        }
        putCuboid(quads, new Vector3f(0, 0, 0), new Vector3f(1.0f, layers / 8.0f, 1.0f), snowTexture, 0);
        return quads;
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData tileData) {
        IModelData modelData = tileData;
        if (state.hasProperty(BlockStateProperties.SNOWY)) {
            ArrayList<Integer> layers = new ArrayList<>();

            BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1), pos.add(1, 0, 1)).forEach(pos1 -> {
                BlockState nextBlock = world.getBlockState(pos1);
                if (nextBlock.getBlock() == Blocks.SNOW) {
                    layers.add(nextBlock.get(BlockStateProperties.LAYERS_1_8));
                }
            });
            int snowBlocks = layers.size();
            if (snowBlocks == 0) {
                modelData.setData(LAYERS, 1);
            } else {
                modelData.setData(LAYERS, Math.round((float) layers.stream().reduce(0, Integer::sum) / (snowBlocks)));
            }
        }
        return modelData;
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
        return snowTexture;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return isAO;
    }

    @Override
    public boolean isGui3d() {
        return isGui3d;
    }

    @Override
    public boolean isSideLit() {
        return isSideLit;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return snowTexture;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return overrideList;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return cameraTransforms;
    }

    public static void putCuboid(List<BakedQuad> quads, Vector3f v1, Vector3f v7, TextureAtlasSprite texture, int tintindex) {
        float x = v7.getX() - v1.getX();
        float y = v7.getY() - v1.getY();
        float z = v7.getZ() - v1.getZ();
        Vector3f v2 = new Vector3f(v7.getX(), v1.getY(), v1.getZ());
        Vector3f v3 = new Vector3f(v7.getX(), v1.getY(), v7.getZ());
        Vector3f v4 = new Vector3f(v1.getX(), v1.getY(), v7.getZ());
        Vector3f v5 = new Vector3f(v1.getX(), v7.getY(), v1.getZ());
        Vector3f v6 = new Vector3f(v7.getX(), v7.getY(), v1.getZ());
        Vector3f v8 = new Vector3f(v1.getX(), v7.getY(), v7.getZ());

        float uvX = x * 16;
        float uvY = y * 16;
        float uvZ = z * 16;

        quads.add(createQuad(v1, v2, v3, v4, 0, 0, uvX, uvZ, texture, tintindex));
        quads.add(createQuad(v1, v5, v6, v2, 0, 0, uvX, uvY, texture, tintindex));
        quads.add(createQuad(v2, v6, v7, v3, 0, 0, uvX, uvY, texture, tintindex));
        quads.add(createQuad(v3, v7, v8, v4, 0, 0, uvX, uvY, texture, tintindex));
        quads.add(createQuad(v4, v8, v5, v1, 0, 0, uvX, uvY, texture, tintindex));
        quads.add(createQuad(v5, v8, v7, v6, 0, 0, uvX, uvZ, texture, tintindex));
    }

    public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, float u, float v, float u_, float v_, TextureAtlasSprite sprite) {
        return createQuad(v1, v2, v3, v4, u, v, u_, v_, sprite, -1);
    }

    public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, float u, float v, float u_, float v_, TextureAtlasSprite sprite, boolean flip) {
        return createQuad(v1, v2, v3, v4, u, v, u_, v_, sprite, -1, flip);
    }

    public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, float u, float v, float u_, float v_, TextureAtlasSprite sprite, int tintIndex) {
        return createQuad(v1, v2, v3, v4, u, v, u_, v_, sprite, tintIndex, false);
    }

    public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, float u, float v, float u_, float v_, TextureAtlasSprite sprite, int tintIndex, boolean flip) {
        Vector3f normal = v3.copy();
        normal.sub(v2);
        Vector3f other = v1.copy();
        other.sub(v2);
        normal.cross(other);
        normal.normalize();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        builder.setQuadTint(tintIndex);
        float rgb = 1.0f;
        if (flip) {
            builder.setQuadOrientation(Direction.getFacingFromVector(-normal.getX(), -normal.getY(), -normal.getZ()));
            putVertex(builder, normal, v1.getX(), v1.getY(), v1.getZ(), u_, v, sprite, rgb, rgb, rgb);
            putVertex(builder, normal, v4.getX(), v4.getY(), v4.getZ(), u, v, sprite, rgb, rgb, rgb);
            putVertex(builder, normal, v3.getX(), v3.getY(), v3.getZ(), u, v_, sprite, rgb, rgb, rgb);
            putVertex(builder, normal, v2.getX(), v2.getY(), v2.getZ(), u_, v_, sprite, rgb, rgb, rgb);
        } else {
            builder.setQuadOrientation(Direction.getFacingFromVector(normal.getX(), normal.getY(), normal.getZ()));
            putVertex(builder, normal, v1.getX(), v1.getY(), v1.getZ(), u_, v, sprite, rgb, rgb, rgb);
            putVertex(builder, normal, v2.getX(), v2.getY(), v2.getZ(), u_, v_, sprite, rgb, rgb, rgb);
            putVertex(builder, normal, v3.getX(), v3.getY(), v3.getZ(), u, v_, sprite, rgb, rgb, rgb);
            putVertex(builder, normal, v4.getX(), v4.getY(), v4.getZ(), u, v, sprite, rgb, rgb, rgb);
        }

        return builder.build();
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void putVertex(BakedQuadBuilder builder, Vector3f normal,
                                 double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

        ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
        if (builder.getVertexFormat() != DefaultVertexFormats.BLOCK) {
            LOGGER.error("Non Block Baked Quad building!");
        }
        for (int j = 0; j < elements.size(); j++) {
            VertexFormatElement e = elements.get(j);
            switch (e.getUsage()) {
                case POSITION:
                    builder.put(j, (float) x, (float) y, (float) z, 1.0f);
                    break;
                case COLOR:
                    builder.put(j, r, g, b, 1.0f);
                    break;
                case UV:
                    switch (e.getIndex()) {
                        case 0:
                            float iu = sprite.getInterpolatedU(u);
                            float iv = sprite.getInterpolatedV(v);
                            builder.put(j, iu, iv);
                            break;
                        case 2:
                            builder.put(j, (short) 0, (short) 0);
                            break;
                        default:
                            builder.put(j, (short) 0, (short) 0);
                            break;
                    }
                    break;
                case NORMAL:
                    builder.put(j, (float) normal.getX(), (float) normal.getY(), (float) normal.getZ());
                    break;
                default:
                    if (j >= 6) break;
                    builder.put(j);
                    break;
            }
        }
    }
}
