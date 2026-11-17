package dev.dragonofshuu.shuuhitindication.gui.screens;

import net.minecraft.client.gui.screens.Screen;
import java.util.function.Consumer;

import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorSettings;
import dev.dragonofshuu.shuuhitindication.gui.components.Slider;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class HitIndicatorPreviewScreen extends Screen {
    
    private static final Component TITLE = Component.translatable("screen.shuuhitindication.hit_indicator_preview");
    private static final Component ROTATION_LABEL = Component.translatable("screen.shuuhitindication.rotation");

    private final Screen previousScreen;

    private final HitIndicatorSettings settings;

    private float rotation = 0;

    public HitIndicatorPreviewScreen(Screen previousScreen) {
        super(TITLE);
        this.previousScreen = previousScreen;
        this.settings = HitIndicatorSettings.fromConfig();
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(this.previousScreen)).size(50, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal("Toggle Background"), button -> ShuuConfig.setShowBackground(!ShuuConfig.isShowBackground())).size(50, 20).pos(0, 20).build());
        Consumer<Double> rotationConsumer = value -> this.rotation = (float) (value*2d*Math.PI - Math.PI);
        this.addRenderableWidget(Slider.builder().message(ROTATION_LABEL).value(this.rotation).value(0.5d).onValueChanged(rotationConsumer).position(0, this.height - 20).width(this.width / 4).build());
    }
    
    @Override
    public void render(net.minecraft.client.gui.GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (ShuuConfig.isShowBackground()) {
            guiGraphics.fill(0, 0, this.width, this.height, 0xFF000000);
        }
        HitIndicatorGUI.renderHitIndicator(guiGraphics, partialTick, this.width, this.height, (int) this.settings.duration, this.settings, this.rotation, 0, 0);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.previousScreen);
    }
}
