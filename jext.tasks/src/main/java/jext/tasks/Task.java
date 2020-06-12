package jext.tasks;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface Task extends Runnable {

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Unique id of the task
     */
    String getId();

    /**
     * Task type (a generic string)
     */
    String getType();

    /**
     * Status of the task
     */
    TaskStatus getStatus();

    /**
     * Status of the task
     */
    TaskStatus getPreviousStatus();

    // ----------------------------------------------------------------------
    // Support properties
    // ----------------------------------------------------------------------

    /**
     * Retrieve the last emitted message
     */
    String getMessage();

    /**
     * Retrieve the progress status
     */
    Progress getProgress();

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Add a listener
     */
    void addListener(TaskStatusListener listener);

    /**
     * Abort the running task.
     * Note: if the task is already terminated, it does nothing.
     */
    void abort();

    // ----------------------------------------------------------------------
    // Thread support
    // ----------------------------------------------------------------------

    /**
     * Callback called when the task is inserted into the waiting queue
     *
     * @param manager manager that handle the running tasks
     * @param future object used to wait the thread termination
     */
    void waiting(TaskManager manager, Future<?> future);

    /**
     * Wait for the task completion
     */
    void waitForCompletion(long timeout, TimeUnit timeUnit);

}
