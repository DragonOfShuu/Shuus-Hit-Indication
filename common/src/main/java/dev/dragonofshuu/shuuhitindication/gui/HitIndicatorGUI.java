package dev.dragonofshuu.shuuhitindication.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;

import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;

public class HitIndicatorGUI {
    private static final ResourceLocation HIT_INDICATOR_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/hit_indicator.png");

    public static void render(Gui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        // Float rotation = HitIndicatorOrchestrator.getHitRotation(player);
        boolean hasHit = HitIndicatorOrchestrator.hasHit();
        if (!hasHit) return;

        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        HitIndicatorOrchestrator.getHitIndicators().forEach((hit) -> {
            guiGraphics.pose().pushPose();
            
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            float alpha = (float) Math.min((hit.getWaitTask().RemainingTicks - partialTick) / ((float) HitIndicatorOrchestrator.HIT_INDICATOR_DURATION - 20), 0.5);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
    
            guiGraphics.pose().translate(centerX, centerY, 0);
            guiGraphics.pose().mulPose(Axis.ZP.rotation(hit.getHitRotation(player)));
            guiGraphics.pose().scale(3, 3, 3);
            guiGraphics.blit(HIT_INDICATOR_TEXTURE, -25, -25, 0, 0, 50, 50, 50, 50);
    
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
    
            guiGraphics.pose().popPose();
        });
    }
}
