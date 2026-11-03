package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import net.minecraft.client.Minecraft;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ShuuModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();
            HitIndicatorGUI.renderHitIndicator(mc.gui, guiGraphics, tickDelta, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
        });
    }
}
