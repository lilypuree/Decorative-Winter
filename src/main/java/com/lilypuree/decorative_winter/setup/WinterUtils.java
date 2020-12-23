package com.lilypuree.decorative_winter.setup;

import com.lilypuree.decorative_blocks.blocks.PalisadeBlock;
import com.lilypuree.decorative_blocks.blocks.SeatBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;

public class WinterUtils {

    public static BlockState getSnowyPalisadeFrom(BlockState palisadeBlock) {
        if (palisadeBlock.getBlock() instanceof PalisadeBlock) {
            Block snowyPalisadeBlock = Registration.getSnowyPalisadeBlock(((PalisadeBlock) palisadeBlock.getBlock()).getWoodType());
            return snowyPalisadeBlock.getDefaultState()
                    .with(BlockStateProperties.NORTH, palisadeBlock.get(BlockStateProperties.NORTH))
                    .with(BlockStateProperties.SOUTH, palisadeBlock.get(BlockStateProperties.SOUTH))
                    .with(BlockStateProperties.EAST, palisadeBlock.get(BlockStateProperties.EAST))
                    .with(BlockStateProperties.WEST, palisadeBlock.get(BlockStateProperties.WEST))
                    .with(BlockStateProperties.WATERLOGGED, false)
                    .with(BlockStateProperties.SNOWY, false);
        }
        return palisadeBlock;
    }

    public static BlockState getSnowySeatFrom(BlockState seatBlock) {
        if (seatBlock.getBlock() instanceof SeatBlock) {
            Block snowySeatBlock = Registration.getSnowySeatBlock(((SeatBlock) seatBlock.getBlock()).getWoodType());
            return snowySeatBlock.getDefaultState()
                    .with(BlockStateProperties.HORIZONTAL_FACING, seatBlock.get(BlockStateProperties.HORIZONTAL_FACING))
                    .with(BlockStateProperties.OCCUPIED, seatBlock.get(BlockStateProperties.OCCUPIED))
                    .with(BlockStateProperties.ATTACHED, seatBlock.get(BlockStateProperties.ATTACHED))
                    .with(BlockStateProperties.WATERLOGGED, false)
                    .with(BlockStateProperties.SNOWY, false);
        }
        return seatBlock;
    }
}
