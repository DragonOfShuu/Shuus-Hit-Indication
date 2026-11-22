package dev.dragonofshuu.shuuhitindication.overlay;

import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import net.minecraft.resources.Identifier;

import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

public class ShuuGuiOverlay {
    public static final Identifier HIT_INDICATOR_OVERLAY = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "hit_indicator_overlay");

    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, HIT_INDICATOR_OVERLAY, HitIndicatorGUI::render);
    }
}
