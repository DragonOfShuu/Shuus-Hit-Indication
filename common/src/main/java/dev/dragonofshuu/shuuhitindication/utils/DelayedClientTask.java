package dev.dragonofshuu.shuuhitindication.utils;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
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
                task.action.run();
                iterator.remove();
            }
        }
    }

    public static int runAfterTicks(int ticks, Runnable action) {
        PendingTask task = new PendingTask(ticks, (waiting) -> true, action);
        pendingTasks.put(task.id, task);
        return task.id;
    }

    public static int runAfterTicks(int ticks, Function<Integer, Boolean> canRun, Runnable action) {
        PendingTask task = new PendingTask(ticks, canRun, action);
        pendingTasks.put(task.id, task);
        return task.id;
    }

    public static int runAfterTicks(Function<Integer, Boolean> canRun, Runnable action) {
        PendingTask task = new PendingTask(0, canRun, action);
        pendingTasks.put(task.id, task);
        return task.id;
    }

    public static void cancelTask(int id) {
        pendingTasks.remove(id);
    }

    private static class PendingTask {
        int RemainingTicks;
        Function<Integer, Boolean> canRun;
        Runnable action;
        /* Number of ticks this task has been waiting for */
        int waitingFor;
        int id;
        static int nextId;

        PendingTask(int remainingTicks, Function<Integer, Boolean> canRun, Runnable action) {
            this.RemainingTicks = remainingTicks;
            this.canRun = canRun;
            this.action = action;
            this.waitingFor = 0;
            this.id = nextId++;
        }
    }    
}
