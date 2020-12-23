package com.lilypuree.decorative_winter.blocks;

import com.lilypuree.decorative_winter.setup.ModSetup;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SnowyFoliageBlock extends TallGrassBlock {

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    public static final IntegerProperty LAYERS = ModBlockProperties.LAYERS_0_8;
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{SHAPE,
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};


    public SnowyFoliageBlock(Properties properties) {
        super(properties);
        this.setDefaultState(getStateContainer().getBaseState().with(SNOWY, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Entity entity = context.getEntity();
        if(entity != null && entity.getType() == EntityType.SNOWBALL){
            return VoxelShapes.fullCube();
        }
        if (state.get(LAYERS) <= 1) return VoxelShapes.empty();
        return SHAPES[state.get(LAYERS) - 1];
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, LAYERS);
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
//        DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == Blocks.FERN ? Blocks.LARGE_FERN : Blocks.TALL_GRASS);
//        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
//            doubleplantblock.placeAt(worldIn, pos, 2);
//        }
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!newState.isAir() && !(newState.getBlock() == this)) {
            ModSetup.spawnSnowBall(worldIn, pos, state.get(LAYERS));
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        if (state.get(LAYERS) > 0) {
            worldIn.setBlockState(pos, Blocks.SNOW.getDefaultState().with(BlockStateProperties.LAYERS_1_8, state.get(LAYERS)));
        }
    }

    @Override
    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        if (projectile.getType() == EntityType.SNOWBALL && !state.get(SNOWY)){
            worldIn.setBlockState(hit.getPos(), state.with(SNOWY, true));
        }
        super.onProjectileCollision(worldIn, state, hit, projectile);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }
}
