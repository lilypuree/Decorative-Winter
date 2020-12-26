package com.lilypuree.decorative_winter.setup;

import com.lilypuree.decorative_winter.client.WinterPackFinder;
import com.lilypuree.decorative_winter.client.WinterTextureStitcher;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.CompletableFuture;

public class ClientConstruction {

    @SuppressWarnings("ConstantConditions")
    public static void run() {
        if (Minecraft.getInstance() == null) return;
        Minecraft.getInstance().getResourcePackList().addPackFinder(new WinterPackFinder(WinterTextureStitcher.RESOURCE_PACK_FOLDER));
        if (Minecraft.getInstance().getResourceManager() instanceof IReloadableResourceManager) {
            ((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).addReloadListener(
                    (stage, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor) -> {
                        CompletableFuture<Void> branchStitcher = CompletableFuture.supplyAsync(WinterTextureStitcher::new)
                                .thenApplyAsync(WinterTextureStitcher::prepare)
                                .thenAcceptAsync(WinterTextureStitcher::generate, backgroundExecutor);
                        return branchStitcher.thenCompose(stage::markCompleteAwaitingOthers);
                    });
        }
    }
}
