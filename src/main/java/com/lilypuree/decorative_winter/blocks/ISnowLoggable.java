package com.lilypuree.decorative_winter.blocks;

import com.lilypuree.decorative_winter.EventListener;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import static net.minecraft.state.properties.BlockStateProperties.SNOWY;

public interface ISnowLoggable {


    default BlockState updateSnowState(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN && stateIn.get(SNOWY)) {
            if (!facingState.isSolidSide(worldIn, facingPos, Direction.UP)) {
                EventListener.spawnSnowBall(worldIn, currentPos, 1);
                stateIn = stateIn.with(SNOWY, false);
            }
        }
        return stateIn;
    }
}
