package lilypuree.decorative_winter.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class RandomTranslatedModelLoader implements IModelLoader<RandomTranslatedModelGeometry> {

    public static RandomTranslatedModelLoader INSTANCE = new RandomTranslatedModelLoader();

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

    }

    @Override
    public RandomTranslatedModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        ResourceLocation modelLocation = new ResourceLocation(modelContents.get("model").getAsString());
        boolean ambientOcclusion = modelContents.get("ambientocclusion").getAsBoolean();
        return new RandomTranslatedModelGeometry(modelLocation, ambientOcclusion);
    }
}
