package lilypuree.decorative_winter.core;

import lilypuree.decorative_blocks.blocks.ChainBlock;
import lilypuree.decorative_blocks.blocks.LatticeBlock;
import lilypuree.decorative_blocks.blocks.types.IWoodType;
import lilypuree.decorative_blocks.blocks.types.VanillaWoodTypes;
import lilypuree.decorative_winter.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.HashMap;
import java.util.Map;

public class DWBlocks {
    protected static final Block.Properties woodProperty = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD);
    protected static final Block.Properties palisadeProperty = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 4.0F).sound(SoundType.WOOD);

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
        Block.Properties chainProperties = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).strength(4.3F).sound(SoundType.METAL).noOcclusion();
        Block.Properties branchProperty = BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.GRASS).noCollission().instabreak().dynamicShape().noOcclusion();

        FESTIVE_CHAIN = new ChainBlock(chainProperties);
        WREATH = new LatticeBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH));
        DRY_GRASS_BLOCK = new SnowyGrassBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.PODZOL).strength(0.5F).sound(SoundType.GRAVEL));
        DRY_GRASS = new SnowyFoliageBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noOcclusion());
        DRY_TALL_GRASS = new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
        DRY_FERN = new SnowyFoliageBlock(BlockBehaviour.Properties.copy(Blocks.FERN));
        DRY_LARGE_FERN = new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN));
        THIN_BRANCH = new BranchBlock(branchProperty);
        SNOWY_THIN_BRANCH = new BranchBlock(branchProperty);

        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
            SNOWY_PALISADES.put(woodTypes, getSnowyPalisade(woodTypes));
        }
//        for (IWoodType woodTypes : VanillaWoodTypes.values()) {
//            SNOWY_SEATS.put(woodTypes, getSnowySeat(woodTypes));
//        }
    }
    public static Block getSnowyPalisade(IWoodType woodType) {
        return new SnowyPalisadeBlock(palisadeProperty, woodType);
    }

    public static Block getSnowySeat(IWoodType woodType) {
        return new SnowySeatBlock(woodProperty, woodType);
    }



}
