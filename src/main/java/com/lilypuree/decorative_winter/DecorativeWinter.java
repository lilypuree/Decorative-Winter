package com.lilypuree.decorative_winter;

import com.lilypuree.decorative_winter.client.WinterTextureStitcher;
import com.lilypuree.decorative_winter.setup.ClientConstruction;
import com.lilypuree.decorative_winter.setup.ClientSetup;
import com.lilypuree.decorative_winter.setup.ModSetup;
import com.lilypuree.decorative_winter.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DecorativeWinter.MODID)
public class DecorativeWinter {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "decorative_winter";


    public DecorativeWinter() {
        WinterTextureStitcher.setupFolders();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientConstruction::run);

        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::setup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init));
    }
}
