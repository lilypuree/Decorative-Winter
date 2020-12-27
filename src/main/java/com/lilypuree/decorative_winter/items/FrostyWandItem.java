package com.lilypuree.decorative_winter.items;

import com.lilypuree.decorative_blocks.blocks.PalisadeBlock;
import com.lilypuree.decorative_blocks.blocks.SeatBlock;
import com.lilypuree.decorative_winter.blocks.ISnowLoggable;
import com.lilypuree.decorative_winter.blocks.SnowyGrassBlock;
import com.lilypuree.decorative_winter.setup.Registration;
import com.lilypuree.decorative_winter.setup.WinterUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.SNOWY;

public class FrostyWandItem extends Item {

    public static Map<Block, Block> dryableBlocks = new HashMap<>();

    public FrostyWandItem(Properties properties) {
        super(properties);
        dryableBlocks.put(Blocks.GRASS, Registration.DRY_GRASS.get());
        dryableBlocks.put(Blocks.TALL_GRASS, Registration.DRY_TALL_GRASS.get());
        dryableBlocks.put(Blocks.GRASS_BLOCK, Registration.DRY_GRASS_BLOCK.get());
        dryableBlocks.put(Blocks.FERN, Registration.DRY_FERN.get());
        dryableBlocks.put(Blocks.LARGE_FERN, Registration.DRY_LARGE_FERN.get());
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState clickedBlock = world.getBlockState(pos);
        Block block = clickedBlock.getBlock();
        boolean activated = false;
        if (!(block instanceof ISnowLoggable)) {
            if (block instanceof PalisadeBlock) {
                setBlockAndDamageItem(context, pos, WinterUtils.getSnowyPalisadeFrom(clickedBlock).with(SNOWY, false));
                activated = true;
            } else if (block instanceof SeatBlock) {
                setBlockAndDamageItem(context, pos, WinterUtils.getSnowySeatFrom(clickedBlock).with(SNOWY, false));
                activated = true;
            } else if (dryableBlocks.containsKey(block)) {
                dryAllBlocksInRange(context, 3, 2);
                activated = true;
            }
        }
        if (activated) {
            if (world.isRemote()) {
                playEvent(world, pos);
            }
            world.playSound(player, player.getPosition(), SoundEvents.BLOCK_CHAIN_PLACE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return ActionResultType.func_233537_a_(world.isRemote);
        } else {

        }
        return super.onItemUse(context);
    }

    private void dryAllBlocksInRange(ItemUseContext context, int xzRange, int yRange) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        BlockPos.getAllInBoxMutable(pos.add(-xzRange, -yRange, -xzRange), pos.add(xzRange, yRange, xzRange)).forEach(otherPos -> {
            BlockState otherState = world.getBlockState(otherPos);
            if (dryableBlocks.containsKey(otherState.getBlock())) {
                Block driedBlock = dryableBlocks.get(otherState.getBlock());
                setBlockAndDamageItem(context, otherPos, driedBlock.getDefaultState());
                if (driedBlock instanceof DoublePlantBlock) {
                    world.setBlockState(otherPos.up(), driedBlock.getDefaultState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 3);
                } else if (driedBlock instanceof SnowyGrassBlock) {
                    if (world.getBlockState(otherPos.up()).isIn(Blocks.SNOW)) {
                        world.setBlockState(otherPos, driedBlock.getDefaultState().with(SNOWY, true), 3);
                    }
                }
            }
        });
    }

    private void setBlockAndDamageItem(ItemUseContext context, BlockPos pos, BlockState newState) {
        context.getWorld().setBlockState(pos, newState);
        if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
            context.getItem().damageItem(1, context.getPlayer(), playerIn -> {
                playerIn.sendBreakAnimation(context.getHand());
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void playEvent(World world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos);
        double d0 = blockstate.getShape(world, pos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
        double d1 = (double) 0.13125F;
        double d2 = (double) 0.7375F;
        Random random = world.getRandom();
        for (int i = 0; i < 10; ++i) {
            double d3 = random.nextGaussian() * 0.02D;
            double d4 = random.nextGaussian() * 0.02D;
            double d5 = random.nextGaussian() * 0.02D;
            world.addParticle(ParticleTypes.CLOUD, (double) pos.getX() + (double) 0.13125F + (double) 0.7375F * (double) random.nextFloat(), (double) pos.getY() + d0 + (double) random.nextFloat() * (1.0D - d0), (double) pos.getZ() + (double) 0.13125F + (double) 0.7375F * (double) random.nextFloat(), d3, d4, d5);
        }

    }

}
