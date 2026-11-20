package dev.dragonofshuu.shuuhitindication.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;

import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;
import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator.HitIndicator;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class HitIndicatorGUI {
    public static void render(GuiGraphics guiGraphics, DeltaTracker partialTick) {
        int screenWidth = guiGraphics.guiWidth();
        int screenHeight = guiGraphics.guiHeight();

        if (!ShuuConfig.isHitIndicationEnabled()) return;
        boolean hasHit = HitIndicatorOrchestrator.hasHit();
        if (!hasHit) return;
        
        HitIndicatorOrchestrator.getHitIndicators().forEach((hit) -> {
            renderHitIndicator(guiGraphics, partialTick.getGameTimeDeltaPartialTick(true), screenWidth, screenHeight, hit);
        });
    }

    public static void renderHitIndicator(
        GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight,
        HitIndicator hit) {
            LocalPlayer player = Minecraft.getInstance().player;
            renderHitIndicator(guiGraphics, partialTick, screenWidth, screenHeight, hit.getWaitTask().RemainingTicks, HitIndicatorSettings.fromConfig(), hit.getHitRotation(player), 0, 0);
    }

    public static void renderHitIndicator(
        GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight,
        int remainingTicks, HitIndicatorSettings settings, float rotation, int x, int y) {
        guiGraphics.pose().pushPose();
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float alpha = calculateAlpha(settings, remainingTicks, partialTick);
        RenderSystem.setShaderColor(
            settings.r, 
            settings.g, 
            settings.b, 
            settings.a * alpha);
    
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        guiGraphics.pose().translate(centerX, centerY, 0);
        guiGraphics.pose().mulPose(Axis.ZP.rotation(rotation));
        guiGraphics.pose().scale(settings.scale, settings.scale, settings.scale);
        var texture = settings.texture;
        var texWidth = texture.getTextureWidth();
        var texHeight = texture.getTextureHeight();
        guiGraphics.blit(texture.getResourceLocation(), -texWidth / 2 + x, -texHeight / 2 + y, 0, 0, texWidth, texHeight, texWidth, texHeight);
    
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    
        guiGraphics.pose().popPose();
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
