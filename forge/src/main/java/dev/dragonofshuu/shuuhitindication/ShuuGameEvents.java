package dev.dragonofshuu.shuuhitindication;
import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShuuGameEvents {
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            PlayerEvents.onPlayerDamage(player, event.getSource(), event.getAmount());
        }
    }

    @SubscribeEvent 
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        DelayedClientTask.onClientTick();
    }
}
