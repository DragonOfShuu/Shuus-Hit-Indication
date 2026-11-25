package dev.dragonofshuu.shuuhitindication;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ShuuMod {

    public ShuuMod(IEventBus eventBus) {
        CommonClass.init();
    }
}