package dev.dragonofshuu.shuuhitindication.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import dev.dragonofshuu.shuuhitindication.Constants;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(method = "actuallyHurt", at = @At("TAIL"))
    // private void onActuallyHurt(DamageSource source, float amount, CallbackInfo cir, @Local float damageAmount) {
    private void onActuallyHurt(DamageSource damageSource, float damageAmount, CallbackInfo cir) {
        // Your code here
        Constants.LOG.info("Damage taken.");
    }
}
