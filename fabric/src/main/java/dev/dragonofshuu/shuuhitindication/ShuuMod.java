package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.networking.NeoHitPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ShuuMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("BEFORE NETWORKING");
        PayloadTypeRegistry.clientboundPlay()
            .register(NeoHitPayload.TYPE, NeoHitPayload.STREAM_CODEC);
        Constants.LOG.info("AFTER NETWORK");
        CommonClass.init();
    }
}
