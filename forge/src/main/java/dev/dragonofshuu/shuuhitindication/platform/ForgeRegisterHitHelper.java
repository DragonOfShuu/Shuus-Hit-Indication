package dev.dragonofshuu.shuuhitindication.platform;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.platform.services.INetworkHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ForgeRegisterHitHelper implements INetworkHelper {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Constants.MOD_ID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static int packetId = 0;

    @Override
    public <T> void registerS2CPacket(ResourceLocation id, Class<T> clazz, Function<FriendlyByteBuf, T> decoder,
            BiConsumer<T, FriendlyByteBuf> encoder, Consumer<T> handler) {
        CHANNEL.registerMessage(
            packetId++,
            clazz,
            encoder,
            decoder,
            (msg, ctx) -> {
                var context = ctx.get();
                context.enqueueWork(() -> handler.accept(msg));
                context.setPacketHandled(true);
            }
        );
    }

    @Override
    public void sendToClient(ServerPlayer player, ResourceLocation id, Object message) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
