package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import dev.dragonofshuu.shuuhitindication.networking.ClientPayloadHandler;
import dev.dragonofshuu.shuuhitindication.networking.NeoHitPayload;
import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ShuuModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(HitIndicatorGUI::render);
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            DelayedClientTask.onClientTick();
        });

        ClientPlayNetworking.registerGlobalReceiver(
            NeoHitPayload.TYPE, 
            (payload, context) -> ClientPayloadHandler.handleHit(payload)
        );
    }
}
