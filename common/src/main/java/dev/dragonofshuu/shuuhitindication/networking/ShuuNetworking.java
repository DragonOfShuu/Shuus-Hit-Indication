package dev.dragonofshuu.shuuhitindication.networking;
import dev.dragonofshuu.shuuhitindication.platform.Services;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class ShuuNetworking {
    public static void sendRegisterHit(ServerPlayer player, float damageAmount, Vec3 position) {
        Services.NETWORK.sendToClient(player, new NeoHitPayload(damageAmount, position));
    }
}
