package dev.dragonofshuu.shuuhitindication.networking;
import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.HitIndicatorOrchestrator;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;

public class RegisterHitPacketListener implements PacketListener {

    // private final Connection connection;

    // public RegisterHitPacketListener(Connection connection) {
    //     this.connection = connection;
    // }

    public void handle(RegisterHitPacket packet) {
        // Handle the received packet here
        Constants.LOG.info("Received hit packet: damage=" + packet.getDamageAmount() + ", position=" + packet.getPosition());
        HitIndicatorOrchestrator.registerHit(packet.getPosition());
    }

    @Override
    public boolean isAcceptingMessages() {
        return true; // Since connection is commented out, always accept messages
    }

    @Override
    public void onDisconnect(Component reason) {
        Constants.LOG.info("Hit listener disconnected: " + reason.getString());
    }
}
