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

    private Logger logger = Logger.getLogger(TaskManager.class);

    // executor service used to execute tasks with or wthout requirements
    private ExecutorService executor;
    // thread used to check is some task is in the running queue but it is terminated
    // (this is an error, in theory it is not possible, BUT it can happen)
    // it is used also to execute tasks with requirement if the requirements are
    // satisfied
    private Thread watchdog;

    // lock used to synchronize the access to 'tasks' map
    private final Object lock = new Object();
    // map of waiting/running tasks
    private Map<String, Task> tasks = new HashMap<>();
    // waiting queue for tasks with requirements > 0 (for now just memory)
    private Queue<Task> waitingHeavyTasks = new LinkedList<>();

    // n of threads used in 'executor'
    private int nthreads;
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

    /**
     * Check if the task with the specified is id running
     */
    public boolean isRunning(String taskId) {
        Optional<Task> task = getTask(taskId);
        return task.isPresent();
    }

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

    public Optional<Task> getTask(Task task) {
        if (task == null)
            return Optional.empty();
        else
            return getTask(task.getId());
    }

    /**
     * Retrieve the list of all tasks (waiting & running)
     */
    public List<Task> listTasks() {
        List<Task> allTasks = new ArrayList<>();

        synchronized (lock) {
            allTasks.addAll(tasks.values());
            allTasks.addAll(waitingHeavyTasks);
        }
        return allTasks;
    }

    /**
     * Retrieve the list of tasks of the specified type
     *
     * @param type
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

    public int getThreadPoolSize() {
        if (executor instanceof ThreadPoolExecutor)
            return ((ThreadPoolExecutor)executor).getPoolSize();
        else
            return nthreads;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public TaskManager submit(Task task) {

        if (task.getStatus() != TaskStatus.CREATED)
            throw new RuntimeException("Wrong task status: " + task.getStatus());

        synchronized (lock) {

            if (isHeavyTask(task)) {
                task.waiting(this, null);
                waitingHeavyTasks.add(task);
                tasks.put(task.getId(), task);
            }
            else {
                Future<?> future = executor.submit(task);
                task.waiting(this, future);
            }
        }
        return this;
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
            // there are no heavy tasks running
            if (!isHeavyTaskRunning())
                return true;

            // check just the memory
            Task heavyTask = waitingHeavyTasks.peek();
            long memoryNeeded = heavyTask.getRequirements().getMemoryRequirements(1);

            // it can submit the task IF there is enough memory available
            return (memoryNeeded <= MemoryUtils.freeMemory());
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
        TaskRequirements treq = task.getRequirements();
        return treq != null && treq != TaskRequirements.noRequirements();
    }

    private boolean isHeavyTaskRunning() {
        for (Task task : tasks.values()) {
            if (isHeavyTask(task))
                return true;
        }
        return false;
    }

    private void removeTerminatedTasks() {
        List<String> terminated = new ArrayList<>();
        synchronized (lock) {
            tasks.values().stream().forEach(task -> {
                if (task.isTerminated())
                    terminated.add(task.getId());
            });

            terminated.forEach(taskId -> {
                tasks.remove(taskId);
            });
        }
    }

}
