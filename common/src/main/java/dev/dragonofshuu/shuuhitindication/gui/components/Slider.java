package dev.dragonofshuu.shuuhitindication.gui.components;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class Slider extends AbstractSliderButton {
    private Consumer<Double> onValueChanged = value -> {};
    private Component baseMessage; 

    protected Slider(int width, Component message, double value, Consumer<Double> onValueChanged) {
        this(0, 0, width, 20, message, value, onValueChanged);
    }

    protected Slider(int x, int y, int width, int height, Component message, double value, Consumer<Double> onValueChanged) {
        super(x, y, width, height, message, value);
        this.onValueChanged = onValueChanged;
        this.baseMessage = message;
        this.updateMessage();
    }

    @Override
    protected void applyValue() {
        // Between 0.0 and 1.0
        double currentValue = this.value;
        this.onValueChanged.accept(currentValue);
    }

    @Override
    protected void updateMessage() {
        this.setMessage(this.baseMessage.copy().append(Component.literal(": " + (int)(this.value * 100) + "%")));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int x = 0;
        private int y = 0;
        private int width = 100;
        private int height = 20;
        private Component message = Component.literal("My Value: 0%");
        private double value = 0.0d;
        private Consumer<Double> onValueChanged = value -> {};

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder message(Component message) {
            this.message = message;
            return this;
        }

        public Builder value(double value) {
            this.value = value;
            return this;
        }

        public Builder onValueChanged(Consumer<Double> onValueChanged) {
            this.onValueChanged = onValueChanged;
            return this;
        }

        public Slider build() {
            var slider = new Slider(x, y, width, height, message, value, onValueChanged);
            return slider;
        }
    }
}
