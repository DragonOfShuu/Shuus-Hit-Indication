package dev.dragonofshuu.shuuhitindication.networking;
import dev.dragonofshuu.shuuhitindication.platform.Services;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class ShuuNetworking {
    public static void register() {
        RegisterHitPacketListener hitPacketListener = new RegisterHitPacketListener();
        Services.NETWORK.registerS2CPacket(RegisterHitPacket.REGISTER_HIT, RegisterHitPacket.class, RegisterHitPacket::decode, RegisterHitPacket::encode, hitPacketListener::handle);
    }

    public static void sendRegisterHit(ServerPlayer player, float damageAmount, Vec3 position) {
        // var buf = new FriendlyByteBuf(Unpooled.buffer());
        // FriendlyByteBuf buf = net.minecraft.network.FriendlyByteBuf.class.cast(new net.minecraft.network.FriendlyByteBuf(io.netty.buffer.Unpooled.buffer()));
        // (new RegisterHitPacket(damageAmount, position)).write(buf);
        Services.NETWORK.sendToClient(player, RegisterHitPacket.REGISTER_HIT, new RegisterHitPacket(damageAmount, position));
    }
}
