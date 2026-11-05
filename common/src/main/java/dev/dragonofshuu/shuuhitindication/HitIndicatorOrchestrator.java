package dev.dragonofshuu.shuuhitindication;

import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class HitIndicatorOrchestrator {
    private static boolean hit;
    private static Vec3 hitPosition;

    private static int hitResetTaskId; 

    public static void registerHit(Vec3 position) {
        DelayedClientTask.cancelTask(hitResetTaskId);
        hit = true;
        hitPosition = position;
        hitResetTaskId = DelayedClientTask.runAfterTicks(60, () -> {
            hit = false;
            hitPosition = null;
        });
    }

    public static boolean hasHit() {
        return hit;
    }

    public static Vec3 getHitPosition() {
        return hitPosition;
    }

    public static Float getHitRotation(Player relativeTo) {
        if (hitPosition == null) return null;
        float playerYaw = relativeTo.getYRot();
        Vec3 direction = hitPosition.subtract(relativeTo.position());
        return (float) Math.atan2(direction.z, direction.x) - (float) Math.toRadians(playerYaw);
    }
}
