package dev.dragonofshuu.shuuhitindication;
import dev.dragonofshuu.shuuhitindication.networking.ShuuNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShuuGameEvents {
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        // Runs on the server by default; will need a custom packet so send the hit indication to the client
        Constants.LOG.info("Damage event triggered for entity: " + event.getEntity().getName().getString());
        if (event.getEntity() instanceof Player) {
            var damageSource = event.getSource();
            var entity = event.getEntity();
            var amount = event.getAmount();
            ShuuNetworking.sendRegisterHit((ServerPlayer) entity, amount, damageSource.getSourcePosition());
        }
    }
}
