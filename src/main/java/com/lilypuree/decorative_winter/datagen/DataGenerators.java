package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_winter.DecorativeWinter;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new WinterRecipes(generator));
            generator.addProvider(new WinterLootTables(generator));
        }
        if (event.includeClient()) {

            generator.addProvider(new WinterBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new WinterItems(generator, DecorativeWinter.MODID, event.getExistingFileHelper()));
            generator.addProvider(new Languages(generator, "en_us"));
        }
    }

}
