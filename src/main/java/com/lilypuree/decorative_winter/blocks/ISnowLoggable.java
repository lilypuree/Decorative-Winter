package com.lilypuree.decorative_winter.blocks;

import com.lilypuree.decorative_winter.setup.ModSetup;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

import static net.minecraft.state.properties.BlockStateProperties.SNOWY;

public interface ISnowLoggable {


    default BlockState updateSnowState(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN && stateIn.get(SNOWY)) {
            if (!facingState.isSolidSide(worldIn, facingPos, Direction.UP)) {
                ModSetup.spawnSnowBall(worldIn, currentPos, 1);
                stateIn = stateIn.with(SNOWY, false);
            }
        }
        return stateIn;
    }
}
