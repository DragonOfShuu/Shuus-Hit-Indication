package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import dev.dragonofshuu.shuuhitindication.gui.ShuuOverlays;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

// @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ShuuClientModEvents {
    @SubscribeEvent 
    public static void onRegisterGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerBelow(VanillaGuiLayers.HOTBAR, ShuuOverlays.HIT_INDICATOR_OVERLAY, HitIndicatorGUI::render);
    }
}
