package com.lilypuree.decorative_winter.datagen;

import com.lilypuree.decorative_blocks.datagen.types.IWoodType;
import net.minecraftforge.fml.ModList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompatModWoodTypes {
    private static final Set<IWoodType> modWoodTypes;
    private static Set<IWoodType> woodTypes = new HashSet<>();

    static {
        modWoodTypes = findWoodTypes();
    }

    private static Set<IWoodType> findWoodTypes(){
        return woodTypes;
    }

    private static void addModWoodTypes(String modid, IWoodType[] modWood){
        if(ModList.get().isLoaded(modid)){
            woodTypes.addAll(Arrays.stream(modWood).collect(Collectors.toSet()));
        }
    }

    public static Set<IWoodType> allWoodTypes(){
        return modWoodTypes;
    }
}
