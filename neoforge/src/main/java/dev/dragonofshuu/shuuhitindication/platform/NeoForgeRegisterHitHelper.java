package dev.dragonofshuu.shuuhitindication.platform;

import dev.dragonofshuu.shuuhitindication.networking.ClientPayloadHandler;
import dev.dragonofshuu.shuuhitindication.networking.NeoHitPayload;
import dev.dragonofshuu.shuuhitindication.platform.services.INetworkHelper;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NeoForgeRegisterHitHelper implements INetworkHelper {
    @Override
    public void sendToClient(ServerPlayer player, NeoHitPayload message) {
        PacketDistributor.sendToPlayer(player, message);
    }

    public static void registerPayloadHandlers(final PayloadRegistrar registrar) {
        registrar.playToClient(NeoHitPayload.TYPE, NeoHitPayload.STREAM_CODEC, (final NeoHitPayload payload, final IPayloadContext context) -> ClientPayloadHandler.handleHit(payload));
    }
}
