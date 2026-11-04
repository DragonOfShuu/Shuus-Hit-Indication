package dev.dragonofshuu.shuuhitindication.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import java.awt.Color;

public class HitIndicatorGUI {
    public static void render(Gui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        guiGraphics.fill(centerX-20, centerY-20, centerX+20, centerY+20, Color.BLUE.getRGB());
    }
}
