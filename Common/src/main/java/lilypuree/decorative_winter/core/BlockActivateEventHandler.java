package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.blocks.PalisadeBlock;
import lilypuree.decorative_blocks.blocks.SeatBlock;
import lilypuree.decorative_winter.DWCommon;
import lilypuree.decorative_winter.WinterUtils;
import lilypuree.decorative_winter.blocks.ISnowLoggable;
import lilypuree.decorative_winter.blocks.ModBlockProperties;
import lilypuree.decorative_winter.blocks.SnowyFoliageBlock;
import lilypuree.decorative_winter.core.DWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.SNOWY;

public class BlockActivateEventHandler {

    private Level level;
    private BlockPos pos;
    private BlockState currentState;
    private Block block;
    private Player player;
    private InteractionHand hand;
    private ItemStack heldItem;

    public BlockActivateEventHandler(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack heldItem) {
        this.level = level;
        this.pos = pos;
        this.player = player;
        this.hand = hand;
        this.heldItem = heldItem;
        this.currentState = level.getBlockState(pos);
        this.block = currentState.getBlock();
    }

    protected boolean isShovel() {
        return heldItem.getItem() instanceof ShovelItem;
    }


    protected boolean canInsertSnow() {
        BlockPos downPos = pos.below();
        BlockState blockUnder = level.getBlockState(downPos);
        return blockUnder.isFaceSturdy(level, downPos, Direction.UP) && heldItem.getItem() == Items.SNOW;
    }

    public InteractionResult getResult() {
        boolean canInsertSnow = canInsertSnow();
        BlockState toReplace = null;
        boolean shovelBlock = false;
        if (block instanceof ISnowLoggable) {
            if (currentState.getValue(SNOWY)) {
                shovelBlock = isShovel();
            } else if (canInsertSnow) {
                toReplace = currentState.setValue(SNOWY, true);
            }
        } else {
            if (block instanceof PalisadeBlock) {
                if (canInsertSnow)
                    toReplace = WinterUtils.getSnowyPalisadeFrom(currentState).setValue(SNOWY, true);
            } else if (block instanceof SeatBlock && canInsertSnow) {
//                toReplace = WinterUtils.getSnowySeatFrom(currentState).setValue(SNOWY, true);
            } else if (block instanceof SnowyFoliageBlock) {
                int currentLevels = currentState.getValue(ModBlockProperties.LAYERS_0_8);
                if (currentLevels < 8 && canInsertSnow) {
                    toReplace = currentState.setValue(ModBlockProperties.LAYERS_0_8, currentLevels + 1);
                } else
                    shovelBlock = isShovel();
            } else if (block == DWBlocks.DRY_TALL_GRASS || block == DWBlocks.DRY_LARGE_FERN) {
                if (canInsertSnow) {
                    return InteractionResult.FAIL;
                }
            } else if (block == Blocks.SNOW) {
                if (heldItem.getItem() instanceof BlockItem blockItem) {
                    if (blockItem.getBlock() instanceof PalisadeBlock) {
                        toReplace = WinterUtils.getSnowyPalisadeFrom(blockItem.getBlock().defaultBlockState()).setValue(SNOWY, true);
                    }
                }
            }
        }
        if (toReplace != null) {
            consumeItemAndReplaceBlock(toReplace);
            level.playSound(player, pos, SoundEvents.SNOW_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.sidedSuccess(true);
        } else if (shovelBlock) {
            shovelSnowFromBlock();
            return InteractionResult.sidedSuccess(true);
        }
        return InteractionResult.PASS;
    }


    private void consumeItemAndReplaceBlock(BlockState newState) {
        if (!level.isClientSide()) {
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            level.setBlockAndUpdate(pos, newState);
        } else {
            player.swing(hand, true);
        }
    }

    private void shovelSnowFromBlock() {
        level.playSound(player, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
        if (!level.isClientSide) {
            DWCommon.removeSnowFromBlock(level, pos, currentState, heldItem);
            if (!player.isCreative()) {
                heldItem.hurtAndBreak(1, player, playerIn -> {
                    playerIn.broadcastBreakEvent(hand);
                });
            }
        } else {
            player.swing(hand, true);
        }
    }


}
