package com.lilypuree.decorative_winter.blocks;

import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BranchBlock extends Block {

    private IWoodType woodType;

    public BranchBlock(Properties properties, IWoodType woodType) {
        super(properties);
        this.woodType = woodType;
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XYZ;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }


    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.woodType.isFlammable();
    }

    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.woodType.isFlammable() ? 50 : super.getFlammability(state, world, pos, face);
    }

    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.woodType.isFlammable() ? 20 : super.getFireSpreadSpeed(state, world, pos, face);
    }
}
