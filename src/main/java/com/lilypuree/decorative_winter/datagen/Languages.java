package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import com.lilypuree.decorative_blocks.datagen.types.VanillaWoodTypes;
import com.lilypuree.decorative_winter.DecorativeWinter;
import com.lilypuree.decorative_winter.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

public class Languages extends LanguageProvider {

    public Languages(DataGenerator gen, String locale) {
        super(gen, DecorativeWinter.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.FESTIVE_CHAIN.get().asItem(), "Festive Chain");
        add(Registration.WREATH.get().asItem(), "Wreath");
        add(Registration.DRY_GRASS.get().asItem(), "Dry Grass");
        add(Registration.DRY_TALL_GRASS.get().asItem(), "Dry Tall Grass");
        add(Registration.DRY_FERN.get().asItem(), "Dry Fern");
        add(Registration.DRY_LARGE_FERN.get().asItem(), "Dry Large Fern");
        add(Registration.DRY_GRASS_BLOCK.get().asItem(), "Dry Grass Block");
        add(Registration.FROSTY_WAND.get(), "Frosty Wand");

        for (IWoodType wood : VanillaWoodTypes.values()) {
//            add(Registration.getBeamBlock(wood).asItem(), cap(wood.toString()) + " Beam");
//            add(Registration.getSupportBlock(wood).asItem(), cap(wood.toString()) + " Support");

//            add(Registration.getSnowyPalisadeBlock(wood).asItem(), "Snowy " + cap(wood.toString()) + " Palisade");
//            add(Registration.getSnowySeatBlock(wood).asItem(), "Snowy " + cap(wood.toString()) + " Seat");
//            add(Registration.getBranchBlock(wood).asItem(), cap(wood.toString()) + " Thin Branch");
        }
    }

    private String cap(String string) {
        return StringUtils.capitalize(string);
    }
}
