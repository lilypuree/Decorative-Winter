package lilypuree.decorative_winter.mixin;

import lilypuree.decorative_winter.core.DWBlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceTileEntityMixin {

    @Shadow
    protected static void add(Map<Item, Integer> map, TagKey<Item> itemTag, int burnTimeIn) {
    }

    @Shadow
    protected static void add(Map<Item, Integer> map, ItemLike itemProvider, int burnTimeIn) {
    }

    @Inject(method = "getFuel", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void onGetBurnTime(CallbackInfoReturnable<Map<Item, Integer>> cir, Map<Item, Integer> map) {
        add(map, DWBlocks.WREATH, 10);
        add(map, DWBlocks.THIN_BRANCH, 20);
    }
}
