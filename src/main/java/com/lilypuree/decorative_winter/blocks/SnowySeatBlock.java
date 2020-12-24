package com.lilypuree.decorative_winter.blocks;

import com.lilypuree.decorative_blocks.blocks.SeatBlock;
import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import net.minecraft.block.*;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class SnowySeatBlock extends SeatBlock implements ISnowLoggable {
    private static final VoxelShape SNOW = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape POST_SHAPE_a = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
    private static final VoxelShape JOIST_SHAPE_NS_a = Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 16.0D, 7.0D, 12.0D);
    private static final VoxelShape JOIST_SHAPE_EW_a = Block.makeCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 7.0D, 16.0D);
    private static final VoxelShape SEAT_SHAPE_NS_a = VoxelShapes.or(POST_SHAPE_a, JOIST_SHAPE_NS_a);
    private static final VoxelShape SEAT_SHAPE_EW_a = VoxelShapes.or(POST_SHAPE_a, JOIST_SHAPE_EW_a);
    protected static final VoxelShape SNOWY_SHAPE_NS = VoxelShapes.or(SNOW, SEAT_SHAPE_NS_a);
    protected static final VoxelShape SNOWY_SHAPE_EW = VoxelShapes.or(SNOW, SEAT_SHAPE_EW_a);

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;


    public SnowySeatBlock(Properties properties, IWoodType woodType) {
        super(properties, woodType);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockState state = super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        return updateSnowState(state, facing, facingState, worldIn, currentPos, facingPos);
    }


    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        boolean attached = state.get(ATTACHED);
        if (state.get(SNOWY)) {
            switch (state.get(HORIZONTAL_FACING)) {
                case NORTH:
                case SOUTH:
                    return attached ? SNOWY_SHAPE_NS : JOIST_SHAPE_NS_a;
                case EAST:
                case WEST:
                    return attached ? SNOWY_SHAPE_EW : JOIST_SHAPE_EW_a;
                default:
                    return SEAT_SHAPE_NS_a;
            }
        } else {
            return super.getShape(state, worldIn, pos, context);
        }
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(SNOWY);
    }
}
