package dev.dragonofshuu.shuuhitindication.gui.screens;

import java.util.function.Consumer;

import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import dev.dragonofshuu.shuuhitindication.gui.components.Slider;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.layouts.FrameLayout;

public class HitIndicatorAdvancedOptionsScreen extends Screen {

    private static final Component IS_EXTERNAL_DAMAGE_ONLY = Component
            .translatable("screen.shuuhitindication.advanced_options.is_external_damage_only");
    private static final Component DURATION = Component.translatable("screen.shuuhitindication.advanced_options.duration");
    private static final Component TITLE = Component.translatable("screen.shuuhitindication.advanced_options");

    private static final int BASE_WIDTH = 150;

    private final Screen previousScreen;

    protected HitIndicatorAdvancedOptionsScreen(Screen previousScreen) {
        super(TITLE);
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.defaultCellSetting().padding(4, 4, 4, 0);
        GridLayout.RowHelper rowHelper = gridLayout.createRowHelper(2);

        rowHelper.addChild(Button
                .builder(CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(this.previousScreen)).width(BASE_WIDTH*2).build(), 2);

        rowHelper.addChild(CycleButton
                .<Boolean>builder(value -> value ? CommonComponents.GUI_YES : CommonComponents.GUI_NO)
                .withValues(true, false).withInitialValue(ShuuConfig.isExternalDamageOnly()).create(0, 0, BASE_WIDTH-4,
                        20, IS_EXTERNAL_DAMAGE_ONLY, (button, value) -> ShuuConfig.setExternalDamageOnly(value)));

        rowHelper.addChild(CycleButton
                .<Integer>builder(value -> Component.literal(value.toString()))
                .withValues(1, 2, 3, 4, 5, 6, 8, 10, 12).withInitialValue(ShuuConfig.getMaximumHits()).create(0, 0,
                        BASE_WIDTH-4,
                        20, Component.translatable("screen.shuuhitindication.advanced_options.maximum_hits"),
                        (button, value) -> ShuuConfig.setMaximumHits(value)));

        double initialValue = (((double) ShuuConfig.getHitIndicationDuration())-20d)/120d;
        Consumer<Double> onValueChanged = value -> ShuuConfig.setHitIndicationDuration((int) (value*120d+20d));
        rowHelper.addChild(Slider.builder().message(DURATION).value(initialValue).width(BASE_WIDTH*2).onValueChanged(onValueChanged).build(), 2);

        gridLayout.arrangeElements();

        FrameLayout.alignInRectangle(gridLayout, 0, 0, this.width, this.height, 0.5F, 0.25F);

        gridLayout.visitWidgets(this::addRenderableWidget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.previousScreen);
    }
}
