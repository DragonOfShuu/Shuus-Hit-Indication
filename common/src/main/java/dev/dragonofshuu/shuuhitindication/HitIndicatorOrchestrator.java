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
        addHitIndicator(new HitIndicator(position, waitTaskId));
    }

    private static void addHitIndicator(HitIndicator hit) {
        if (hitIndicators.size() >= ShuuConfig.getMaximumHits()) {
            var oldHit = hitIndicators.remove(0);
            oldHit.cancel();
        }
        hitIndicators.add(hit);
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

        public void cancel() {
            DelayedClientTask.cancelTask(getWaitTask().ID);
        }

        public Float getHitRotation(Player relativeTo, float partialTick) {
            if (hitPosition == null) return null;
            float playerYaw = relativeTo.getViewYRot(partialTick);
            Vec3 direction = relativeTo.getPosition(partialTick).subtract(hitPosition);
            return (float) Math.atan2(direction.x, -direction.z) - (float) Math.toRadians(playerYaw);
        }
    }
}
