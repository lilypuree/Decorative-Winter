package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.datagen.types.IWoodType;
import lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import lilypuree.decorative_winter.core.factory.BlockSuppliers;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class DWBlocks {

    public static Block FESTIVE_CHAIN;
    public static Block WREATH;
    public static Block DRY_GRASS_BLOCK;
    public static Block DRY_GRASS;
    public static Block DRY_TALL_GRASS;
    public static Block DRY_FERN;
    public static Block DRY_LARGE_FERN;
    public static Block THIN_BRANCH;
    public static Block SNOWY_THIN_BRANCH;
    public static Map<IWoodType, Block> SNOWY_PALISADES = new HashMap<>();
    public static Map<IWoodType, Block> SNOWY_SEATS = new HashMap<>();

    public static void init() {
        FESTIVE_CHAIN = BlockSuppliers.FESTIVE_CHAIN.get();
        WREATH = BlockSuppliers.WREATH.get();
        DRY_GRASS_BLOCK = BlockSuppliers.DRY_GRASS_BLOCK.get();
        DRY_GRASS = BlockSuppliers.DRY_GRASS.get();
        DRY_TALL_GRASS = BlockSuppliers.DRY_TALL_GRASS.get();
        DRY_FERN = BlockSuppliers.DRY_FERN.get();
        DRY_LARGE_FERN = BlockSuppliers.DRY_LARGE_FERN.get();
        THIN_BRANCH = BlockSuppliers.BRANCH_GENERAL.get();
        SNOWY_THIN_BRANCH = BlockSuppliers.BRANCH_GENERAL.get();

        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_PALISADES.put(woodTypes, BlockSuppliers.getSnowyPalisade(woodTypes));
        }
        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_SEATS.put(woodTypes, BlockSuppliers.getSnowySeat(woodTypes));
        }
    }

}
