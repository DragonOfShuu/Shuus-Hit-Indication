package dev.dragonofshuu.shuuhitindication.overlay;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;

import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

public class ShuuGuiOverlay {
    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, "hit_indicator",
                (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> HitIndicatorGUI.render(guiGraphics,
                        partialTick, screenWidth, screenHeight));
    }
}
