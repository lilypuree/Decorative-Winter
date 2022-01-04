package lilypuree.decorative_winter.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public interface RandomTranslatedModelBase {

    BakedModel getBaseModel();

    default List<BakedQuad> getQuadsDefault(BlockState state, Direction side, Random rand) {
        long i = Mth.getSeed(rand.nextInt(), 0, rand.nextInt());
        Vec2 offset = new Vec2(((i & 15L) / 15.0F - 0.5F) * 0.5F, (((i >> 8 & 15L) / 15.0F) - 0.5F) * 0.5F);
        return getBaseModel().getQuads(state, side, rand).stream().map(quad -> getOffsetQuad(quad, offset)).collect(Collectors.toList());
    }

    public static BakedQuad getOffsetQuad(BakedQuad quadIn, Vec2 offset) {
        int[] vertexData = quadIn.getVertices().clone();
        for (int i = 0, j = 0; i < 4; i++, j += DefaultVertexFormat.BLOCK.getIntegerSize()) {
            float x = Float.intBitsToFloat(vertexData[j]);
            float z = Float.intBitsToFloat(vertexData[j + 2]);
            x += offset.x;
            z += offset.y;
            vertexData[j] = Float.floatToIntBits(x);
            vertexData[j + 2] = Float.floatToIntBits(z);
        }
        return new BakedQuad(vertexData, quadIn.getTintIndex(), quadIn.getDirection(), quadIn.getSprite(), quadIn.isShade());
    }
}
