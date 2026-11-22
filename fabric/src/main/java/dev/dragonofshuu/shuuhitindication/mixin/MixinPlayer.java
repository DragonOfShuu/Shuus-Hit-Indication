package dev.dragonofshuu.shuuhitindication.mixin;
import dev.dragonofshuu.shuuhitindication.PlayerEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.damagesource.DamageSource;

@Mixin(Player.class)
public class MixinPlayer {
    
    @Inject(method = "actuallyHurt", at = @At("TAIL"))
    private void onActuallyHurt(ServerLevel level, DamageSource damageSource, float originalDamageAmount, CallbackInfo cir) {
        PlayerEvents.onPlayerDamage(level, (Player) (Object) this, damageSource, originalDamageAmount);
    }
}
