package dev.dragonofshuu.shuuhitindication.gui.components;

import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorGUI;
import dev.dragonofshuu.shuuhitindication.gui.HitIndicatorSettings;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class HitIndicator extends AbstractWidget {

    private HitIndicatorSettings settings;

    public HitIndicator(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.settings = HitIndicatorSettings.newFromConfig();
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.settings.update();
        this.settings.scale = 1f;
        HitIndicatorGUI.renderHitIndicator(guiGraphics, partialTick, this.width, this.height, (int) this.settings.duration, this.settings, 0, this.getX(), this.getY());
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
    
}
