package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.networking.ShuuNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public class PlayerEvents {
    /**
     * Server side event called by a modloader when the player takes damage.
     * @param player The player who took damage.
     * @param damageSource The source of the damage.
     * @param amount The amount of damage taken.
     */
    public static void onPlayerDamage(Player player, DamageSource damageSource, float amount) {
        if (player.level().isClientSide())
            return;
        if (player.isInvulnerableTo(damageSource))
            return;
        var causingEntity = damageSource.getEntity();
        var damageSourcePosition = causingEntity == null ? damageSource.getSourcePosition() : causingEntity.position();
        if (damageSourcePosition == null) {
            if (ShuuConfig.isExternalDamageOnly())
                return;
            damageSourcePosition = player.position();
        }
        ShuuNetworking.sendRegisterHit((ServerPlayer) player, amount, damageSourcePosition);
    }
}
