package jext.batch;

public interface Step {

    /**
     * Initialization of the step
     */
    void init(Task task);

    Task getTask();

    /**
     * Request to abort the current step
     */
    void abort();

    /**
     * Execute the step.
     *
     * If the step is aborted, it can throws an AbortedException
     *
     * @throws Exception
     */
    void run() throws Exception;
}
