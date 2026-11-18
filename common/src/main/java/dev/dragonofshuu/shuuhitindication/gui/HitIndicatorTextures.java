package dev.dragonofshuu.shuuhitindication.gui;

import dev.dragonofshuu.shuuhitindication.Constants;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class HitIndicatorTextures {
    public static final Texture THIN_TRIANGLE = new Texture("textures/gui/hit-indicator-thin-triangle.png", "Thin Triangle");
    public static final Texture TRIANGLE = new Texture("textures/gui/hit-indicator-triangle.png", "Triangle");
    public static final Texture THIN = new Texture("textures/gui/hit-indicator-thin.png", "Thin");
    public static final Texture SIMPLE_TRIANGLE_2 = new Texture("textures/gui/hit-indicator-simple-triangle-2.png", "Simple Triangle 2");
    public static final Texture THIN_2 = new Texture("textures/gui/hit-indicator-thin-2.png", "Thin 2");
    public static final Texture THIN_SHARP_TRIANGLE_2 = new Texture("textures/gui/hit-indicator-thin-sharp-triangle-2.png", "Thin Sharp Triangle");
    public static final Texture THIN_TRIANGLE_2 = new Texture("textures/gui/hit-indicator-thin-triangle-2.png", "Thin Triangle 2");

    private static HashMap<String, Texture> TEXTURE_MAP = null;

    public static HashMap<String, Texture> getTextureMap() {
        if (TEXTURE_MAP != null) return TEXTURE_MAP;

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

    public static final Texture DEFAULT = THIN;

    private static final List<Texture> ALL = new ArrayList<Texture>() {{
        add(THIN_TRIANGLE);
        add(TRIANGLE);
        add(THIN);
        add(SIMPLE_TRIANGLE_2);
        add(THIN_2);
        add(THIN_SHARP_TRIANGLE_2);
        add(THIN_TRIANGLE_2);
    }};

    public static Texture[] getAll() {
        return ALL.toArray(new Texture[0]);
    }

    /**
     * Intended for mod developers to add custom hit indicator textures.
     * Make sure your mod runs after this mod, then call this method in your mod's initialization code.
     * @param texture The texture to add.
     */
    public static void addTexture(Texture texture) {
        ALL.add(texture);
        // Reset cache
        TEXTURE_MAP = null;
    }

    public static class Texture {
        private final String TEXTURE_PATH;
        private final String NAME;

        private final ResourceLocation RESOURCE_LOCATION;

        public Texture(String TEXTURE_PATH, String NAME) {
            this.TEXTURE_PATH = TEXTURE_PATH;
            this.NAME = NAME;

            this.RESOURCE_LOCATION = new ResourceLocation(Constants.MOD_ID, TEXTURE_PATH);
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
    }
}
