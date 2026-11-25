package dev.dragonofshuu.shuuhitindication.networking;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;

public class ClientPayloadHandler {
    public static void handleHit(final NeoHitPayload payload) {
        HitIndicatorOrchestrator.registerHit(payload.position());
    }
}
