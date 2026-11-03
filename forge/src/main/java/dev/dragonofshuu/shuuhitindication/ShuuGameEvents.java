package dev.dragonofshuu.shuuhitindication;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ShuuGameEvents {
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        // Runs on the server by default; will need a custom packet so send the hit indication to the client
        Constants.LOG.info("Damage event triggered for entity: " + event.getEntity().getName().getString());
        if (event.getEntity() instanceof Player) {
            var damageSource = event.getSource();
            var entity = event.getEntity();
            var amount = event.getAmount();
            if (entity.level().isClientSide()) {
                // Handle client-side hit indication here
                Constants.LOG.info("PLAYER HAS BEEN HIT ON THE CLIENT: " + amount);
            } else {
                Constants.LOG.info("PLAYER HAS BEEN HIT ON THE SERVER: " + amount);
            }
        }
    }
}
