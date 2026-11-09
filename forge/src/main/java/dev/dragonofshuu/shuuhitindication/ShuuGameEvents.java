package dev.dragonofshuu.shuuhitindication;
import dev.dragonofshuu.shuuhitindication.networking.ShuuNetworking;
import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShuuGameEvents {
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        // Runs on the server by default; will need a custom packet so send the hit indication to the client
        Constants.LOG.info("Damage event triggered for entity: " + event.getEntity().getName().getString());
        if (event.getEntity() instanceof Player player) {
            var damageSource = event.getSource();
            var amount = event.getAmount();
            var causingEntity = damageSource.getEntity();
            var damageSourcePosition = causingEntity == null ? damageSource.getSourcePosition() : causingEntity.position();
            if (damageSourcePosition == null)
                damageSourcePosition = player.position();
            ShuuNetworking.sendRegisterHit((ServerPlayer) player, amount, damageSourcePosition);
        }
    }

    @SubscribeEvent 
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        DelayedClientTask.onClientTick();
    }
}
