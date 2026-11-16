package dev.dragonofshuu.shuuhitindication.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;

import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;
import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator.HitIndicator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;

public class HitIndicatorGUI {
    // private static final ResourceLocation HIT_INDICATOR_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/hit_indicator.png");
    private static final ResourceLocation HIT_INDICATOR_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/hit-indicator-thin.png");

    public static void render(Gui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if (!ShuuConfig.isHitIndicationEnabled()) return;
        boolean hasHit = HitIndicatorOrchestrator.hasHit();
        if (!hasHit) return;
        
        HitIndicatorOrchestrator.getHitIndicators().forEach((hit) -> {
            renderHitIndicator(gui, guiGraphics, partialTick, screenWidth, screenHeight, hit);
        });
    }
    
    public static void renderHitIndicator(
        Gui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight,
        HitIndicator hit) {
            LocalPlayer player = Minecraft.getInstance().player;
            renderHitIndicator(gui, guiGraphics, partialTick, screenWidth, screenHeight, hit, HitIndicatorSettings.fromConfig(), player);
    }

    public static void renderHitIndicator(
        Gui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight,
        HitIndicator hit, HitIndicatorSettings settings, LocalPlayer player) {
        guiGraphics.pose().pushPose();
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float alpha = calculateAlpha(settings, hit.getWaitTask().RemainingTicks, partialTick);
        RenderSystem.setShaderColor(
            settings.r, 
            settings.g, 
            settings.b, 
            settings.a * alpha);
    
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        guiGraphics.pose().translate(centerX, centerY, 0);
        guiGraphics.pose().mulPose(Axis.ZP.rotation(hit.getHitRotation(player)));
        guiGraphics.pose().scale(settings.scale, settings.scale, settings.scale);
        guiGraphics.blit(HIT_INDICATOR_TEXTURE, -25, -25, 0, 0, 50, 50, 50, 50);
    
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    
        guiGraphics.pose().popPose();
    }

    public static float calculateAlpha(float remainingTicks, float partialTick) {
        return calculateAlpha(HitIndicatorSettings.fromConfig(), remainingTicks, partialTick);
    }

    public static float calculateAlpha(HitIndicatorSettings settings, float remainingTicks, float partialTick) {
        float maximumAlpha = settings.a;
        float timeBeforeFade = Math.min(20, Math.max(settings.duration, 0));
        float alpha = (float) Math.min((remainingTicks - partialTick) / (settings.duration - timeBeforeFade), maximumAlpha);
        return alpha;
    }
}
