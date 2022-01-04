package lilypuree.decorative_winter.blocks;

import lilypuree.decorative_winter.DWCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.SNOWY;

public interface ISnowLoggable {
    default BlockState updateSnowState(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN && stateIn.getValue(SNOWY)) {
            if (!facingState.isFaceSturdy(worldIn, facingPos, Direction.UP)) {
                DWCommon.spawnSnowBall(worldIn, currentPos, 1);
                stateIn = stateIn.setValue(SNOWY, false);
            }
        }

        return stateIn;
    }
}
