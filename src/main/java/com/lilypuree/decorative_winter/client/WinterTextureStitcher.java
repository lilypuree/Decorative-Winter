/**
 * Code based on https://github.com/JTK222/DRP-MARG
 */

package com.lilypuree.decorative_winter.client;

import com.lilypuree.decorative_winter.DecorativeWinter;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinterTextureStitcher {

    public static File RESOURCE_PACK_FOLDER;

    private TexturePair[] requiredTextures;
    private BufferedImage mask;
    private static List<ResourceLocation> rawTextures = new ArrayList<>();

    static {
        rawTextures.add(new ResourceLocation("minecraft:textures/block/oak_log.png"));
        rawTextures.add(new ResourceLocation("minecraft:textures/block/birch_log.png"));
        rawTextures.add(new ResourceLocation("minecraft:textures/block/acacia_log.png"));
        rawTextures.add(new ResourceLocation("minecraft:textures/block/spruce_log.png"));
        rawTextures.add(new ResourceLocation("minecraft:textures/block/dark_oak_log.png"));
        rawTextures.add(new ResourceLocation("minecraft:textures/block/jungle_log.png"));
    }

    public static void setupFolders() {
        File modData = new File("./mod_data/" + DecorativeWinter.MODID + "/");
        modData.mkdirs();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            RESOURCE_PACK_FOLDER = new File(modData, "resource_pack");

            new File(RESOURCE_PACK_FOLDER, "assets").mkdirs();

            rawTextures.forEach(fullpath -> {
                String fileName = fullpath.toString().replaceFirst("minecraft", "decorative_winter")
                        .replaceFirst(":", "/").replaceAll("_log.png", "_thin_branch.png");
                File outputFile = new File(RESOURCE_PACK_FOLDER + "/assets/" + fileName);
                outputFile.getParentFile().mkdirs();
            });
            try {
                if (!new File(RESOURCE_PACK_FOLDER, "pack.mcmeta").exists())
                    Files.copy(Thread.currentThread().getContextClassLoader().getResourceAsStream("/assets/mcmeta_template"), new File(RESOURCE_PACK_FOLDER, "pack.mcmeta").toPath());
//                if (!new File(RESOURCE_PACK_FOLDER, "pack.png").exists())
//                    Files.copy(Thread.currentThread().getContextClassLoader().getResourceAsStream("/assets/pack.png"), new File(RESOURCE_PACK_FOLDER, "pack.png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WinterTextureStitcher prepare() {

        try {
            IResource resource = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(DecorativeWinter.MODID, "textures/block/thin_branch_mask.png"));
            if (resource == null) return null;
            InputStream input = new BufferedInputStream(resource.getInputStream());
            mask = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.requiredTextures = rawTextures.stream().map(loc -> {
            try {
                IResource resource = Minecraft.getInstance().getResourceManager().getResource(loc);
                if (resource == null) return null;
                InputStream input = new BufferedInputStream(resource.getInputStream());
                return new TexturePair(loc, ImageIO.read(input));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).toArray(TexturePair[]::new);
        return this;
    }

    public void generate() {
        Arrays.stream(requiredTextures).parallel().forEach(texturePair -> {
            try {
                texturePair.setImage(mask(upscale(texturePair.getImage()), transformGrayToTransparency(mask)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Arrays.stream(requiredTextures).parallel().forEach(texturePair -> {
            try {
                String fileName = texturePair.loc.toString().replaceFirst("minecraft", "decorative_winter")
                        .replaceFirst(":", "/").replaceAll("_log.png", "_thin_branch.png");
                File outputFile = new File(RESOURCE_PACK_FOLDER + "/assets/" + fileName);
                outputFile.getParentFile().mkdirs();
                ImageIO.write(texturePair.getImage(), "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static BufferedImage upscale(BufferedImage sourceImage) {
        int x = sourceImage.getWidth();
        int y = sourceImage.getHeight();
        BufferedImage newImage = new BufferedImage(x * 2, y * 2, sourceImage.getType());
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(sourceImage, 0, 0, null);
        graphics.drawImage(sourceImage, x, 0, null);
        graphics.drawImage(sourceImage, 0, y, null);
        graphics.drawImage(sourceImage, x, y, null);
        graphics.dispose();
        return newImage;
    }

    private Image transformGrayToTransparency(BufferedImage image)
    {
        ImageFilter filter = new RGBImageFilter()
        {
            public final int filterRGB(int x, int y, int rgb)
            {
                return (rgb << 8) & 0xFF000000;
            }
        };

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public static BufferedImage mask(BufferedImage sourceImage, Image mask) {
        BufferedImage dest = new BufferedImage(
                sourceImage.getWidth(), sourceImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = dest.createGraphics();
        graphics.drawImage(sourceImage, 0, 0, null);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0f);
        graphics.setComposite(ac);
        graphics.drawImage(mask, 0, 0, null);
        graphics.dispose();
        return dest;
    }

    public static class TexturePair {
        private ResourceLocation loc;
        private BufferedImage image;

        public TexturePair(ResourceLocation loc, BufferedImage image) {
            this.loc = loc;
            this.image = image;
        }

        public ResourceLocation getLoc() {
            return loc;
        }

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }
}
