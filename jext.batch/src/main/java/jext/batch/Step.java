package jext.batch;

public interface Step {

    Task getTask();

    /**
     * Initialization of the step
     */
    void onInit(Task task);

    /**
     * Execute the step.
     *
     * If the step is aborted, it can throws an AbortedException
     */
    void run() throws Exception;

    /**
     * Request to abort this step
     */
    void abort();

    /**
     * Called at the end (also after abort or exception)
     */
    void onDone();

}
