package lilypuree.decorative_winter.core.factory;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.datagen.types.IWoodType;
import lilypuree.decorative_winter.core.DWBlocks;
import lilypuree.decorative_winter.items.FrostyWandItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ItemSuppliers {
    public static final Item.Properties modItemProperties = new Item.Properties().tab(Constants.ITEM_GROUP);
    public static final Item.Properties dummyProperty = new Item.Properties();

    public static final Supplier<Item> FROSTY_WAND = () -> new FrostyWandItem(new Item.Properties().tab(Constants.ITEM_GROUP).stacksTo(1));

    public static Item blockItem(Block block){
        return new BlockItem(block, modItemProperties);
    }

    public static Item getSnowySeat(IWoodType woodType) {
        return new BlockItem(DWBlocks.SNOWY_SEATS.get(woodType), modItemProperties);
    }

    public static Item getSnowyPalisade(IWoodType woodType) {
        return new BlockItem(DWBlocks.SNOWY_PALISADES.get(woodType), modItemProperties);
    }
}
