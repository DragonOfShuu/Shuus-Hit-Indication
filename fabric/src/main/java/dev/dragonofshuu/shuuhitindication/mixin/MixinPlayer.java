package dev.dragonofshuu.shuuhitindication.mixin;
import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.networking.ShuuNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;

@Mixin(Player.class)
public class MixinPlayer {
    
    @Inject(method = "actuallyHurt", at = @At("TAIL"))
    private void onActuallyHurt(DamageSource damageSource, float originalDamageAmount, CallbackInfo cir) {
        var entityTakingDamage = (LivingEntity) (Object) this;
        if (entityTakingDamage.isInvulnerableTo(damageSource))
            return;
        ShuuNetworking.sendRegisterHit((ServerPlayer) entityTakingDamage, originalDamageAmount, entityTakingDamage.position()); 
    }
}
