package jext.vfs;

public interface VProgressMonitor {

    /**
     * Called by the used: abort the job
     */
    void abort();

    /**
     * Check if the job is aborted of onFailed
     *
     * @return
     */
    boolean isAborted();

    // ----------------------------------------------------------------------

    /**
     * Job onStart.
     * Note: the 'totalTasks' can be not correct!
     *
     * @param totalTasks number of tasks to execute
     */
    void onStart(int totalTasks);

    /**
     * Start of a task
     * Note: the 'totalWorks' can be not correct!
     *
     * @param title label assigned to the task
     * @param totalWorks number of steps to do
     */
    void onStartTask(String title, int totalWorks);

    /**
     * Update the work processed by the task
     *
     * @param completed number of steps completed
     */
    void onUpdate(int completed);

    /**
     * End of a task
     * Note: it is always called, excepts if an exception is throwed
     */
    void endTask();


    /**
     * Job terminated with success
     */
    void onSuccess();

    /**
     * Job terminated for exception
     *
     * @param e
     */
    void onFailed(Throwable e);

    /**
     * Job terminated for user abort
     */
    void onAborted();

    /**
     * Exception that has onFailed the job
     * @return
     */
    Throwable getThrowedException();

}
