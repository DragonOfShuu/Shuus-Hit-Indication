package dev.dragonofshuu.shuuhitindication.platform;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import dev.dragonofshuu.shuuhitindication.networking.RegisterHitPacket;
import dev.dragonofshuu.shuuhitindication.platform.services.INetworkHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class FabricRegisterHitHelper implements INetworkHelper {

    @Override
    public <T> void registerS2CPacket(ResourceLocation id, Class<T> clazz, Function<FriendlyByteBuf, T> decoder,
            BiConsumer<T, FriendlyByteBuf> encoder, Consumer<T> handler) {
        ClientPlayNetworking.registerGlobalReceiver(id, (client, packetListener, buf, responseSender) -> {
            T decoded = decoder.apply(buf);

            client.execute(() -> handler.accept(decoded));
        });
    }

    @Override
    public void sendToClient(ServerPlayer player, ResourceLocation id, Object message) {
        FriendlyByteBuf buf = new FriendlyByteBuf(io.netty.buffer.Unpooled.buffer());
        if (message instanceof RegisterHitPacket packet) {
            packet.write(buf);
        }
        ServerPlayNetworking.send(player, id, buf);
    }
    
}
