package dev.dragonofshuu.shuuhitindication.platform;

import dev.dragonofshuu.shuuhitindication.networking.NeoHitPayload;
import dev.dragonofshuu.shuuhitindication.platform.services.INetworkHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class FabricRegisterHitHelper implements INetworkHelper {

    @Override
    public void sendToClient(ServerPlayer player, NeoHitPayload message) {
        ServerPlayNetworking.send(player, message);
    }
}
