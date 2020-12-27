package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_blocks.datagen.Items;
import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_winter.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WinterItems extends Items {

    public WinterItems(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        for (IWoodType wood : VanillaWoodTypes.values()) {
//            getBuilder(wood+"_beam").parent(new ModelFile.UncheckedModelFile(modLoc("block/"+wood+"_beam_y")));
//            getBuilder(wood+"_palisade").parent(new ModelFile.UncheckedModelFile(modLoc("block/"+wood+"_palisade_inventory")));
//            getBuilder(wood+"_seat").parent(new ModelFile.UncheckedModelFile(modLoc("block/"+wood+"_seat_inventory")));
//            getBuilder(wood+"_support").parent(new ModelFile.UncheckedModelFile(modLoc("block/"+wood+"_support")));
            blockItemModel(Registration.getBranchBlock(wood));
        }

        blockItemModel(Registration.DRY_GRASS_BLOCK.get());
        blockItemModel(Registration.FESTIVE_CHAIN.get());
        generated(Registration.DRY_GRASS.get().asItem(), modLoc("block/dry_grass"));
        generated(Registration.DRY_FERN.get().asItem(), modLoc("block/dry_fern"));
        generated(Registration.DRY_TALL_GRASS.get().asItem(), modLoc("block/dry_tall_grass_top"));
        generated(Registration.DRY_LARGE_FERN.get().asItem(), modLoc("block/dry_large_fern_top"));
        generated(Registration.FROSTY_WAND.get(), modLoc("item/frosty_wand"));
    }

    private void generated(Item item, ResourceLocation texture) {
        getBuilder(item.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated"))).texture("layer0", texture);
    }

    private void blockItemModel(Block block) {
        String name = block.getRegistryName().getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc(ModelProvider.BLOCK_FOLDER + "/" + name)));
    }

    private void blockInventoryModel(Block block) {
        String name = block.getRegistryName().getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc(ModelProvider.BLOCK_FOLDER + "/" + name + "_inventory")));
    }


    @Override
    public String getName() {
        return "Decorative Winter Item Models";
    }
}
