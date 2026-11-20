package dev.dragonofshuu.shuuhitindication.platform.services;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Consumer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public interface INetworkHelper {
    // <T> void registerS2CPacket(ResourceLocation id, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, FriendlyByteBuf> encoder, Consumer<T> handler);
    <T> void registerS2CPacket(ResourceLocation id, Class<T> clazz, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, FriendlyByteBuf> encoder, Consumer<T> handler);
    // void sendToClient(ServerPlayer player, ResourceLocation id, FriendlyByteBuf buf);
    void sendToClient(ServerPlayer player, ResourceLocation id, Object message);
}
