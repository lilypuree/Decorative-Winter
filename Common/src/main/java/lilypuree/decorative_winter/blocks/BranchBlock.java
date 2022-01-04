package lilypuree.decorative_winter.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BranchBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);

    public BranchBlock(Properties properties) {
        super(properties);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
    @Override
    public void entityInside(BlockState $$0, Level $$1, BlockPos $$2, Entity $$3) {
        if (!($$3 instanceof LivingEntity) || $$3.getFeetBlockState().is(this)) {
            $$3.makeStuckInBlock($$0, new Vec3((double) 0.9F, 0.9D, (double) 0.9F));
        }
    }
    @Override
    public VoxelShape getInteractionShape(BlockState $$0, BlockGetter $$1, BlockPos $$2) {
        return SHAPE;
    }
    //
//    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
//        return this.woodType.isFlammable();
//    }
//
//    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
//        return this.woodType.isFlammable() ? 50 : super.getFlammability(state, world, pos, face);
//    }
//
//    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
//        return this.woodType.isFlammable() ? 20 : super.getFireSpreadSpeed(state, world, pos, face);
//    }
}
