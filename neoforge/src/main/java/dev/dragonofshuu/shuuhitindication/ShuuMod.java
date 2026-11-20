package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.platform.NeoForgeRegisterHitHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(Constants.MOD_ID)
public class ShuuMod {

    public ShuuMod(IEventBus eventBus) {
        CommonClass.init();
    }

    @SubscribeEvent 
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        NeoForgeRegisterHitHelper.registerPayloadHandlers(registrar);
    }
}