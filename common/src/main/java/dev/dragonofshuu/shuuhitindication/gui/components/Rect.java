package dev.dragonofshuu.shuuhitindication.gui.components;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class Rect extends AbstractWidget {
    private final int color;
    private final int borderColor;

    public Rect(int width, int height, int color) {
        this(0, 0, width, height, color, 0);
    }

    public Rect(int width, int height, int color, int bordercolor) {
        this(0, 0, width, height, color, bordercolor);
    }

    public Rect(int x, int y, int width, int height, int color) {
        this(x, y, width, height, color, 0);
    }

    public Rect(int x, int y, int width, int height, int color, int borderColor) {
        super(x, y, width, height, Component.empty());
        this.color = color;
        this.borderColor = borderColor;
        this.active = false;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int x = this.getX();
        int y = this.getY();
        int w = this.getWidth();
        int h = this.getHeight();
        
        guiGraphics.fill(x, y, x + w, y + h, this.color);

        if (this.borderColor != 0)
            guiGraphics.renderOutline(x, y, w, h, this.borderColor);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
    
}
