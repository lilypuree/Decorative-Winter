package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_winter.setup.Registration;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class WinterRecipes extends RecipeProvider {
    public WinterRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private Consumer<IFinishedRecipe> consumer;

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumerIn) {
        consumer = consumerIn;

        for (IWoodType wood : CompatModWoodTypes.allWoodTypes()) {
//            makeWoodenBlockRecipes(wood);

        }
        festiveChainRecipe();
        frostyWandRecipe();
        driedFoliageRecipe(Items.GRASS, Registration.DRY_GRASS.get());
        driedFoliageRecipe(Items.TALL_GRASS, Registration.DRY_TALL_GRASS.get());
        driedFoliageRecipe(Items.FERN, Registration.DRY_FERN.get());
        driedFoliageRecipe(Items.LARGE_FERN, Registration.DRY_LARGE_FERN.get());
        driedFoliageRecipe(Items.GRASS_BLOCK, Registration.DRY_GRASS_BLOCK.get());
        driedFoliageRecipe(Items.OAK_LEAVES, Registration.getBranchBlock(VanillaWoodTypes.OAK));
        driedFoliageRecipe(Items.SPRUCE_LEAVES, Registration.getBranchBlock(VanillaWoodTypes.SPRUCE));
        driedFoliageRecipe(Items.DARK_OAK_LEAVES, Registration.getBranchBlock(VanillaWoodTypes.DARK_OAK));
        driedFoliageRecipe(Items.ACACIA_LEAVES, Registration.getBranchBlock(VanillaWoodTypes.ACACIA));
        driedFoliageRecipe(Items.BIRCH_LEAVES, Registration.getBranchBlock(VanillaWoodTypes.BIRCH));
        driedFoliageRecipe(Items.JUNGLE_LEAVES, Registration.getBranchBlock(VanillaWoodTypes.JUNGLE));
        cookingRecipesForMethod(consumer, "smoking", IRecipeSerializer.SMOKING, 100);
        cookingRecipesForMethod(consumer, "campfire_cooking", IRecipeSerializer.CAMPFIRE_COOKING, 600);
    }

    private void wreathRecipe(){
        ShapedRecipeBuilder.shapedRecipe(Registration.WREATH.get(), 2)
                .patternLine(" x ")
                .patternLine("x x")
                .patternLine(" x ")
                .key('x', Items.SWEET_BERRIES)
                .addCriterion("has_berries", InventoryChangeTrigger.Instance.forItems(Items.SWEET_BERRIES))
                .build(consumer);

    }

    private void festiveChainRecipe() {
        ShapelessRecipeBuilder.shapelessRecipe(Registration.FESTIVE_CHAIN.get(), 1)
                .addIngredient(Tags.Items.DYES_RED)
                .addIngredient(Tags.Items.DYES_GREEN)
                .addIngredient(com.lilypuree.decorative_blocks.setup.Registration.CHAIN.get())
                .addCriterion("has_chain", InventoryChangeTrigger.Instance.forItems(com.lilypuree.decorative_blocks.setup.Registration.CHAIN.get()))
                .build(consumer);
    }

    private void frostyWandRecipe(){
        ShapedRecipeBuilder.shapedRecipe(Registration.FROSTY_WAND.get(), 1)
                .patternLine("  o")
                .patternLine(" # ")
                .patternLine("#  ")
                .key('o', Items.GLOWSTONE_DUST)
                .key('#', Items.PACKED_ICE)
                .addCriterion("has_glowstone", InventoryChangeTrigger.Instance.forItems(Items.GLOWSTONE_DUST))
                .build(consumer);
    }

    private void driedFoliageRecipe(IItemProvider itemIn, IItemProvider dried) {
        String itemName = itemIn.asItem().getRegistryName().getPath();
        String driedName = dried.asItem().getRegistryName().getPath();

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(itemIn), dried, 0.1f, 100).addCriterion("has_" + itemName, hasItem(itemIn)).build(consumer, driedName + "_from_smelting");
    }


    private static void cookingRecipesForMethod(Consumer<IFinishedRecipe> recipeConsumer, String recipeConsumerIn, CookingRecipeSerializer<?> cookingMethod, int serializerIn) {
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.GRASS), Registration.DRY_GRASS.get(), 0.1F, serializerIn, cookingMethod).addCriterion("has_grass", hasItem(Items.GRASS)).build(recipeConsumer, "dried_grass_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.TALL_GRASS), Registration.DRY_TALL_GRASS.get(), 0.1F, serializerIn, cookingMethod).addCriterion("has_grass", hasItem(Items.GRASS)).build(recipeConsumer, "dried_tall_grass_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.FERN), Registration.DRY_FERN.get(), 0.1F, serializerIn, cookingMethod).addCriterion("has_fern", hasItem(Items.FERN)).build(recipeConsumer, "dried_fern_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.LARGE_FERN), Registration.DRY_LARGE_FERN.get(), 0.1F, serializerIn, cookingMethod).addCriterion("has_fern", hasItem(Items.FERN)).build(recipeConsumer, "dried_large_fern_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.GRASS_BLOCK), Registration.DRY_GRASS_BLOCK.get(), 0.1F, serializerIn, cookingMethod).addCriterion("has_grass_block", hasItem(Items.GRASS_BLOCK)).build(recipeConsumer, "dried_grass_block_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.OAK_LEAVES), Registration.getBranchBlock(VanillaWoodTypes.OAK), 0.1F, serializerIn, cookingMethod).addCriterion("has_oak_leaves", hasItem(Items.OAK_LEAVES)).build(recipeConsumer, "oak_branch_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.SPRUCE_LEAVES), Registration.getBranchBlock(VanillaWoodTypes.SPRUCE), 0.1F, serializerIn, cookingMethod).addCriterion("has_spruce_leaves", hasItem(Items.SPRUCE_LEAVES)).build(recipeConsumer, "spruce_branch_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.ACACIA_LEAVES), Registration.getBranchBlock(VanillaWoodTypes.ACACIA), 0.1F, serializerIn, cookingMethod).addCriterion("has_acacia_leaves", hasItem(Items.ACACIA_LEAVES)).build(recipeConsumer, "acacia_branch_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.BIRCH_LEAVES), Registration.getBranchBlock(VanillaWoodTypes.BIRCH), 0.1F, serializerIn, cookingMethod).addCriterion("has_birch_leaves", hasItem(Items.BIRCH_LEAVES)).build(recipeConsumer, "birch_branch_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.JUNGLE_LEAVES), Registration.getBranchBlock(VanillaWoodTypes.JUNGLE), 0.1F, serializerIn, cookingMethod).addCriterion("has_jungle_leaves", hasItem(Items.JUNGLE_LEAVES)).build(recipeConsumer, "jungle_branch_from_" + recipeConsumerIn);
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.DARK_OAK_LEAVES), Registration.getBranchBlock(VanillaWoodTypes.DARK_OAK), 0.1F, serializerIn, cookingMethod).addCriterion("has_dark_oak_leaves", hasItem(Items.DARK_OAK_LEAVES)).build(recipeConsumer, "dark_oak_branch_from_" + recipeConsumerIn);
    }

    private void makeWoodenBlockRecipes(IWoodType wood) {
//        makeBeamRecipeOf(wood);
        makePalisadeRecipeOf(wood);
        makeSeatRecipeOf(wood);
//        makeSupportRecipeOf(wood);
    }

//    private void makeBeamRecipeOf(IWoodType wood) {
//        ShapedRecipeBuilder.shapedRecipe(Registration.getBeamBlock(wood), 2)
//                .patternLine(" x ")
//                .patternLine(" x ")
//                .key('x', wood.getStrippedLog())
//                .addCriterion("has_stripped_log", InventoryChangeTrigger.Instance.forItems(wood.getStrippedLog()))
//                .build(consumer);
//    }

    private void makePalisadeRecipeOf(IWoodType wood) {
        ShapedRecipeBuilder.shapedRecipe(Registration.getSnowyPalisadeBlock(wood), 6)
                .patternLine("xx ")
                .key('x', wood.getLog())
                .addCriterion("has_log", InventoryChangeTrigger.Instance.forItems(wood.getLog()))
                .build(consumer);
    }

    private void makeSeatRecipeOf(IWoodType wood) {
        ShapedRecipeBuilder.shapedRecipe(Registration.getSnowySeatBlock(wood), 2)
                .patternLine("x  ")
                .patternLine("y  ")
                .key('x', wood.getSlab())
                .key('y', wood.getFence())
                .addCriterion("has_plank", InventoryChangeTrigger.Instance.forItems(wood.getPlanks()))
                .build(consumer);
    }

//    private void makeSupportRecipeOf(IWoodType wood) {
//        ShapedRecipeBuilder.shapedRecipe(Registration.getSupportBlock(wood), 3)
//                .patternLine("xx ")
//                .patternLine("x  ")
//                .key('x', wood.getPlanks())
//                .addCriterion("has_plank", InventoryChangeTrigger.Instance.forItems(wood.getPlanks()))
//                .build(consumer);
//    }

}
