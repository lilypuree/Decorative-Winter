package com.lilypuree.decorative_winter.setup;

import com.lilypuree.decorative_blocks.blocks.PalisadeBlock;
import com.lilypuree.decorative_blocks.blocks.SeatBlock;
import com.lilypuree.decorative_winter.DecorativeWinter;
import com.lilypuree.decorative_winter.blocks.ISnowLoggable;
import com.lilypuree.decorative_winter.blocks.ModBlockProperties;
import com.lilypuree.decorative_winter.blocks.SnowyFoliageBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.client.renderer.model.BlockPart;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.minecraft.state.properties.BlockStateProperties.SNOWY;


@Mod.EventBusSubscriber(modid = DecorativeWinter.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Method setFireInfo = ObfuscationReflectionHelper.findMethod(FireBlock.class, "func_180686_a", Block.class, int.class, int.class);
            int i = 1;
            try {
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_GRASS.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_FERN.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_LARGE_FERN.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_TALL_GRASS.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_GRASS_BLOCK.get(), 60, 100);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }


    @SubscribeEvent
    public static void onBlockActivated(PlayerInteractEvent.RightClickBlock event) {
        ItemStack heldItem = event.getItemStack();
        World world = event.getWorld();
        PlayerEntity player = event.getPlayer();

        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        BlockPos downPos = pos.down();
        BlockState blockUnder = world.getBlockState(downPos);
        boolean canInsertSnow = blockUnder.isSolidSide(world, downPos, Direction.UP) && heldItem.getItem() == Items.SNOW;

        if (block instanceof ISnowLoggable) {
            if (state.get(SNOWY)) {
                if (heldItem.getToolTypes().contains(ToolType.SHOVEL)) {
                    shovelSnowFromBlock(event, state);
                    event.setUseItem(Event.Result.DENY);
                    event.setUseBlock(Event.Result.DENY);
                }
            } else if (canInsertSnow) {
                consumeItemAndReplaceBlock(event, state.with(SNOWY, true));
                playSnowSound(world, player, pos);
                event.setUseItem(Event.Result.DENY);
                event.setUseBlock(Event.Result.DENY);
            }
        } else {
            if (block instanceof PalisadeBlock && canInsertSnow) {
                consumeItemAndReplaceBlock(event, WinterUtils.getSnowyPalisadeFrom(state).with(SNOWY, true));
                playSnowSound(world, player, pos);
                event.setUseBlock(Event.Result.DENY);
                event.setUseItem(Event.Result.DENY);
            } else if (block instanceof SeatBlock) {
                if (canInsertSnow){
                    consumeItemAndReplaceBlock(event, WinterUtils.getSnowySeatFrom(state).with(SNOWY, true));
                    playSnowSound(world, player, pos);
                    event.setUseBlock(Event.Result.DENY);
                    event.setUseItem(Event.Result.DENY);
                }
            } else if (block instanceof SnowyFoliageBlock) {
                int currentLevels = state.get(ModBlockProperties.LAYERS_0_8);
                if (currentLevels < 8 && canInsertSnow) {
                    consumeItemAndReplaceBlock(event, state.with(ModBlockProperties.LAYERS_0_8, currentLevels + 1));
                    playSnowSound(world, player, pos);
                    event.setUseBlock(Event.Result.DENY);
                    event.setUseItem(Event.Result.DENY);
                }
                if (heldItem.getToolTypes().contains(ToolType.SHOVEL)) {
                    shovelSnowFromBlock(event, state);
                    event.setUseItem(Event.Result.DENY);
                }
            } else if (block == Registration.DRY_TALL_GRASS.get() || block == Registration.DRY_LARGE_FERN.get()) {
                if (canInsertSnow) {
                    event.setUseItem(Event.Result.DENY);
                }
            }
        }
    }

    private static void playSnowSound(World world, PlayerEntity player, BlockPos pos) {
        world.playSound(player, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private static void shovelSnowFromBlock(PlayerInteractEvent event, BlockState currentState) {
        if (!event.getWorld().isRemote) {
            event.getWorld().playSound(event.getPlayer(), event.getPos(), SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            removeSnowFromBlock(event.getWorld(), event.getPos(), currentState, event.getItemStack());
            if (!event.getPlayer().isCreative()) {
                event.getItemStack().damageItem(1, event.getPlayer(), playerIn -> {
                    playerIn.sendBreakAnimation(event.getHand());
                });
            }
        } else {
            event.getPlayer().swing(event.getHand(), true);
        }
    }

    private static void consumeItemAndReplaceBlock(PlayerInteractEvent event, BlockState newState) {
        if (!event.getWorld().isRemote()) {
            if (!event.getPlayer().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getWorld().setBlockState(event.getPos(), newState);
        } else {
            event.getPlayer().swing(event.getHand(), true);
        }
    }


    public static void removeSnowFromBlock(World world, BlockPos pos, BlockState block, ItemStack heldItem) {
        if (block.hasProperty(ModBlockProperties.LAYERS_0_8)) {
            int levels = block.get(ModBlockProperties.LAYERS_0_8);
            world.setBlockState(pos, block.with(ModBlockProperties.LAYERS_0_8, 0));
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(Enchantments.SILK_TOUCH)) {
                spawnSnow(world, pos, levels);
            } else {
                spawnSnowBall(world, pos, levels);
            }
        } else if (block.hasProperty(SNOWY)) {
            world.setBlockState(pos, block.with(SNOWY, false));
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(Enchantments.SILK_TOUCH)) {
                spawnSnow(world, pos, 1);
            } else {
                spawnSnowBall(world, pos, 1);
            }
        }
    }

    public static void spawnSnowBall(IWorld world, BlockPos pos, int count) {
        spawnItem(world, pos, new ItemStack(Items.SNOWBALL, count));
    }

    public static void spawnSnow(IWorld world, BlockPos pos, int count) {
        spawnItem(world, pos, new ItemStack(Items.SNOW, count));
    }

    public static void spawnItem(IWorld world, BlockPos pos, ItemStack itemStack) {
        if (world instanceof ServerWorld) {
            double dx = (double) (world.getRandom().nextFloat() * 0.7F) + 0.15000000596046448D;
            double dy = (double) (world.getRandom().nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
            double dz = (double) (world.getRandom().nextFloat() * 0.7F) + 0.15000000596046448D;
            ItemEntity itemEntity = new ItemEntity((ServerWorld) world, (double) pos.getX() + dx, (double) pos.getY() + dy, (double) pos.getZ() + dz, itemStack);
            itemEntity.setDefaultPickupDelay();
            world.addEntity(itemEntity);
        }
    }


}
