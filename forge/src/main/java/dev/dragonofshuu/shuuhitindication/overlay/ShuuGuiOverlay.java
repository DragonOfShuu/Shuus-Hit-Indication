package dev.dragonofshuu.shuuhitindication.overlay;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;

import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;

public class ShuuGuiOverlay {
    public static void register(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "hit_indicator", HitIndicatorGUI::render);
    }
}
