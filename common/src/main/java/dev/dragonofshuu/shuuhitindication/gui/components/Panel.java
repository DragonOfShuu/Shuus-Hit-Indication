package dev.dragonofshuu.shuuhitindication.gui.components;

import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;

public class Panel {
    private final FrameLayout frame;
    private final GridLayout innerGrid;
    private final GridLayout.RowHelper rowHelper;
    private final Rect background;

    public Panel(int columns, int padding, int fillColor, int borderColor) {
        this.frame = new FrameLayout();
        this.frame.defaultChildLayoutSetting().padding(padding);

        this.innerGrid = new GridLayout();
        
        // Add spacing between inner widgets if desired
        this.innerGrid.defaultCellSetting().padding(2);
        this.rowHelper = this.innerGrid.createRowHelper(columns);

        // Start with 0x0 size; we will resize it once child widgets are added
        this.background = new Rect(0, 0, fillColor, borderColor);

        // Layer 1: Background rectangle (added first so it renders behind)
        this.frame.addChild(this.background);
        
        // Layer 2: Inner grid with padding around its edges
        this.frame.addChild(this.innerGrid);
    }

    public <T extends LayoutElement> T addChild(T widget) {
        return this.rowHelper.addChild(widget);
    }

    public LayoutElement build() {
        // 1. Calculate the size required by all inner widgets
        this.innerGrid.arrangeElements();

        // 2. Resize the background rectangle to match the frame's padded size
        this.frame.arrangeElements();
        this.background.setWidth(this.frame.getWidth());
        this.background.setHeight(this.frame.getHeight());

        return this.frame;
    }
}
