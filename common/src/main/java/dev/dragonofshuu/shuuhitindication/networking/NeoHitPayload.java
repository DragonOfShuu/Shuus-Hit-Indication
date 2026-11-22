package dev.dragonofshuu.shuuhitindication.networking;

import dev.dragonofshuu.shuuhitindication.Constants;
import net.minecraft.resources.Identifier;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

public record NeoHitPayload(float damageAmount, Vec3 position) implements CustomPacketPayload {
    public static final Type<NeoHitPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "hit"));

    public static final StreamCodec<ByteBuf, NeoHitPayload> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.FLOAT,
        NeoHitPayload::damageAmount,
        ByteBufCodecs.fromCodec(Vec3.CODEC),
        NeoHitPayload::position,
        NeoHitPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
