package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_blocks.blocks.types.VanillaWoodTypes;
import lilypuree.decorative_winter.items.FrostyWandItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class DWItems {
    public static final Item.Properties modItemProperties = new Item.Properties().tab(Constants.ITEM_GROUP);

    public static Item FESTIVE_CHAIN;
    public static Item WREATH;
    public static Item DRY_GRASS_BLOCK;
    public static Item DRY_GRASS;
    public static Item DRY_TALL_GRASS;
    public static Item DRY_FERN;
    public static Item DRY_LARGE_FERN;
    public static Item THIN_BRANCH;
    public static Item SNOWY_THIN_BRANCH;

    public static Item FROSTY_WAND;

    public static Map<IWoodType, Item> SNOWY_PALISADES = new HashMap<>();
    public static Map<IWoodType, Item> SNOWY_SEATS = new HashMap<>();

    public static void init() {
        FESTIVE_CHAIN = blockItem(DWBlocks.FESTIVE_CHAIN);
        WREATH = blockItem(DWBlocks.WREATH);
        DRY_GRASS_BLOCK = blockItem(DWBlocks.DRY_GRASS_BLOCK);
        DRY_GRASS = blockItem(DWBlocks.DRY_GRASS);
        DRY_TALL_GRASS = blockItem(DWBlocks.DRY_TALL_GRASS);
        DRY_FERN = blockItem(DWBlocks.DRY_FERN);
        DRY_LARGE_FERN = blockItem(DWBlocks.DRY_LARGE_FERN);
        THIN_BRANCH = blockItem(DWBlocks.THIN_BRANCH);
        SNOWY_THIN_BRANCH = blockItem(DWBlocks.SNOWY_THIN_BRANCH);
        FROSTY_WAND = new FrostyWandItem(new Item.Properties().tab(Constants.ITEM_GROUP).stacksTo(1));

        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_PALISADES.put(woodTypes, getSnowyPalisade(woodTypes));
        }
//        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
//            SNOWY_SEATS.put(woodTypes, getSnowySeat(woodTypes));
//        }
    }

    public static Item blockItem(Block block) {
        return new BlockItem(block, modItemProperties);
    }

    public static Item getSnowySeat(IWoodType woodType) {
        return new BlockItem(DWBlocks.SNOWY_SEATS.get(woodType), modItemProperties);
    }

    public static Item getSnowyPalisade(IWoodType woodType) {
        return new BlockItem(DWBlocks.SNOWY_PALISADES.get(woodType), modItemProperties);
    }
}
