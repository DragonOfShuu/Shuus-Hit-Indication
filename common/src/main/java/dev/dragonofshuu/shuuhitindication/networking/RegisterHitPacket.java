package dev.dragonofshuu.shuuhitindication.networking;
import dev.dragonofshuu.shuuhitindication.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
public class RegisterHitPacket implements Packet<RegisterHitPacketListener> {
    public static final ResourceLocation REGISTER_HIT = new ResourceLocation(Constants.MOD_ID, "register_hit");

    private final float damageAmount;
    private final Vec3 position;

    public RegisterHitPacket(float damageAmount, Vec3 position) {
        this.damageAmount = damageAmount;
        this.position = position;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeFloat(damageAmount);
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
    }

    public static RegisterHitPacket read(FriendlyByteBuf buf) {
        float damageAmount = buf.readFloat();
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        Vec3 position = new Vec3(x, y, z);
        return new RegisterHitPacket(damageAmount, position);
    }

    public static RegisterHitPacket decode(FriendlyByteBuf buf) {
        return read(buf);
    }

    public static void encode(RegisterHitPacket packet, FriendlyByteBuf buf) {
        packet.write(buf);
    }

    public float getDamageAmount() {
        return damageAmount;
    }

    public Vec3 getPosition() {
        return position;
    }

    @Override
    public void handle(RegisterHitPacketListener handler) {
        handler.handle(this);
    }
}
