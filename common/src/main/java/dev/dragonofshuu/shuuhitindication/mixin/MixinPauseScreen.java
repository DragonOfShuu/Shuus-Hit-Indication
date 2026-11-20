package dev.dragonofshuu.shuuhitindication.mixin;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dragonofshuu.shuuhitindication.Constants;
import dev.dragonofshuu.shuuhitindication.gui.screens.HitIndicatorOptionsScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;

@Mixin(PauseScreen.class)
public abstract class MixinPauseScreen extends Screen {
    private static final ResourceLocation HIT_INDICATOR_SETTINGS_ICON = ResourceLocation
            .fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/hit-indicator-options.png");
    private static final int HIT_INDICATOR_SETTINGS_ICON_SIZE = 10;
    private static final int HIT_INDICATOR_SETTINGS_ICON_SCREEN_PADDING = 10;

    protected MixinPauseScreen(Component title) {
        super(title);
    }

    @Inject(method = "createPauseMenu", at = @At("TAIL"))
    private void onCreatePauseMenu(CallbackInfo ci) {
        WidgetSprites sprites = new WidgetSprites(HIT_INDICATOR_SETTINGS_ICON, HIT_INDICATOR_SETTINGS_ICON);
        this.addRenderableWidget(new ImageButton(
            HIT_INDICATOR_SETTINGS_ICON_SCREEN_PADDING, 
            this.height - HIT_INDICATOR_SETTINGS_ICON_SCREEN_PADDING - HIT_INDICATOR_SETTINGS_ICON_SIZE,
            HIT_INDICATOR_SETTINGS_ICON_SIZE,
            HIT_INDICATOR_SETTINGS_ICON_SIZE, sprites, button -> this.minecraft.setScreen(new HitIndicatorOptionsScreen(this))
            ));
    }
}
