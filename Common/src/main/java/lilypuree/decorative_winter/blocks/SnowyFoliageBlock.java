package lilypuree.decorative_winter.blocks;

import lilypuree.decorative_winter.DWCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class SnowyFoliageBlock extends TallGrassBlock {

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    public static final IntegerProperty LAYERS = ModBlockProperties.LAYERS_0_8;
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{SHAPE,
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};


    public SnowyFoliageBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any().setValue(SNOWY, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(LAYERS)];
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return SHAPES[state.getValue(LAYERS)];
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityCollisionContext) {
            if (entityCollisionContext.getEntity() != null)
                if (entityCollisionContext.getEntity().getType() == EntityType.SNOWBALL)
                    return Shapes.block();

        }
        if (state.getValue(LAYERS) <= 1) return Shapes.empty();
        return SHAPES[state.getValue(LAYERS) - 1];
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return SHAPES[state.getValue(LAYERS)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, LAYERS);
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
//        DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == Blocks.FERN ? Blocks.LARGE_FERN : Blocks.TALL_GRASS);
//        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
//            doubleplantblock.placeAt(worldIn, pos, 2);
//        }
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!newState.isAir() && !(newState.getBlock() == this)) {
            DWCommon.spawnSnowBall(worldIn, pos, state.getValue(LAYERS));
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if (state.getValue(LAYERS) > 0) {
            worldIn.setBlockAndUpdate(pos, Blocks.SNOW.defaultBlockState().setValue(BlockStateProperties.LAYERS, state.getValue(LAYERS)));
        }
    }

    @Override
    public void onProjectileHit(Level worldIn, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (projectile.getType() == EntityType.SNOWBALL && !state.getValue(SNOWY)) {
            worldIn.setBlockAndUpdate(hit.getBlockPos(), state.setValue(SNOWY, true));
        }
        super.onProjectileHit(worldIn, state, hit, projectile);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean isSnow = context.getLevel().getBlockState(context.getClickedPos()).is(Blocks.SNOW);
        return super.getStateForPlacement(context).setValue(SNOWY, isSnow);

    }
    @Override
    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}
