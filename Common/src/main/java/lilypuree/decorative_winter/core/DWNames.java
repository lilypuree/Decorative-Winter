package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_winter.DWConstants;
import net.minecraft.resources.ResourceLocation;

public class DWNames {
    public static ResourceLocation FESTIVE_CHAIN = create("festive_chain");
    public static ResourceLocation WREATH = create("wreath");
    public static ResourceLocation DRY_GRASS_BLOCK = create("dry_grass_block");
    public static ResourceLocation DRY_GRASS = create("dry_grass");
    public static ResourceLocation DRY_TALL_GRASS = create("dry_tall_grass");
    public static ResourceLocation DRY_FERN = create("dry_fern");
    public static ResourceLocation DRY_LARGE_FERN = create("dry_large_fern");
    public static ResourceLocation THIN_BRANCH = create("thin_branch");
    public static ResourceLocation SNOWY_THIN_BRANCH = create("snowy_thin_branch");

    public static ResourceLocation FROSTY_WAND = create("frosty_wand");
    public static ResourceLocation FLOWING_SNOW = create("flowing_snow");
    public static ResourceLocation STILL_SNOW = create("fluid_snow");


    protected static ResourceLocation create(String name) {
        return new ResourceLocation(DWConstants.MODID, name);
    }

    public static ResourceLocation snowyPalisade(IWoodType woodType) {
        return create("snowy_" + woodType + "_palisade");
    }
    public static ResourceLocation snowySeat(IWoodType woodType) {
        return create("snowy_" + woodType + "_seat");
    }
}
