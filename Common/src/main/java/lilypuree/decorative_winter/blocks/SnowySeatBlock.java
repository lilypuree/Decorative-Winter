package lilypuree.decorative_winter.blocks;

import lilypuree.decorative_blocks.blocks.IWoodenBlock;
import lilypuree.decorative_blocks.datagen.types.IWoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SnowySeatBlock extends HorizontalDirectionalBlock implements ISnowLoggable, IWoodenBlock {
    private static final VoxelShape SNOW = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape POST_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
    protected static final VoxelShape JOIST_NS = Block.box(0.0D, 4.0D, 4.0D, 16.0D, 7.0D, 12.0D);
    protected static final VoxelShape JOIST_EW = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 7.0D, 16.0D);
    protected static final VoxelShape SEAT_NS = Shapes.or(POST_SHAPE, JOIST_NS);
    protected static final VoxelShape SEAT_EW = Shapes.or(POST_SHAPE, JOIST_NS);
    protected static final VoxelShape SNOWY_SEAT_NS = Shapes.or(SNOW, SEAT_NS);
    protected static final VoxelShape SNOWY_SEAT_EW = Shapes.or(SNOW, SEAT_EW);
    protected static final VoxelShape SNOWY_JOIST_NS = Shapes.or(SNOW, JOIST_NS);
    protected static final VoxelShape SNOWY_JOIST_EW = Shapes.or(SNOW, JOIST_EW);

    //    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    public static final BooleanProperty ATTACHED = BlockStateProperties.ATTACHED;
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    private IWoodType woodType;

    public SnowySeatBlock(Properties properties, IWoodType woodType) {
        super(properties);
        this.woodType = woodType;
        this.registerDefaultState(this.getStateDefinition().any().setValue(ATTACHED, false).setValue(SNOWY, false));
    }
    @Override
    public IWoodType getWoodType() {
        return woodType;
    }
    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockState state = super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        return updateSnowState(state, facing, facingState, worldIn, currentPos, facingPos);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        boolean attached = state.getValue(ATTACHED);
        if (state.getValue(SNOWY)) {
            switch (state.getValue(FACING)) {
                case NORTH:
                case SOUTH:
                    return attached ? SNOWY_SEAT_NS : SNOWY_JOIST_NS;
                case EAST:
                case WEST:
                    return attached ? SNOWY_SEAT_EW : SNOWY_JOIST_EW;
                default:
                    return SNOW;
            }
        } else {
            switch (state.getValue(FACING)) {
                case NORTH:
                case SOUTH:
                    return attached ? SEAT_NS : JOIST_NS;
                case EAST:
                case WEST:
                    return attached ? SEAT_EW : JOIST_EW;
                default:
                    return SNOW;
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState ifluidstate = world.getFluidState(pos);
        ItemStack stack = context.getItemInHand();
        boolean attachedFlag = this.isInAttachablePos(world, pos);

        Direction facingDir = context.getClickedFace();
        Direction placementDir;
        if (facingDir != Direction.DOWN && facingDir != Direction.UP) {
            placementDir = facingDir.getClockWise();
        } else {
            placementDir = context.getHorizontalDirection().getOpposite();
        }
        BlockState blockstate = this.defaultBlockState().setValue(FACING, placementDir).setValue(ATTACHED, attachedFlag);

        return blockstate;
    }

    private boolean isInAttachablePos(LevelReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.below()).getBlock() == Blocks.LANTERN ? true : Block.canSupportCenter(worldIn, pos.below(), Direction.UP);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, FACING, ATTACHED);
    }
}
