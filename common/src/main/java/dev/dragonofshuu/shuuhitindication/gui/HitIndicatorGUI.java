package dev.dragonofshuu.shuuhitindication.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;

import org.joml.Matrix4f;

import com.mojang.math.Axis;

import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;

public class HitIndicatorGUI {
    private static final ResourceLocation HIT_INDICATOR_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/hit_indicator.png");

    public static void render(Gui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        // if (!HitIndicatorOrchestrator.hasHit()) return;
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        Float rotation = HitIndicatorOrchestrator.getHitRotation(player);
        if (rotation == null) return;

        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        // guiGraphics.fill(centerX-20, centerY-20, centerX+20, centerY+20, Color.BLUE.getRGB());
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(centerX, centerY, 0);
        guiGraphics.pose().mulPose(Axis.ZP.rotation(rotation));
        // guiGraphics.pose().translate(-centerX, -centerY, 0);
        // guiGraphics.blit(HIT_INDICATOR_TEXTURE, centerX-25, centerY-25, 0, 0, 50, 50, 50, 50);
        guiGraphics.blit(HIT_INDICATOR_TEXTURE, -25, -25, 0, 0, 50, 50, 50, 50);
        guiGraphics.pose().popPose();
    }
}
