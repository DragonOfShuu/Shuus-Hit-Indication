package dev.dragonofshuu.shuuhitindication.platform.services;

import dev.dragonofshuu.shuuhitindication.networking.NeoHitPayload;
import net.minecraft.server.level.ServerPlayer;

public interface INetworkHelper {
    // <T> void registerS2CPacket(ResourceLocation id, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, FriendlyByteBuf> encoder, Consumer<T> handler);
    // void sendToClient(ServerPlayer player, ResourceLocation id, FriendlyByteBuf buf);
    void sendToClient(ServerPlayer player, NeoHitPayload message);
}
