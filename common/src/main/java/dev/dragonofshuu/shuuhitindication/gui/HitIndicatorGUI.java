package dev.dragonofshuu.shuuhitindication.gui;

import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;
import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator.HitIndicator;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.ARGB;

public class HitIndicatorGUI {
    public static void render(GuiGraphicsExtractor GuiGraphicsExtractor, DeltaTracker partialTick) {
        int screenWidth = GuiGraphicsExtractor.guiWidth();
        int screenHeight = GuiGraphicsExtractor.guiHeight();

        if (!ShuuConfig.isHitIndicationEnabled()) return;
        boolean hasHit = HitIndicatorOrchestrator.hasHit();
        if (!hasHit) return;
        
        HitIndicatorOrchestrator.getHitIndicators().forEach((hit) -> {
            renderHitIndicator(GuiGraphicsExtractor, partialTick.getGameTimeDeltaPartialTick(true), screenWidth, screenHeight, hit);
        });
    }

    public static void renderHitIndicator(
        GuiGraphicsExtractor GuiGraphicsExtractor, float partialTick, int screenWidth, int screenHeight,
        HitIndicator hit) {
            LocalPlayer player = Minecraft.getInstance().player;
            renderHitIndicator(GuiGraphicsExtractor, partialTick, screenWidth, screenHeight, hit.getWaitTask().RemainingTicks, HitIndicatorSettings.fromConfig(), hit.getHitRotation(player), 0, 0);
    }

    public static void renderHitIndicator(
        GuiGraphicsExtractor GuiGraphicsExtractor, float partialTick, int screenWidth, int screenHeight,
        int remainingTicks, HitIndicatorSettings settings, float rotation, int x, int y) {
        var stack = GuiGraphicsExtractor.pose().pushMatrix();
        
        float alpha = calculateAlpha(settings, remainingTicks, partialTick);
        int color = ARGB.colorFromFloat(
            settings.a * alpha,
            settings.r, 
            settings.g, 
            settings.b);
    
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        stack.translate(centerX, centerY);
        stack.rotate(rotation);
        stack.scale(settings.scale, settings.scale);
        var texture = settings.texture;
        var texWidth = texture.getTextureWidth();
        var texHeight = texture.getTextureHeight();
        GuiGraphicsExtractor.blit(RenderPipelines.GUI_TEXTURED, texture.getIdentifier(), -texWidth / 2 + x, -texHeight / 2 + y, 0, 0, texWidth, texHeight, texWidth, texHeight, color);
    
        stack.popMatrix();
    }

    public static float calculateAlpha(float remainingTicks, float partialTick) {
        return calculateAlpha(HitIndicatorSettings.fromConfig(), remainingTicks, partialTick);
    }

    public static float calculateAlpha(HitIndicatorSettings settings, float remainingTicks, float partialTick) {
        float maximumAlpha = settings.a;
        float fadeDuration = Math.min(30, Math.max(settings.duration, 0));
        float alpha = (float) Math.min((remainingTicks - partialTick) / fadeDuration, maximumAlpha);
        return alpha;
    }
}
