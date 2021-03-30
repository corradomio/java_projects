package jext.tasks;

import jext.util.Parameters;

import java.util.List;
import java.util.concurrent.Future;

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

    boolean isTerminated();

    // ----------------------------------------------------------------------
    // Task status
    // ----------------------------------------------------------------------

    /**
     * Current status of the task
     */
    TaskStatus getStatus();

    /**
     * List of status changed with timestamp
     */
    List<StatusChange> getStatusHistory();

    /**
     * When the task has reached the current state
     */
    long getTimestamp();

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

    /**
     * Retrieve the list of parameters
     * @return
     */
    Parameters getParameters();

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Abort the running task.
     * Note: if the task is already terminated, it does nothing.
     */
    void abort();

    // ----------------------------------------------------------------------
    // TaskManager support
    // ----------------------------------------------------------------------

    /**
     * Add a listener
     */
    void addListener(TaskStatusListener listener);

    /**
     * Callback called when the task is inserted into the waiting queue
     *
     * @param manager manager that handle the running tasks
     * @param future object used to wait the thread termination
     */
    void waiting(TaskManager manager, Future<?> future);

}
