package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_blocks.blocks.types.VanillaWoodTypes;
import lilypuree.decorative_blocks.registration.BlockRegistryObject;
import lilypuree.decorative_blocks.registration.RegistrationProvider;
import lilypuree.decorative_blocks.registration.RegistryObject;
import lilypuree.decorative_winter.DWConstants;
import lilypuree.decorative_winter.items.FrostyWandItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DWItems {
    private static final RegistrationProvider<Item> ITEM_REGISTRY = RegistrationProvider.get(Registries.ITEM, DWConstants.MOD_ID);

    public static RegistryObject<Item> FESTIVE_CHAIN;
    public static RegistryObject<Item> WREATH;
    public static RegistryObject<Item> DRY_GRASS_BLOCK;
    public static RegistryObject<Item> DRY_GRASS;
    public static RegistryObject<Item> DRY_TALL_GRASS;
    public static RegistryObject<Item> DRY_FERN;
    public static RegistryObject<Item> DRY_LARGE_FERN;
    public static RegistryObject<Item> THIN_BRANCH;
    public static RegistryObject<Item> SNOWY_THIN_BRANCH;

    public static RegistryObject<Item> FROSTY_WAND;

    public static Map<IWoodType, RegistryObject<Item>> SNOWY_PALISADES = new HashMap<>();
    public static Map<IWoodType, RegistryObject<Item>> SNOWY_SEATS = new HashMap<>();

    public static void init() {
        FESTIVE_CHAIN = registerBlockItem(DWBlocks.FESTIVE_CHAIN);
        WREATH = registerBlockItem(DWBlocks.WREATH);
//        DRY_GRASS_BLOCK = blockItem(DWBlocks.DRY_GRASS_BLOCK);
//        DRY_GRASS = blockItem(DWBlocks.DRY_GRASS);
//        DRY_TALL_GRASS = blockItem(DWBlocks.DRY_TALL_GRASS);
//        DRY_FERN = blockItem(DWBlocks.DRY_FERN);
//        DRY_LARGE_FERN = blockItem(DWBlocks.DRY_LARGE_FERN);
        THIN_BRANCH = registerBlockItem(DWBlocks.THIN_BRANCH);
        SNOWY_THIN_BRANCH = registerBlockItem(DWBlocks.SNOWY_THIN_BRANCH);
        FROSTY_WAND = registerItem("frosty_wand", () -> new FrostyWandItem(new Item.Properties().stacksTo(1)));

        for (IWoodType woodType : VanillaWoodTypes.values()) {
            SNOWY_PALISADES.put(woodType, registerBlockItem(DWBlocks.SNOWY_PALISADES.get(woodType)));
        }
//        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
//            SNOWY_SEATS.put(woodTypes, getSnowySeat(woodTypes));
//        }
    }

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return ITEM_REGISTRY.register(name, itemSupplier);
    }

    public static RegistryObject<Item> registerBlockItem(String name, Supplier<Item> itemSupplier) {
        return ITEM_REGISTRY.register(name, itemSupplier);
    }

    public static RegistryObject<Item> registerBlockItem(BlockRegistryObject<?> block) {
        return ITEM_REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
