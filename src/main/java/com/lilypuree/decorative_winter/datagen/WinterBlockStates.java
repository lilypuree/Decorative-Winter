package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_blocks.DecorativeBlocks;
import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_blocks.datagen.types.WoodDecorativeBlockTypes;
import com.lilypuree.decorative_winter.blocks.ModBlockProperties;
import com.lilypuree.decorative_winter.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.lilypuree.decorative_winter.DecorativeWinter.MODID;

public class WinterBlockStates extends BlockStateProvider {

    public WinterBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MODID, exFileHelper);
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public void snowyPalisadeBlock(IWoodType wood) {
        palisadeInventory(wood);
        MultiPartBlockStateBuilder builder = getMultipartBuilder(Registration.getSnowyPalisadeBlock(wood));
        ModelFile postModel = snowyPalisadePostPart(wood);
        ModelFile sideModel = snowyPalisadeSidePart(wood);

        builder.part().modelFile(postModel).addModel().end()
                .part().modelFile(sideModel).uvLock(true).addModel().condition(BlockStateProperties.NORTH, Boolean.TRUE).end()
                .part().modelFile(sideModel).uvLock(true).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, Boolean.TRUE).end()
                .part().modelFile(sideModel).uvLock(true).rotationY(90).addModel().condition(BlockStateProperties.EAST, Boolean.TRUE).end()
                .part().modelFile(sideModel).uvLock(true).rotationY(270).addModel().condition(BlockStateProperties.WEST, Boolean.TRUE).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/custom/custom_snow"))).addModel().condition(BlockStateProperties.SNOWY, true).end();
    }

    public ModelFile snowyPalisadePostPart(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.PALISADE, "post");
        ResourceLocation side = modLoc("block/snowy_" + wood.toString() + "_palisade_side");
        ResourceLocation end = modLoc("block/snow_palisade_end");
        return builder.texture("particle", side).texture("side", side).texture("end", end);
    }

    public ModelFile snowyPalisadeSidePart(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.PALISADE, "side");
        ResourceLocation side = modLoc("block/snowy_" + wood.toString() + "_palisade_side");
        ResourceLocation end = modLoc("block/snow_palisade_end");
        return builder.texture("particle", side).texture("side", side).texture("end", end);
    }

    public ModelFile palisadeInventory(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.PALISADE, "inventory");
        ResourceLocation side = modLoc("block/snowy_" + wood.toString() + "_palisade_side");
        ResourceLocation end = modLoc("block/snow_palisade_end");
        return builder.texture("particle", side).texture("side", side).texture("end", end);
    }


//    public void beamBlock(IWoodType wood) {
//        VariantBlockStateBuilder builder = getVariantBuilder()
//        ModelFile beamXModel = beamModel(wood, Direction.Axis.X);
//        ModelFile beamYModel = beamModel(wood, Direction.Axis.Y);
//        ModelFile beamZModel = beamModel(wood, Direction.Axis.Z);
//
//        builder.partialState().with(BlockStateProperties.AXIS, Direction.Axis.X)
//                .modelForState().modelFile(beamXModel).addModel();
//        builder.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y)
//                .modelForState().modelFile(beamYModel).addModel();
//        builder.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z)
//                .modelForState().modelFile(beamZModel).addModel();
//    }

    public ModelFile beamModel(IWoodType wood, Direction.Axis axis) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.BEAM, axis.getName2());
        return withSideEndTextures(builder, wood + "_beam");
    }

    public void snowySeatBlock(IWoodType wood) {
        seatInventory(wood);
        MultiPartBlockStateBuilder builder = getMultipartBuilder(Registration.getSnowySeatBlock(wood));
        ModelFile seatTopModel = snowySeatTopModel(wood);
        ModelFile seatPostModel = seatPostModel(wood);

        builder.part().modelFile(seatPostModel).addModel().condition(BlockStateProperties.ATTACHED, Boolean.TRUE).end()
                .part().modelFile(seatTopModel).addModel().condition(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).end()
                .part().modelFile(seatTopModel).rotationY(180).addModel().condition(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).end()
                .part().modelFile(seatTopModel).rotationY(90).addModel().condition(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).end()
                .part().modelFile(seatTopModel).rotationY(270).addModel().condition(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/custom/custom_snow"))).addModel().condition(BlockStateProperties.SNOWY, true).end();
    }

    public ModelFile snowySeatTopModel(IWoodType wood) {
        ModelBuilder<?> builder = models().getBuilder("snowy_" + wood + "_seat_top").parent(new ModelFile.UncheckedModelFile(modLoc("block/custom/snowy_seat_top")));
        ResourceLocation snowy = modLoc("block/snowy_" + wood.toString() + "_seat");
        ResourceLocation normal = new ResourceLocation(DecorativeBlocks.MODID, "block/" + wood + "_seat");
        return builder.texture("particle", snowy).texture("snowy", snowy).texture("normal", normal);
    }

    public ModelFile seatPostModel(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.SEAT, "post");
        ResourceLocation texture = modLoc("block/snowy_" + wood.toString() + "_seat");
        return builder.texture("particle", texture).texture("texture", texture);
    }

    public ModelFile seatInventory(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.SEAT, "inventory");
        ResourceLocation texture = modLoc("block/snowy_" + wood.toString() + "_seat");
        return builder.texture("particle", texture).texture("texture", texture);
    }

    public void supportBlock(IWoodType wood) {
        ModelFile supportUpModel = supportBlockModel(wood);
        ModelFile supportDownModel = supportBlockDownModel(wood);

        horizontalBlock(com.lilypuree.decorative_blocks.setup.Registration.getSupportBlock(wood), state ->
                state.get(BlockStateProperties.UP) ? supportUpModel : supportDownModel
        );
    }

    public ModelFile supportBlockModel(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.SUPPORT);
        return withParticleTexture(builder, wood + "_support");
    }

    public ModelFile supportBlockDownModel(IWoodType wood) {
        ModelBuilder<?> builder = createModel(wood, WoodDecorativeBlockTypes.SUPPORT, "down");
        return withParticleTexture(builder, wood + "_support");
    }

    private ModelBuilder<?> createModel(IWoodType wood, WoodDecorativeBlockTypes type) {
        return createModel(wood, type, null);
    }

    private ModelBuilder<?> createModel(IWoodType wood, WoodDecorativeBlockTypes type, String suffix) {
        String name = type + ((suffix == null) ? "" : "_" + suffix);
        return models().getBuilder("snowy_" + wood + "_" + name).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(DecorativeBlocks.MODID, "custom/" + name)));
    }

    private ModelBuilder<?> withParticleTexture(ModelBuilder<?> model, String name) {
        ResourceLocation texture = modLoc("block/" + name);
        return model.texture("particle", texture).texture("texture", texture);
    }

    private ModelBuilder<?> withSideEndTextures(ModelBuilder<?> model, String name) {
        ResourceLocation side = modLoc("block/" + name + "_side");
        ResourceLocation end = modLoc("block/" + name + "_end");
        return model.texture("particle", side).texture("side", side).texture("end", end);
    }

    private ModelFile tintedCross(String name, ResourceLocation texture) {
        return models().withExistingParent(name, mcLoc("block/tinted_cross")).texture("cross", texture);
    }

    public void snowyFoliageBlock(Block block) {
        String name = block.getRegistryName().getPath();
        ResourceLocation blockTexture = blockTexture(block);
        ResourceLocation snowyBlockTexture = modLoc("block/snowy_" + name);
        ResourceLocation blockModelWithSnow = modLoc("block/" + name + "_with_snow");
        ResourceLocation snowyBlockModelWithSnow = modLoc("block/snowy_" + name + "_with_snow");
        getMultipartBuilder(block)
                .part().modelFile(new ModelFile.UncheckedModelFile(blockTexture)).addModel().condition(BlockStateProperties.SNOWY, false).condition(ModBlockProperties.LAYERS_0_8, 0).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(snowyBlockTexture)).addModel().condition(BlockStateProperties.SNOWY, true).condition(ModBlockProperties.LAYERS_0_8, 0).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(blockModelWithSnow)).addModel().condition(BlockStateProperties.SNOWY, false).condition(ModBlockProperties.LAYERS_0_8, 1, 2, 3, 4, 5, 6, 7, 8).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(snowyBlockModelWithSnow)).addModel().condition(BlockStateProperties.SNOWY, true).condition(ModBlockProperties.LAYERS_0_8, 1, 2, 3, 4, 5, 6, 7, 8).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height2"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 1).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height4"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 2).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height6"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 3).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height8"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 4).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height10"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 5).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height12"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 6).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_height14"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 7).end()
                .part().modelFile(new ModelFile.UncheckedModelFile(mcLoc("block/snow_block"))).addModel().condition(ModBlockProperties.LAYERS_0_8, 8).end();
    }

    public void doublePlantBlock(Block block) {
        String name = block.getRegistryName().getPath();
        getVariantBuilder(block).partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().cross(name + "_bottom", modLoc("block/" + name + "_bottom"))).addModel()
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().cross(name + "_top", modLoc("block/" + name + "_top"))).addModel();
    }

    public void branchBlock(IWoodType woodType) {
        Block branchBlock = Registration.getBranchBlock(woodType);
        String name = branchBlock.getRegistryName().getPath();
        ModelFile modelFile = models().withExistingParent(name, modLoc("block/custom/thin_branch"))
                .texture("texture", blockTexture(branchBlock));
        getVariantBuilder(branchBlock).partialState().modelForState()
                .modelFile(modelFile).nextModel().modelFile(modelFile).rotationY(180).nextModel()
                .modelFile(modelFile).rotationX(180).addModel();
    }

    @Override
    protected void registerStatesAndModels() {
        for (IWoodType wood : VanillaWoodTypes.values()) {
            snowyPalisadeBlock(wood);
            snowySeatBlock(wood);
            branchBlock(wood);
        }

        snowyFoliageBlock(Registration.DRY_GRASS.get());
        snowyFoliageBlock(Registration.DRY_FERN.get());
        doublePlantBlock(Registration.DRY_TALL_GRASS.get());
        doublePlantBlock(Registration.DRY_LARGE_FERN.get());
    }
}
