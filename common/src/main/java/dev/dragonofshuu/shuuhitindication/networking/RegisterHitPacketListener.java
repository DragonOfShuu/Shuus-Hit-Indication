package dev.dragonofshuu.shuuhitindication.networking;
import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;

public class RegisterHitPacketListener implements PacketListener {

    public void handle(RegisterHitPacket packet) {
        HitIndicatorOrchestrator.registerHit(packet.getPosition());
    }

    @Override
    public boolean isAcceptingMessages() {
        return true; // Since we don't have access to connection, we'll just always accept messages.
    }

    @Override
    public void onDisconnect(Component reason) {
        Constants.LOG.info("Hit listener disconnected: " + reason.getString());
    }
}
