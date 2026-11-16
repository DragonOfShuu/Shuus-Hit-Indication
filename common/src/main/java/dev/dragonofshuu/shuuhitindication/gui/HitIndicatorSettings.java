package dev.dragonofshuu.shuuhitindication.gui;

import dev.dragonofshuu.shuuhitindication.ShuuConfig;

public class HitIndicatorSettings {
    public float r;
    public float g;
    public float b;
    public float a;
    public float duration;
    public float scale;
    public String texture;

    private static HitIndicatorSettings DEFAULT_INSTANCE;
    private static int matchedVersion;
    private static int matchedSoftVersion;

    public HitIndicatorSettings(float r, float g, float b, float a, float duration, float scale, String texture) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.duration = duration;
        this.scale = scale;
        this.texture = texture;
    }

    public static HitIndicatorSettings fromConfig() {
        if (matchedVersion == ShuuConfig.getVersion()) {
            return DEFAULT_INSTANCE;
        }
        DEFAULT_INSTANCE = newFromConfig();
        matchedVersion = ShuuConfig.getVersion();
        return DEFAULT_INSTANCE;
    }

    public static HitIndicatorSettings newFromConfig() {
        return new HitIndicatorSettings(
            ShuuConfig.getHitIndicationR(),
            ShuuConfig.getHitIndicationG(),
            ShuuConfig.getHitIndicationB(),
            ShuuConfig.getHitIndicationA(),
            ShuuConfig.getHitIndicationDuration(),
            ShuuConfig.getHitIndicationScale(),
            ShuuConfig.getHitIndicationTexture().toString()
        );
    }

    public HitIndicatorSettings update() {
        if (matchedSoftVersion == ShuuConfig.getSoftVersion()) return this;
        this.r = ShuuConfig.getHitIndicationR();
        this.g = ShuuConfig.getHitIndicationG();
        this.b = ShuuConfig.getHitIndicationB();
        this.a = ShuuConfig.getHitIndicationA();
        this.duration = ShuuConfig.getHitIndicationDuration();
        this.scale = ShuuConfig.getHitIndicationScale();
        this.texture = ShuuConfig.getHitIndicationTexture().toString();
        matchedSoftVersion = ShuuConfig.getSoftVersion();
        return this;
    }
}
