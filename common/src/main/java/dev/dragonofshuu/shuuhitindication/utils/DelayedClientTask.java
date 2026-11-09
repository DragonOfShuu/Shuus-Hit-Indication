package dev.dragonofshuu.shuuhitindication.utils;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class DelayedClientTask {
    private static final HashMap<Integer, PendingTask> pendingTasks = new HashMap<>();

    public static void onClientTick() {
        Iterator<Map.Entry<Integer, PendingTask>> iterator = pendingTasks.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, PendingTask> entry = iterator.next();
            PendingTask task = entry.getValue();
            task.RemainingTicks--;
            task.waitingFor++;
            if (task.RemainingTicks <= 0 && task.canRun.apply(task.waitingFor)) {
                task.action.apply(task.ID);
                iterator.remove();
            }
        }
    }

    public static int runAfterTicks(int ticks, ICleanup action) {
        PendingTask task = new PendingTask(ticks, (waiting) -> true, action);
        pendingTasks.put(task.ID, task);
        return task.ID;
    }

    public static int runAfterTicks(int ticks, ICanRun canRun, ICleanup action) {
        PendingTask task = new PendingTask(ticks, canRun, action);
        pendingTasks.put(task.ID, task);
        return task.ID;
    }

    public static int runAfterTicks(ICanRun canRun, ICleanup action) {
        PendingTask task = new PendingTask(0, canRun, action);
        pendingTasks.put(task.ID, task);
        return task.ID;
    }

    public static void cancelTask(int id) {
        pendingTasks.remove(id);
    }

    public static PendingTask getTask(int id) {
        return pendingTasks.get(id);
    }

    public static class PendingTask {
        public final int ID;

        public int RemainingTicks;
        ICanRun canRun;
        ICleanup action;
        /* Number of ticks this task has been waiting for */
        public int waitingFor;
        static int nextId;

        PendingTask(int remainingTicks, ICanRun canRun, ICleanup action) {
            this.RemainingTicks = remainingTicks;
            this.canRun = canRun;
            this.action = action;
            this.waitingFor = 0;
            this.ID = nextId++;
        }
    }    
}
