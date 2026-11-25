package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.networking.NeoHitPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ShuuMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playS2C()
            .register(NeoHitPayload.TYPE, NeoHitPayload.STREAM_CODEC);
        CommonClass.init();
    }
}
