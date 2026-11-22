package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.overlay.ShuuGuiOverlay;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

// @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ShuuClientModEvents {
    @SubscribeEvent 
    public static void onRegisterGuiOverlays(RegisterGuiLayersEvent event) {
        ShuuGuiOverlay.register(event);
    }
}
