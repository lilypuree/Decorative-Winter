package com.lilypuree.decorative_winter.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class SnowyBlockModelLoader implements IModelLoader<SnowyBlockModelGeometry> {

    public static SnowyBlockModelLoader INSTANCE = new SnowyBlockModelLoader();

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public SnowyBlockModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new SnowyBlockModelGeometry();
    }
}
