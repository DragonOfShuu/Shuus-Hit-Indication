package dev.dragonofshuu.shuuhitindication;

import java.util.List;
import java.util.ArrayList;

import dev.dragonofshuu.shuuhitindication.utils.DelayedClientTask;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class HitIndicatorOrchestrator {
    private static List<HitIndicator> hitIndicators = new ArrayList<>();
    // public static final int HIT_INDICATOR_DURATION = 60;

    public static void registerHit(Vec3 position) {
        int waitTaskId = DelayedClientTask.runAfterTicks(ShuuConfig.getHitIndicationDuration(), (taskId) -> {
            hitIndicators.removeIf(h -> h.waitTaskId == taskId);
        });
        hitIndicators.add(new HitIndicator(position, waitTaskId));
    }

    public static boolean hasHit() {
        return hitIndicators.size() > 0;
    }

    public static List<HitIndicator> getHitIndicators() {
        return hitIndicators;
    }

    public static record HitIndicator(Vec3 hitPosition, int waitTaskId) {
        public DelayedClientTask.PendingTask getWaitTask() {
            return DelayedClientTask.getTask(waitTaskId);
        }

        public Float getHitRotation(Player relativeTo) {
            if (hitPosition == null) return null;
            float playerYaw = relativeTo.getYRot();
            Vec3 direction = relativeTo.position().subtract(hitPosition);
            return (float) Math.atan2(direction.x, -direction.z) - (float) Math.toRadians(playerYaw);
        }
    }
}
