package dev.dragonofshuu.shuuhitindication.gui.screens;
import dev.dragonofshuu.shuuhitindication.ShuuConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
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
        rowHelper.addChild(Button.builder(ShuuConfig.isHitIndicationEnabled() ? HIT_ENABLED : HIT_DISABLED, button -> {
            ShuuConfig.setEnableHitIndication(!ShuuConfig.isHitIndicationEnabled());
            button.setMessage(ShuuConfig.isHitIndicationEnabled() ? HIT_ENABLED : HIT_DISABLED);
        }).width(204).build(), 2, gridLayout.newCellSettings().paddingTop(50));
        rowHelper.addChild(Button.builder(DONE, (button) -> this.minecraft.setScreen(this.previousScreen)).width(204).build(), 2);

        gridLayout.arrangeElements();
        
        FrameLayout.alignInRectangle(gridLayout, 0, 0, width, height, 0.5F, 0.25F);
        
        gridLayout.visitWidgets(this::addRenderableWidget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    public void removed() {
        ShuuConfig.save();
    }

    public void onClose() {
        this.minecraft.setScreen(this.previousScreen);
    }
}
