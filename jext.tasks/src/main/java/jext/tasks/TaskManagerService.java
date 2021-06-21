package jext.tasks;

import jext.logging.Logger;
import jext.util.MemoryUtils;
import jext.util.Sleep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TaskManagerService implements TaskManager, Runnable {

    public static TaskManagerService createTaskManager(int nthreads) {
        return new TaskManagerService(nthreads);
    }

    // ----------------------------------------------------------------------
    // Task Id
    // ----------------------------------------------------------------------

    private static long idgen = 0;
    private static long id;
    {
        id = System.currentTimeMillis();
    }

    public static String newTaskId() {
        synchronized (TaskManager.class) {
            return String.format("%d_%d", id, ++idgen);
        }
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private final Logger logger = Logger.getLogger(TaskManager.class);

    // executor service used to execute tasks with or wthout requirements
    private final ExecutorService executor;
    // thread used to check is some task is in the running queue but it is terminated
    // (this is an error, in theory it is not possible, BUT it can happen)
    // it is used also to execute tasks with requirement if the requirements are
    // satisfied
    private final Thread watchdog;

    // lock used to synchronize the access to 'tasks' map
    private final Object lock = new Object();
    // map of waiting/running tasks
    private final Map<String, Task> tasks = new HashMap<>();
    // waiting queue for tasks with requirements > 0 (for now just memory)
    private final Queue<Task> waitingHeavyTasks = new LinkedList<>();

    // n of threads used in 'executor'
    private final int nthreads;
    // if this service is terminated
    private transient boolean terminated;

    // ----------------------------------------------------------------------
    // TaskManager
    // ----------------------------------------------------------------------

    public TaskManagerService(int nthreads) {
        if (nthreads <= 0)
            nthreads = Runtime.getRuntime().availableProcessors() + nthreads;

        this.nthreads = nthreads;
        this.executor = Executors.newFixedThreadPool(nthreads);
        this.watchdog = new Thread(this);
        this.terminated = false;
        this.watchdog.start();
    }

    // ----------------------------------------------------------------------
    // Tasks
    // ----------------------------------------------------------------------

    // /**
    //  * Check if the task with the specified is id running
    //  */
    // public boolean isRunning(String taskId) {
    //     Optional<Task> task = getTask(taskId);
    //     return task.isPresent();
    // }

    /**
     * Retrieve the task with the specified id
     */
    public Optional<Task> getTask(String taskId) {
        if (taskId == null)
            return Optional.empty();

        synchronized (lock) {
            return Optional.ofNullable(tasks.getOrDefault(taskId, null));
        }
    }

    // public Optional<Task> getTask(Task task) {
    //     if (task == null)
    //         return Optional.empty();
    //     else
    //         return getTask(task.getId());
    // }

    /**
     * Retrieve the list of all tasks (waiting & running)
     */
    public List<Task> listTasks() {
        List<Task> allTasks = new ArrayList<>();

        synchronized (lock) {
            allTasks.addAll(tasks.values());
            // all heavy tasks are ALREADY registered in 'tasks'
            //allTasks.addAll(waitingHeavyTasks);
        }
        return allTasks;
    }

    /**
     * Retrieve the list of tasks of the specified type
     *
     * @param type the class name that implements the task
     */
    public List<Task> listTasks(String type) {
        return listTasks().stream()
            .filter(task -> task.getType().equals(type))
            .collect(Collectors.toList());
    }

    public List<Task> listWaitingTasks() {
        return listTasks().stream()
            .filter(task -> task.getStatus() == TaskStatus.WAITING)
            .collect(Collectors.toList());
    }

    public List<Task> listRunningTasks() {
        return listTasks().stream()
            .filter(task -> task.getStatus() == TaskStatus.RUNNING)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int getActiveThreads() {
        if (executor instanceof ThreadPoolExecutor) {
            int nactive = ((ThreadPoolExecutor)executor).getActiveCount();
            int nrunning = listRunningTasks().size();
            if (nactive != nrunning)
                logger.errorf("N of active threads (%d) different than the running tasks (%d)", nactive, nrunning);

            return nactive;
        }
        else
            return listRunningTasks().size();
    }

    public int getPoolThreads() {
        if (executor instanceof ThreadPoolExecutor)
            return ((ThreadPoolExecutor)executor).getPoolSize();
        else
            return nthreads;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public boolean submitIfNew(Task task) {
        if (task.getStatus() != TaskStatus.CREATED)
            throw new RuntimeException("Wrong task status: " + task.getStatus());

        synchronized (lock) {
            for (Task other : tasks.values())
                if (other.getExtendedType().equals(task.getExtendedType()))
                    return false;
            internalSubmit(task);
            return true;
        }
    }

    public TaskManager submit(Task task) {
        if (task.getStatus() != TaskStatus.CREATED)
            throw new RuntimeException("Wrong task status: " + task.getStatus());

        synchronized (lock) {
            internalSubmit(task);
        }
        return this;
    }

    private void internalSubmit(Task task) {
        tasks.put(task.getId(), task);

        if (isHeavyTask(task)) {
            // if there are no heavy tasks running, we can call the GC to
            // obtain more memory free
            if (!isHeavyTaskRunning())
                clearMemory();

            task.waiting(this, null);
            waitingHeavyTasks.add(task);
        }
        else {
            Future<?> future = executor.submit(task);
            task.waiting(this, future);
        }
    }

    public TaskManager abort(String taskId) {
        synchronized (lock) {
            if (tasks.containsKey(taskId))
                tasks.get(taskId).abort();
        }
        return this;
    }

    public TaskManager abortAll() {
        listTasks().forEach(Task::abort);
        tasks.clear();
        waitingHeavyTasks.clear();
        return this;
    }

    public TaskManager abortAll(String type) {
        listTasks(type).forEach(Task::abort);
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void waitForCompletion(int timeout, TimeUnit timeUnit) throws InterruptedException {
        terminated = true;
        executor.shutdown();
        executor.awaitTermination(timeout, timeUnit);
    }

    public TaskManagerService shutdown() {
        terminated = true;
        executor.shutdown();
        return this;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    public void runningTask(Task task) {
        synchronized (lock) {
            ;
        }
    }

    @Override
    public void finishedTask(Task task) {
        synchronized (lock) {
            tasks.remove(task.getId());
        }
    }

    // ----------------------------------------------------------------------
    // Watchdog
    // ----------------------------------------------------------------------

    @Override
    public void run() {
        while (!terminated) {
            removeTerminatedTasks();

            if (canSubmitHeavyTask())
                submitHeavyTask();

            // 2 times each second
            Sleep.sleep(500);
        }
    }

    private boolean canSubmitHeavyTask() {
        synchronized (lock) {
            // there are no heavy tasks waiting
            if (waitingHeavyTasks.isEmpty())
                return false;

            // there are heavy tasks waiting
            // there are no heavy tasks running
            if (!isHeavyTaskRunning())
                return true;

            // PROBLEM: you suppose that we have several heavy just started, and they
            //   have not allocated all memory. In this case, we can start ALL waiting
            //   heavy tasks.
            // SOLUTION:
            //   1) we collect the requirements from all running heavy task
            //   2) we retrieve the current allocated resources
            //   3) we take the max from 1) and 2) and we consider this value as
            //      the already used resources
            //   4) we evaluate the freeMemory as totalMemory - 3)
            //   5) if there is enough memory for the new task, we start it.

            Task heavyTask = waitingHeavyTasks.peek();

            TaskRequirements needed = new TaskRequirements();
            // add the requirement for the running heavy tasks
            addHeavyTasksRunningRequirements(needed);

            // check just the memory
            long neededMemory = Math.max(needed.getMemoryRequirements(), MemoryUtils.allocatedMemory());
            long freeMemory = MemoryUtils.maxMemory() - neededMemory;
            long taskMemory = heavyTask.getRequirements().getMemoryRequirements();

            // it can be submitted IF there is enough memory available
            boolean canSubmit = (taskMemory <= freeMemory);
            return canSubmit;
        }
    }

    private void submitHeavyTask() {
        synchronized (lock) {
            if (waitingHeavyTasks.isEmpty()) return;
            Task heavyTask = waitingHeavyTasks.remove();
            Future<?> future = executor.submit(heavyTask);
            heavyTask.waiting(this, future);
        }
    }

    private boolean isHeavyTask(Task task) {
        return task.hasRequirements();
    }

    private boolean isHeavyTaskRunning() {
        synchronized (lock) {
            for (Task task : tasks.values())
                if (task.getStatus() == TaskStatus.RUNNING && isHeavyTask(task))
                    return true;
        }
        return false;
    }

    private void addHeavyTasksRunningRequirements(TaskRequirements req) {
        synchronized (lock) {
            for (Task task : tasks.values())
                if (task.getStatus() == TaskStatus.RUNNING && isHeavyTask(task))
                    req.add(task.getRequirements());
        }
    }

    private void removeTerminatedTasks() {
        List<String> terminated = new ArrayList<>();
        synchronized (lock) {
            tasks.values().forEach(task -> {
                if (task.isTerminated())
                    terminated.add(task.getId());
            });

            terminated.forEach(taskId -> {
                tasks.remove(taskId);
            });
        }
    }


    // ----------------------------------------------------------------------
    // GC
    // ----------------------------------------------------------------------

    private static final long GC_TIMOUT = 60*1000; // 1 minute
    private long gcTimestamp;

    private void clearMemory() {
        long now = System.currentTimeMillis();
        long delta = now - gcTimestamp;
        if (delta < GC_TIMOUT) return;

        gcTimestamp = now;
        System.gc();
    }


}
