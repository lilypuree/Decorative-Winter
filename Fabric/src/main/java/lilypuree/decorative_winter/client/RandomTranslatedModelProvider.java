package lilypuree.decorative_winter.client;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RandomTranslatedModelProvider implements ModelResourceProvider {

    private ResourceLocation modelID;
    private ResourceLocation baseModel;
    private boolean ambientOcclusion;

    public RandomTranslatedModelProvider(ResourceLocation modelID, ResourceLocation baseModelID, boolean ambientOcclusion) {
        this.modelID = modelID;
        this.baseModel = baseModelID;
        this.ambientOcclusion = ambientOcclusion;
    }

    @Override
    public @Nullable UnbakedModel loadModelResource(ResourceLocation resourceId, ModelProviderContext context) throws ModelProviderException {
        if (resourceId.equals(modelID)) {
            return new RandomTranslatedUnbakedModel(baseModel, ambientOcclusion);
        } else
            return null;
    }
}
