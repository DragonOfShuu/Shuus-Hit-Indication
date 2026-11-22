package dev.dragonofshuu.shuuhitindication.gui.screens;

import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorTextures;
import dev.dragonofshuu.shuuhitindication.gui.components.HitIndicator;
import dev.dragonofshuu.shuuhitindication.gui.components.Panel;
import dev.dragonofshuu.shuuhitindication.gui.components.Slider;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class HitIndicatorOptionsScreen extends Screen {

        private static final Component TITLE = Component.translatable("screen.shuuhitindication.hit_indicator_options");
        private static final Component DONE = CommonComponents.GUI_DONE;
        private static final Component HIT_ENABLED = Component.translatable("screen.shuuhitindication.hit_enabled");
        private static final Component HIT_DISABLED = Component.translatable("screen.shuuhitindication.hit_disabled");
        private static final Component RED_VALUE = Component.translatable("screen.shuuhitindication.red_value");
        private static final Component GREEN_VALUE = Component.translatable("screen.shuuhitindication.green_value");
        private static final Component BLUE_VALUE = Component.translatable("screen.shuuhitindication.blue_value");
        private static final Component ALPHA_VALUE = Component.translatable("screen.shuuhitindication.alpha_value");
        private static final Component SCALE_VALUE = Component.translatable("screen.shuuhitindication.scale_value");
        private static final Component VISUALIZE_SCREEN_LINK = Component
                        .translatable("screen.shuuhitindication.visualize_screen_link");
        private static final Component ADVANCED_SCREEN_LINK = Component
                        .translatable("screen.shuuhitindication.advanced_screen_link");

        private static final int BASE_WIDTH = 150;

        private final Screen previousScreen;

        public HitIndicatorOptionsScreen(Screen previousScreen) {
                super(TITLE);
                this.previousScreen = previousScreen;
        }

        @Override
        protected void init() {
                GridLayout gridLayout = new GridLayout();
                gridLayout.defaultCellSetting().padding(4, 4, 4, 0);
                GridLayout.RowHelper rowHelper = gridLayout.createRowHelper(2);

                rowHelper.addChild(Button
                                .builder(ShuuConfig.isHitIndicationEnabled() ? HIT_ENABLED : HIT_DISABLED, button -> {
                                        ShuuConfig.setEnableHitIndication(!ShuuConfig.isHitIndicationEnabled());
                                        button.setMessage(ShuuConfig.isHitIndicationEnabled() ? HIT_ENABLED
                                                        : HIT_DISABLED);
                                }).width(BASE_WIDTH - 4).build(), 1, gridLayout.newCellSettings());

                rowHelper.addChild(Button
                                .builder(ADVANCED_SCREEN_LINK,
                                                button -> this.minecraft
                                                                .gui.setScreen(new HitIndicatorAdvancedOptionsScreen(this)))
                                .width(BASE_WIDTH - 4).build(), 1, gridLayout.newCellSettings());

                rowHelper.addChild(CycleButton.<HitIndicatorTextures.Texture>builder(
                                item -> Component.literal(item.getName()),
                                () -> HitIndicatorTextures.getByName(ShuuConfig.getHitIndicationTextureName()))
                                .withValues(HitIndicatorTextures.getAll())
                                .create(0, 0, BASE_WIDTH - 4, 20, Component.literal("Texture"),
                                                (button, texture) -> ShuuConfig
                                                                .setHitIndicationTexture(texture.getName())));

                rowHelper.addChild(CycleButton
                                .<Float>builder(value -> Component.literal(String.valueOf(value)),
                                                ShuuConfig.getHitIndicationScale())
                                .withValues(1f, 2f, 3f, 4f, 5f, 6f, 8f, 10f, 12f)
                                .create(0, 0, BASE_WIDTH - 4, 20, SCALE_VALUE,
                                                (button, scale) -> ShuuConfig.setHitIndicationScale(scale)));

                rowHelper.addChild(
                                Slider.builder().width(BASE_WIDTH - 4).message(RED_VALUE)
                                                .value(ShuuConfig.getHitIndicationR())
                                                .onValueChanged(value -> ShuuConfig
                                                                .setHitIndicationR(value.floatValue()))
                                                .build());
                rowHelper.addChild(
                                Slider.builder().width(BASE_WIDTH - 4).message(GREEN_VALUE)
                                                .value(ShuuConfig.getHitIndicationG())
                                                .onValueChanged(value -> ShuuConfig
                                                                .setHitIndicationG(value.floatValue()))
                                                .build());
                rowHelper.addChild(
                                Slider.builder().width(BASE_WIDTH - 4).message(BLUE_VALUE)
                                                .value(ShuuConfig.getHitIndicationB())
                                                .onValueChanged(value -> ShuuConfig
                                                                .setHitIndicationB(value.floatValue()))
                                                .build());
                rowHelper.addChild(
                                Slider.builder().width(BASE_WIDTH - 4).message(ALPHA_VALUE)
                                                .value(ShuuConfig.getHitIndicationA())
                                                .onValueChanged(value -> ShuuConfig
                                                                .setHitIndicationA(value.floatValue()))
                                                .build());

                Panel panel = new Panel(1, 2, 0xFF000000, 0XFFFFFFFF);
                panel.addChild(new HitIndicator(0, 0, 60, 60, Component.empty()));
                rowHelper.addChild(panel.build(), 2, gridLayout.newCellSettings().alignHorizontallyCenter());
                rowHelper.addChild(
                                Button.builder(VISUALIZE_SCREEN_LINK,
                                                button -> this.minecraft.gui.setScreen(new HitIndicatorPreviewScreen(this)))
                                                .width(60).build(),
                                2, gridLayout.newCellSettings().alignHorizontallyCenter());

                rowHelper.addChild(Button.builder(DONE, (button) -> this.minecraft.gui.setScreen(this.previousScreen))
                                .width(BASE_WIDTH * 2).build(), 2);

                gridLayout.arrangeElements();

                FrameLayout.alignInRectangle(gridLayout, 0, 0, width, height, 0.5F, 0.25F);

                gridLayout.visitWidgets(this::addRenderableWidget);
        }

        @Override
        public void extractRenderState(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY,
                        float partialTick) {
                // this.extractBackground(guiGraphicsExtractor, mouseX, mouseY, partialTick);
                super.extractRenderState(guiGraphicsExtractor, mouseX, mouseY, partialTick);
        }

        public void removed() {
                ShuuConfig.save();
        }

        public void onClose() {
                this.minecraft.gui.setScreen(this.previousScreen);
        }
}
