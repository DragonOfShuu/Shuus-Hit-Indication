package dev.dragonofshuu.shuuhitindication;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.Reader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.dragonofshuu.shuuhitindication.platform.Services;
import net.minecraft.resources.ResourceLocation;

public class ShuuConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Services.PLATFORM.getConfigDirectory().resolve(Constants.MOD_ID + ".json");

    private static ResourceLocation hitIndicationTextureCache;

    // Static instance to access values anywhere in your mod
    private static ConfigData INSTANCE = new ConfigData();

    public static class ConfigData {
        public boolean enableHitIndication = true;
        public int hitIndicationDuration = 60; 
        public float hitIndicationR = 1.0F;
        public float hitIndicationG = 0.0F;
        public float hitIndicationB = 0.0F;
        public float hitIndicationA = 0.5F;
        public String hitIndicationTexture = "textures/gui/hit-indicator-thin.png";
    }

    public static void setEnableHitIndication(boolean enable) {
        INSTANCE.enableHitIndication = enable;
    }

    public static boolean isHitIndicationEnabled() {
        return INSTANCE.enableHitIndication;
    }

    public static void setHitIndicationDuration(int duration) {
        int newValue = Math.min(120, Math.max(duration, 10));
        INSTANCE.hitIndicationDuration = newValue;
    }

    public static int getHitIndicationDuration() {
        return INSTANCE.hitIndicationDuration;
    }
    
    public static void setHitIndicationColor(float r, float g, float b, float a) {
        INSTANCE.hitIndicationR = validateHitValue(r);
        INSTANCE.hitIndicationG = validateHitValue(g);
        INSTANCE.hitIndicationB = validateHitValue(b);
        INSTANCE.hitIndicationA = validateHitValue(a);
    }

    public static int getHitIndicationColor() {
        int r = (int)(INSTANCE.hitIndicationR * 255.0F);
        int g = (int)(INSTANCE.hitIndicationG * 255.0F);
        int b = (int)(INSTANCE.hitIndicationB * 255.0F);
        int a = (int)(INSTANCE.hitIndicationA * 255.0F);
        return (r << 16) | (g << 8) | b | (a << 24);
    }

    public static float getHitIndicationR() {
        return INSTANCE.hitIndicationR;
    }

    public static float getHitIndicationG() {
        return INSTANCE.hitIndicationG;
    }

    public static float getHitIndicationB() {
        return INSTANCE.hitIndicationB;
    }

    public static float getHitIndicationA() {
        return INSTANCE.hitIndicationA;
    }

    private static float validateHitValue(float v) {
        return Math.min(1.0F, Math.max(v, 0.0F));
    }

    public static void setHitIndicationTexture(String texture) {
        INSTANCE.hitIndicationTexture = texture;
        hitIndicationTextureCache = null;
    }

    public static void setHitIndicationTexture(ResourceLocation texture) {
        INSTANCE.hitIndicationTexture = texture.toString();
        hitIndicationTextureCache = texture;
    }

    public static ResourceLocation getHitIndicationTexture() {
        if (hitIndicationTextureCache == null) {
            hitIndicationTextureCache = ResourceLocation.tryParse(INSTANCE.hitIndicationTexture);
        }
        return hitIndicationTextureCache;
    }

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                ConfigData loaded = GSON.fromJson(reader, ConfigData.class);
                if (loaded != null) {
                    INSTANCE = loaded;
                }
            } catch (IOException e) {
                Constants.LOG.error("Failed to load config :(", e);
            }
        }

        save();
    }

    public static void save() {
        try {
            if (!Files.exists(CONFIG_PATH.getParent())) {
                Files.createDirectories(CONFIG_PATH.getParent());
            }
            try (var writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(INSTANCE, writer);
            }
        } catch (IOException e) {
            Constants.LOG.error("Failed to save config :(", e);
        }
    }
}
