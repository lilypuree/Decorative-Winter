package com.lilypuree.decorative_winter.setup;

import com.google.common.collect.ImmutableMap;
import com.lilypuree.decorative_blocks.DecorativeBlocks;
import com.lilypuree.decorative_blocks.blocks.*;
import com.lilypuree.decorative_blocks.blocks.ChainBlock;
import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_blocks.datagen.types.WoodDecorativeBlockTypes;
import com.lilypuree.decorative_blocks.items.BurnableBlockItem;
import com.lilypuree.decorative_blocks.setup.ModSetup;
import com.lilypuree.decorative_winter.blocks.*;
import com.lilypuree.decorative_winter.items.FrostyWandItem;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.lilypuree.decorative_winter.DecorativeWinter.MODID;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITIES.register(modEventBus);
    }


    public static final ImmutableMap<String, RegistryObject<Block>> DECORATIVE_BLOCKS;
    public static final ImmutableMap<String, RegistryObject<Item>> DECORATIVE_ITEMBLOCKS;
    public static final AbstractBlock.Properties chainProperties = Block.Properties.create(Material.IRON, MaterialColor.RED).hardnessAndResistance(4.3F).sound(SoundType.METAL).notSolid();

    public static final Item.Properties dummyProperty = new Item.Properties();
    public static final Item.Properties modItemProperties = (new Item.Properties()).group(ModSetup.ITEM_GROUP);
    public static WoodDecorativeBlockTypes[] supportedTypes = new WoodDecorativeBlockTypes[]{WoodDecorativeBlockTypes.PALISADE, WoodDecorativeBlockTypes.SEAT};

    public static RegistryObject<Block> FESTIVE_CHAIN;
    public static RegistryObject<Block> DRY_GRASS_BLOCK;
    public static RegistryObject<Block> DRY_GRASS;
    public static RegistryObject<Block> DRY_TALL_GRASS;
    public static RegistryObject<Block> DRY_FERN;
    public static RegistryObject<Block> DRY_LARGE_FERN;
    public static RegistryObject<Block> WREATH;

    public static RegistryObject<Item> FROSTY_WAND;

    public static RegistryObject<Block> registerBlockItem(String name, Supplier<Block> blockSupplier) {
        RegistryObject<Block> blockRegistry = BLOCKS.register(name, blockSupplier);
        ITEMS.register(name, () -> new BlockItem(blockRegistry.get(), modItemProperties));
        return blockRegistry;
    }


    static {
        FESTIVE_CHAIN = registerBlockItem("festive_chain",
                () -> new ChainBlock(chainProperties));
        DRY_GRASS_BLOCK = registerBlockItem("dry_grass_block",
                () -> new SnowyGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
        DRY_GRASS = registerBlockItem("dry_grass",
                () -> new SnowyFoliageBlock(AbstractBlock.Properties.from(Blocks.GRASS).notSolid()));
        DRY_TALL_GRASS = registerBlockItem("dry_tall_grass",
                () -> new DoublePlantBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS)));
        DRY_FERN = registerBlockItem("dry_fern",
                () -> new SnowyFoliageBlock(AbstractBlock.Properties.from(Blocks.FERN)));
        DRY_LARGE_FERN = registerBlockItem("dry_large_fern",
                () -> new DoublePlantBlock(AbstractBlock.Properties.from(Blocks.LARGE_FERN)));
        WREATH = registerBlockItem("wreath",
                () -> new LatticeBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
        FROSTY_WAND = ITEMS.register("frosty_wand", () -> new FrostyWandItem(new Item.Properties().group(ModSetup.ITEM_GROUP).maxStackSize(1)));
    }

    static {
        ImmutableMap.Builder<String, RegistryObject<Block>> decorativeBlockBuilder = ImmutableMap.builder();
        ImmutableMap.Builder<String, RegistryObject<Item>> itemBuilder = ImmutableMap.builder();
        for (IWoodType wood : VanillaWoodTypes.values()) {
            for (WoodDecorativeBlockTypes type : supportedTypes) {
                String name = "snowy_" + wood + "_" + type;
                decorativeBlockBuilder.put(name, BLOCKS.register(name, () -> createSnowyDecorativeBlock(wood, type)));
            }
            String branchName = wood + "_thin_branch";
            decorativeBlockBuilder.put(branchName, BLOCKS.register(branchName, () -> new BranchBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.2f).sound(SoundType.PLANT).notSolid(), wood)));
        }


        DECORATIVE_BLOCKS = decorativeBlockBuilder.build();


        for (IWoodType wood : VanillaWoodTypes.values()) {
//            String name = wood + "_" + type;
//
//            itemBuilder.put(name, ITEMS.register(name, () ->
//                    new BurnableBlockItem(DECORATIVE_BLOCKS.get(name).get(), wood.isAvailable() ? modItemProperties : dummyProperty, 300)));

            String branchName = wood + "_thin_branch";
            itemBuilder.put(branchName, ITEMS.register(branchName, () -> new BurnableBlockItem(DECORATIVE_BLOCKS.get(branchName).get(), modItemProperties, 50)));
        }


        DECORATIVE_ITEMBLOCKS = itemBuilder.build();
    }

    public static Block getBranchBlock(IWoodType wood) {
        String name = wood + "_thin_branch";
        return DECORATIVE_BLOCKS.get(name).get();
    }

    public static Block getSnowyWoodDecorativeBlock(IWoodType wood, WoodDecorativeBlockTypes decorativeBlockType) {
        String name = "snowy_" + wood + "_" + decorativeBlockType;
        return DECORATIVE_BLOCKS.get(name).get();
    }


    public static PalisadeBlock getSnowyPalisadeBlock(IWoodType wood) {
        return (PalisadeBlock) getSnowyWoodDecorativeBlock(wood, WoodDecorativeBlockTypes.PALISADE);
    }

    public static SeatBlock getSnowySeatBlock(IWoodType wood) {
        return (SeatBlock) getSnowyWoodDecorativeBlock(wood, WoodDecorativeBlockTypes.SEAT);
    }

//    public static RotatedPillarBlock getBeamBlock(IWoodType wood) {
//        return (RotatedPillarBlock) getWoodDecorativeBlock(wood, WoodDecorativeBlockTypes.BEAM);
//    }
//
//    public static SupportBlock getSupportBlock(IWoodType wood) {
//        return (SupportBlock) getWoodDecorativeBlock(wood, WoodDecorativeBlockTypes.SUPPORT);
//    }


    private static Block createSnowyDecorativeBlock(IWoodType wood, WoodDecorativeBlockTypes woodDecorativeBlockType) {
        Block.Properties woodProperty = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(1.2F).sound(SoundType.WOOD);
        Block.Properties palisadeProperty = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 4.0F).sound(SoundType.WOOD);

        switch (woodDecorativeBlockType) {
            default:
            case BEAM:
                return new BeamBlock(woodProperty, wood);
            case SEAT:
                return new SnowySeatBlock(woodProperty, wood);
            case SUPPORT:
                return new SupportBlock(woodProperty, wood);
            case PALISADE:
                return new SnowyPalisadeBlock(palisadeProperty, wood);
        }
    }
}

