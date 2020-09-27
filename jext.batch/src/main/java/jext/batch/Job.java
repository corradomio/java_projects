package jext.batch;

import java.util.List;

public interface Job {

    /**
     * Initialization of the job.
     * This step creates the list of tasks to execute.
     *
     * It is possible to add extra tasks at the END of the current tasks list
     * 'getTasks()' is called at the end of each task completed
     */
    void init();

    /**
     * List of ALL tasks (already executed, running and to execute).
     * It is called multiple time
     */
    List<Task> getTasks();

    void onCreate();

    void onWaiting();

    void onRunning();

    void onSuccess();

    void onFailed();

    void onAborted();

    void onDone();
}
