package lilypuree.decorative_winter.core.setup;

import lilypuree.decorative_blocks.blocks.PalisadeBlock;
import lilypuree.decorative_blocks.blocks.SeatBlock;
import lilypuree.decorative_winter.core.DWBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class WinterUtils {

    public static BlockState getSnowyPalisadeFrom(BlockState block) {
        if (block.getBlock() instanceof PalisadeBlock palisadeBlock) {
            Block snowyPalisadeBlock = DWBlocks.SNOWY_PALISADES.get(palisadeBlock.getWoodType());
            if (snowyPalisadeBlock == null) return block;
            return snowyPalisadeBlock.defaultBlockState()
                    .setValue(BlockStateProperties.NORTH, block.getValue(BlockStateProperties.NORTH))
                    .setValue(BlockStateProperties.SOUTH, block.getValue(BlockStateProperties.SOUTH))
                    .setValue(BlockStateProperties.EAST, block.getValue(BlockStateProperties.EAST))
                    .setValue(BlockStateProperties.WEST, block.getValue(BlockStateProperties.WEST))
                    .setValue(BlockStateProperties.WATERLOGGED, false)
                    .setValue(BlockStateProperties.SNOWY, false);
        }
        return block;
    }

    public static BlockState getSnowySeatFrom(BlockState block) {
        if (block.getBlock() instanceof SeatBlock seatBlock) {
            Block snowySeatBlock = DWBlocks.SNOWY_SEATS.get(seatBlock.getWoodType());
            if (snowySeatBlock == null) return block;
            return snowySeatBlock.defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, block.getValue(BlockStateProperties.HORIZONTAL_FACING))
                    .setValue(BlockStateProperties.OCCUPIED, block.getValue(BlockStateProperties.OCCUPIED))
                    .setValue(BlockStateProperties.ATTACHED, block.getValue(BlockStateProperties.ATTACHED))
                    .setValue(BlockStateProperties.WATERLOGGED, false)
                    .setValue(BlockStateProperties.SNOWY, false);
        }
        return block;
    }
}
