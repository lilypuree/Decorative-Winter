package com.lilypuree.decorative_winter.setup;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModSetup {
    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Method setFireInfo = ObfuscationReflectionHelper.findMethod(FireBlock.class, "func_180686_a", Block.class, int.class, int.class);
            try {
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_GRASS.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_FERN.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_LARGE_FERN.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_TALL_GRASS.get(), 60, 100);
                setFireInfo.invoke(Blocks.FIRE, Registration.DRY_GRASS_BLOCK.get(), 60, 100);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}