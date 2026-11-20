package dev.dragonofshuu.shuuhitindication;
import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

// @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
@EventBusSubscriber(modid = Constants.MOD_ID)
public class ShuuGameEvents {
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            PlayerEvents.onPlayerDamage(player, event.getSource(), event.getNewDamage());
        }
    }

    @SubscribeEvent 
    public static void onClientTick(ClientTickEvent.Post event) {
        DelayedClientTask.onClientTick();
    }
}
