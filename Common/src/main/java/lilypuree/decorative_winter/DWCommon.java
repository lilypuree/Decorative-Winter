package lilypuree.decorative_winter;

import lilypuree.decorative_blocks.CommonAPI;
import lilypuree.decorative_winter.blocks.ModBlockProperties;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.core.Registration;
import lilypuree.decorative_winter.mixin.FireBlockInvoker;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.SNOWY;

public class DWCommon {

    public static void init() {
        CommonAPI.addThatchlikeFluid(Registration.snowReferenceHolder);
        
        FireBlockInvoker invoker = ((FireBlockInvoker) ((Object) Blocks.FIRE));
        invoker.invokeSetFlammable(DWBlocks.WREATH.get(), 5, 20);
//        invoker.invokeSetFlammable(DWBlocks.DRY_GRASS, 60, 80);
//        invoker.invokeSetFlammable(DWBlocks.DRY_TALL_GRASS, 60, 80);
//        invoker.invokeSetFlammable(DWBlocks.DRY_GRASS_BLOCK, 60, 80);
//        invoker.invokeSetFlammable(DWBlocks.DRY_FERN, 60, 80);
//        invoker.invokeSetFlammable(DWBlocks.DRY_LARGE_FERN, 60, 80);
    }

    public static void removeSnowFromBlock(Level world, BlockPos pos, BlockState block, ItemStack heldItem) {
        if (block.hasProperty(ModBlockProperties.LAYERS_0_8)) {
            int levels = block.getValue(ModBlockProperties.LAYERS_0_8);
            world.setBlockAndUpdate(pos, block.setValue(ModBlockProperties.LAYERS_0_8, 0));
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(Enchantments.SILK_TOUCH)) {
                spawnSnow(world, pos, levels);
            } else {
                spawnSnowBall(world, pos, levels);
            }
        } else if (block.hasProperty(SNOWY)) {
            world.setBlockAndUpdate(pos, block.setValue(SNOWY, false));
            if (EnchantmentHelper.getEnchantments(heldItem).containsKey(Enchantments.SILK_TOUCH)) {
                spawnSnow(world, pos, 1);
            } else {
                spawnSnowBall(world, pos, 1);
            }
        }
    }

    public static void spawnSnowBall(LevelAccessor world, BlockPos pos, int count) {
        spawnItem(world, pos, new ItemStack(Items.SNOWBALL, count));
    }

    public static void spawnSnow(LevelAccessor world, BlockPos pos, int count) {
        spawnItem(world, pos, new ItemStack(Items.SNOW, count));
    }

    public static void spawnItem(LevelAccessor world, BlockPos pos, ItemStack itemStack) {
        if (world instanceof ServerLevel) {
            double dx = (double) (world.getRandom().nextFloat() * 0.7F) + 0.15000000596046448D;
            double dy = (double) (world.getRandom().nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
            double dz = (double) (world.getRandom().nextFloat() * 0.7F) + 0.15000000596046448D;
            ItemEntity itemEntity = new ItemEntity((ServerLevel) world, (double) pos.getX() + dx, (double) pos.getY() + dy, (double) pos.getZ() + dz, itemStack);
            itemEntity.setDefaultPickUpDelay();
            world.addFreshEntity(itemEntity);
        }
    }
}
