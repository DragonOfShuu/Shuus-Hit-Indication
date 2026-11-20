package dev.dragonofshuu.shuuhitindication;
import dev.dragonofshuu.shuuhitindication.platform.NeoForgeRegisterHitHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ShuuModEvents {
    @SubscribeEvent 
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        NeoForgeRegisterHitHelper.registerPayloadHandlers(registrar);
    }
}
