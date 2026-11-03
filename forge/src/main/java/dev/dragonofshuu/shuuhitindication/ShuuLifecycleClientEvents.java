package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.overlay.ShuuGuiOverlay;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ShuuLifecycleClientEvents {
    @SubscribeEvent 
    public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
        ShuuGuiOverlay.register(event);
    }
}
