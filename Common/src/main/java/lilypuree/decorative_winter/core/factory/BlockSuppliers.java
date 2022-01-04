package lilypuree.decorative_winter.core.factory;

import lilypuree.decorative_blocks.blocks.ChainBlock;
import lilypuree.decorative_blocks.blocks.LatticeBlock;
import lilypuree.decorative_blocks.datagen.types.IWoodType;
import lilypuree.decorative_blocks.fluid.ThatchFluidBlock;
import lilypuree.decorative_winter.blocks.BranchBlock;
import lilypuree.decorative_winter.blocks.SnowyPalisadeBlock;
import lilypuree.decorative_winter.blocks.SnowySeatBlock;
import lilypuree.decorative_winter.blocks.SnowyFoliageBlock;
import lilypuree.decorative_winter.blocks.SnowyGrassBlock;
import lilypuree.decorative_winter.core.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class BlockSuppliers {
    protected static final Block.Properties woodProperty = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.2F).sound(SoundType.WOOD);
    protected static final Block.Properties palisadeProperty = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 4.0F).sound(SoundType.WOOD);
    protected static final Block.Properties branchProperty = BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.GRASS).noCollission().instabreak().dynamicShape().noOcclusion();
    protected static final Block.Properties chainProperties = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).strength(4.3F).sound(SoundType.METAL).noOcclusion();
    public static final Material snowMaterial = (new Material.Builder(MaterialColor.SNOW)).noCollider().nonSolid().replaceable().liquid().build();

    public static Supplier<Block> FESTIVE_CHAIN = () -> new ChainBlock(chainProperties);
    public static Supplier<Block> WREATH = () -> new LatticeBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH));
    public static Supplier<Block> DRY_GRASS_BLOCK = () -> new SnowyGrassBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.PODZOL).strength(0.5F).sound(SoundType.GRAVEL));
    public static Supplier<Block> DRY_GRASS = () -> new SnowyFoliageBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noOcclusion());
    public static Supplier<Block> DRY_TALL_GRASS = () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
    public static Supplier<Block> DRY_FERN = () -> new SnowyFoliageBlock(BlockBehaviour.Properties.copy(Blocks.FERN));
    public static Supplier<Block> DRY_LARGE_FERN = () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN));
    public static Supplier<Block> BRANCH_GENERAL = ()->new BranchBlock(branchProperty);
    public static Supplier<Block> FLUID_SNOW = () -> new ThatchFluidBlock(Registration.STILL_SNOW, BlockBehaviour.Properties.of(snowMaterial).noCollission().randomTicks().strength(100).noDrops().noOcclusion()){
        @Override
        public boolean propagatesSkylightDown(BlockState $$0, BlockGetter $$1, BlockPos $$2) {
            return true;
        }
    };


    public static Block getSnowyPalisade(IWoodType woodType) {
        return new SnowyPalisadeBlock(palisadeProperty, woodType);
    }

    public static Block getSnowySeat(IWoodType woodType) {
        return new SnowySeatBlock(woodProperty, woodType);
    }
}
