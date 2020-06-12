package jext.tasks;

import jext.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TaskManagerService implements TaskManager {

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

    private ExecutorService executor;

    private final Object lock = new Object();
    private Map<String, Task> tasks = new HashMap<>();
    private int nthreads;

    // ----------------------------------------------------------------------
    // TaskManager
    // ----------------------------------------------------------------------

    public TaskManagerService(int nthreads) {
        if (nthreads <= 0)
            nthreads = Runtime.getRuntime().availableProcessors() + nthreads;

        this.nthreads = nthreads;
        this.executor = Executors.newFixedThreadPool(nthreads);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // /**
    //  * Unique id assigned to the TaskManager
    //  */
    // public static String getId() { return Long.toString(id); }

    /**
     * Check if the taskId is a valid id
     */
    public boolean isRunning(String taskId) {
        Task task = getTask(taskId);
        return task != null;
    }

    /**
     * Retrieve the task with the specified id
     *
     * Note: the task can not exist because ALREADY terminates!
     *
     * @param taskId id of the task to retrieve
     * @return
     */
    public Task getTask(String taskId) {
        if (taskId == null)
            return null;

        synchronized (lock) {
            return tasks.getOrDefault(taskId, null);
        }
    }

    public Task getTask(Task task) {
        if (task == null)
            return null;
        else
            return getTask(task.getId());
    }

    /**
     * retrieve the list of tasks
     *
     * @return
     */
    public List<Task> listTasks() {
        List<Task> selectedTasks = new ArrayList<>();

        synchronized (lock) {
            selectedTasks.addAll(tasks.values());
        }
        return selectedTasks;
    }

    /**
     * Retrieve the list of tasks of the specified type
     *
     * @param type
     * @return
     */
    public List<Task> listTasks(String type) {
        List<Task> selectedTasks = new ArrayList<>();

        synchronized (lock) {
            tasks.values().forEach(task -> {
                if(type.equals(task.getType()))
                    selectedTasks.add(task);
            });
        }

        return selectedTasks;
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

    public List<Task> listWaitingTasks() {
        return listTasks().stream()
            .filter(task -> task.getStatus() != TaskStatus.RUNNING)
            .collect(Collectors.toList());
    }

    public List<Task> listRunningTasks() {
        return listTasks().stream()
            .filter(task -> task.getStatus() == TaskStatus.RUNNING)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public TaskManager submit(Task task) {

        if (task.getStatus() != TaskStatus.CREATED)
            throw new RuntimeException("Wrong task status: " + task.getStatus());

        synchronized (lock) {
            Future<?> future = executor.submit(task);
            task.waiting(this, future);
            tasks.put(task.getId(), task);
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
        synchronized (lock) {
            tasks.values().forEach(Task::abort);
        }
        return this;
    }

    public TaskManager abortAll(String type) {
        synchronized (lock) {
            tasks.values().forEach(task -> {
                if (type.equals(task.getType()))
                    task.abort();
            });
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void waitForCompletion(int timeout, TimeUnit timeUnit) throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(timeout, timeUnit);
    }

    public TaskManagerService shutdown() {
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

}
