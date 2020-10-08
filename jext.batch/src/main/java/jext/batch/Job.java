package jext.batch;

import java.util.List;

public interface Job {

    // ----------------------------------------------------------------------
    //                      failed
    //  init -> progress -> success -> done
    //                      aborted


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

    /**
     * Called at the end (also after abort or exception)
     */
    void onDone();
}
