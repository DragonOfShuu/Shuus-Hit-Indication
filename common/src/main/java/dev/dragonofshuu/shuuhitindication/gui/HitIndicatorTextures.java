package dev.dragonofshuu.shuuhitindication.gui;

import dev.dragonofshuu.shuuhitindication.Constants;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class HitIndicatorTextures {
    private static HashMap<String, Texture> TEXTURE_MAP = null;
    private static final List<Texture> ALL = new ArrayList<Texture>(); 

    public static final Texture THIN_TRIANGLE = registerTexture("textures/gui/hit-indicator-thin-triangle.png",
            "Thin Triangle");
    public static final Texture TRIANGLE = registerTexture("textures/gui/hit-indicator-triangle.png", "Triangle");
    public static final Texture THIN = registerTexture("textures/gui/hit-indicator-thin.png", "Thin");
    public static final Texture SIMPLE_TRIANGLE_2 = registerTexture("textures/gui/hit-indicator-simple-triangle-2.png",
            "Simple Triangle 2");
    public static final Texture THIN_2 = registerTexture("textures/gui/hit-indicator-thin-2.png", "Thin 2");
    public static final Texture THIN_SHARP_TRIANGLE_2 = registerTexture(
            "textures/gui/hit-indicator-thin-sharp-triangle-2.png", "Thin Sharp Triangle");
    public static final Texture THIN_TRIANGLE_2 = registerTexture("textures/gui/hit-indicator-thin-triangle-2.png",
            "Thin Triangle 2");

    public static HashMap<String, Texture> getTextureMap() {
        if (TEXTURE_MAP != null)
            return TEXTURE_MAP;

        TEXTURE_MAP = new HashMap<>();
        for (Texture texture : ALL) {
            TEXTURE_MAP.put(texture.getName(), texture);
        }

        return TEXTURE_MAP;
    }

    public static Texture getByName(String name) {
        return getTextureMap().get(name);
    }

    public static Texture getNameOrDefault(String name) {
        return getTextureMap().getOrDefault(name, DEFAULT);
    }

    public static final Texture DEFAULT = THIN_TRIANGLE_2;

    public static Texture[] getAll() {
        return ALL.toArray(new Texture[0]);
    }

    /**
     * Intended for mod developers to add custom hit indicator textures.
     * Make sure your mod runs after this mod, then call this method in your mod's
     * initialization code.
     * 
     * @param modid The mod ID to associate with the texture.
     * @param texturePath The path to the texture to add.
     * @param name The name to associate with the texture.
     */
    public static Texture registerTexture(String modid, String texturePath, String name) {
        return registerTexture(new Texture(modid, texturePath, name));
    }

    public static Texture registerTexture(String texturePath, String name) {
        return registerTexture(new Texture(texturePath, name));
    }

    public static Texture registerTexture(Texture texture) {
        ALL.add(texture);
        // Reset cache
        TEXTURE_MAP = null;
        return texture;
    }

    public static class Texture {
        private final String TEXTURE_PATH;
        private final String NAME;
        private final int TEXTURE_WIDTH;
        private final int TEXTURE_HEIGHT;

        private final ResourceLocation RESOURCE_LOCATION;

        private static final int DEFAULT_SIZE = 50;

        public Texture(String texturePath, String name) {
            this(Constants.MOD_ID, texturePath, name, DEFAULT_SIZE, DEFAULT_SIZE);
        }

        public Texture(String modid, String texturePath, String name) {
            this(modid, texturePath, name, DEFAULT_SIZE, DEFAULT_SIZE);
        }

        public Texture(String modid, String texturePath, String name, int textureWidth, int textureHeight) {
            this.TEXTURE_PATH = texturePath;
            this.NAME = name;
            this.TEXTURE_WIDTH = textureWidth;
            this.TEXTURE_HEIGHT = textureHeight;

            this.RESOURCE_LOCATION = new ResourceLocation(modid, TEXTURE_PATH);
        }

        public String getName() {
            return NAME;
        }

        public String getTexturePath() {
            return TEXTURE_PATH;
        }

        public ResourceLocation getResourceLocation() {
            return RESOURCE_LOCATION;
        }

        public int getTextureWidth() {
            return TEXTURE_WIDTH;
        }

        public int getTextureHeight() {
            return TEXTURE_HEIGHT;
        }
    }
}
