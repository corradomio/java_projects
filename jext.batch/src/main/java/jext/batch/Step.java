package jext.batch;

import java.util.Optional;

public interface Step {

    /**
     * Parent task
     */
    Task getTask();

    /** Task status */
    Status getStatus();

    /**
     * If terminated for exception
     */
    Optional<Throwable> getException();

    /**
     * Execute the step.
     *
     * If the step is aborted, it can throws an AbortedException
     */
    void run();

    /**
     * Request to abort this step
     */
    void abort();

    /**
     * Initialization of the step
     */
    void onInit(Task task);


    void onRun() throws Exception;

    /**
     * Called at the end (also after abort or exception)
     */
    void onDone();

}
