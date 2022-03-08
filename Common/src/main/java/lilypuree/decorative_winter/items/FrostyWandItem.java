package lilypuree.decorative_winter.items;

import lilypuree.decorative_blocks.blocks.PalisadeBlock;
import lilypuree.decorative_blocks.blocks.SeatBlock;
import lilypuree.decorative_winter.blocks.ISnowLoggable;
import lilypuree.decorative_winter.blocks.SnowyGrassBlock;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.setup.WinterUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.SNOWY;


public class FrostyWandItem extends Item {

    public static Map<Block, Block> dryableBlocks = new HashMap<>();

    public FrostyWandItem(Properties properties) {
        super(properties);
        dryableBlocks.put(Blocks.GRASS, DWBlocks.DRY_GRASS);
        dryableBlocks.put(Blocks.TALL_GRASS, DWBlocks.DRY_TALL_GRASS);
        dryableBlocks.put(Blocks.GRASS_BLOCK, DWBlocks.DRY_GRASS_BLOCK);
        dryableBlocks.put(Blocks.FERN, DWBlocks.DRY_FERN);
        dryableBlocks.put(Blocks.LARGE_FERN, DWBlocks.DRY_LARGE_FERN);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState clickedBlock = world.getBlockState(pos);
        Block block = clickedBlock.getBlock();
        boolean activated = false;
        if (!(block instanceof ISnowLoggable)) {
            if (block instanceof PalisadeBlock) {
                setBlockAndDamageItem(context, pos, WinterUtils.getSnowyPalisadeFrom(clickedBlock).setValue(SNOWY, false));
                activated = true;
            } else if (block instanceof SeatBlock) {
//                setBlockAndDamageItem(context, pos, WinterUtils.getSnowySeatFrom(clickedBlock).setValue(SNOWY, false));
//                activated = true;
            } else if (dryableBlocks.containsKey(block)) {
                dryAllBlocksInRange(context, 3, 2);
                activated = true;
            }
        }
        if (activated) {
            if (world.isClientSide()) {
                playEvent(world, pos);
            }
            world.playSound(player, player.blockPosition(), SoundEvents.CHAIN_PLACE, SoundSource.PLAYERS, 1.0f, 1.0f);
            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {

        }
        return super.useOn(context);
    }

    private void dryAllBlocksInRange(UseOnContext context, int xzRange, int yRange) {
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        BlockPos.betweenClosed(pos.offset(-xzRange, -yRange, -xzRange), pos.offset(xzRange, yRange, xzRange)).forEach(otherPos -> {
            BlockState otherState = world.getBlockState(otherPos);
            if (dryableBlocks.containsKey(otherState.getBlock())) {
                Block driedBlock = dryableBlocks.get(otherState.getBlock());
                setBlockAndDamageItem(context, otherPos, driedBlock.defaultBlockState());
                if (driedBlock instanceof DoublePlantBlock) {
                    world.setBlock(otherPos.above(), driedBlock.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 3);
                } else if (driedBlock instanceof SnowyGrassBlock) {
                    if (world.getBlockState(otherPos.above()).is(Blocks.SNOW)) {
                        world.setBlock(otherPos, driedBlock.defaultBlockState().setValue(SNOWY, true), 3);
                    }
                }
            }
        });
    }

    private void setBlockAndDamageItem(UseOnContext context, BlockPos pos, BlockState newState) {
        context.getLevel().setBlockAndUpdate(pos, newState);
        if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), playerIn -> {
                playerIn.broadcastBreakEvent(context.getHand());
            });
        }
    }

    public static void playEvent(Level world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos);
        double d0 = blockstate.getShape(world, pos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
        double d1 = (double) 0.13125F;
        double d2 = (double) 0.7375F;
        Random random = world.getRandom();
        for (int i = 0; i < 10; ++i) {
            double d3 = random.nextGaussian() * 0.02D;
            double d4 = random.nextGaussian() * 0.02D;
            double d5 = random.nextGaussian() * 0.02D;
            world.addParticle(ParticleTypes.CLOUD, (double) pos.getX() + d1 + d2 * (double) random.nextFloat(), (double) pos.getY() + d0 + (double) random.nextFloat() * (1.0D - d0), (double) pos.getZ() + d1 + d2 * (double) random.nextFloat(), d3, d4, d5);
        }

    }

}
