package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.datagen.types.IWoodType;
import lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import lilypuree.decorative_winter.core.factory.ItemSuppliers;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class DWItems {

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
        FESTIVE_CHAIN = ItemSuppliers.blockItem(DWBlocks.FESTIVE_CHAIN);
        WREATH = ItemSuppliers.blockItem(DWBlocks.WREATH);
        DRY_GRASS_BLOCK = ItemSuppliers.blockItem(DWBlocks.DRY_GRASS_BLOCK);
        DRY_GRASS = ItemSuppliers.blockItem(DWBlocks.DRY_GRASS);
        DRY_TALL_GRASS = ItemSuppliers.blockItem(DWBlocks.DRY_TALL_GRASS);
        DRY_FERN = ItemSuppliers.blockItem(DWBlocks.DRY_FERN);
        DRY_LARGE_FERN = ItemSuppliers.blockItem(DWBlocks.DRY_LARGE_FERN);
        THIN_BRANCH = ItemSuppliers.blockItem(DWBlocks.THIN_BRANCH);
        SNOWY_THIN_BRANCH = ItemSuppliers.blockItem(DWBlocks.SNOWY_THIN_BRANCH);
        FROSTY_WAND = ItemSuppliers.FROSTY_WAND.get();

        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_PALISADES.put(woodTypes, ItemSuppliers.getSnowyPalisade(woodTypes));
        }
        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_SEATS.put(woodTypes, ItemSuppliers.getSnowySeat(woodTypes));
        }
    }
}
