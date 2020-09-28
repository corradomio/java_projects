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
     * Called at the end (also after abort or exception)
     */
    void done();

    /**
     * List of ALL tasks (already executed, running and to execute).
     * It is called multiple time
     */
    List<Task> getTasks();

    // ----------------------------------------------------------------------
    //                                  failed
    //  create -> waiting -> running -> success -> done
    //                                  aborted
    //

    void onInit();

    void onProgress(Progress progress);

    void onSuccess();

    void onFailed();

    void onAborted();

    void onDone();
}
