//package lilypuree.decorative_winter.client.model;
//
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.client.renderer.block.model.ItemOverrides;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.*;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.client.model.IModelConfiguration;
//import net.minecraftforge.client.model.geometry.IModelGeometry;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.function.Function;
//
//public class RandomTranslatedModelGeometry implements IModelGeometry<RandomTranslatedModelGeometry> {
//
//    private boolean ambientOcclusion;
//    private ResourceLocation modelLoc;
//
//    public RandomTranslatedModelGeometry(ResourceLocation resourceLocation, boolean ambientOcclusion) {
//        modelLoc = resourceLocation;
//        this.ambientOcclusion = ambientOcclusion;
//    }
//
//    @Override
//    public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
//        UnbakedModel unbakedModel = bakery.getModel(modelLoc);
//        BakedModel bakedModel = unbakedModel.bake(bakery, spriteGetter, modelTransform, modelLocation);
//        return new RandomTranslatedModel(bakedModel, ambientOcclusion);
//    }
//
//
//    @Override
//    public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
//        UnbakedModel unbakedModel = modelGetter.apply(modelLoc);
//        return unbakedModel.getMaterials(modelGetter, missingTextureErrors);
//    }
//}
