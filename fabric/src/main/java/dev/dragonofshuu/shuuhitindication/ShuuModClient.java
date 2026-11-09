package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.minecraft.client.Minecraft;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ShuuModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();
            HitIndicatorGUI.render(mc.gui, guiGraphics, tickDelta, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
        });
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            DelayedClientTask.onClientTick();
        });
    }
}
