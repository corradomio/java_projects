package jext.batch;

import java.util.List;

public interface Task {

    /**
     * Initialization of the task
     * This step creates the list of steps to execute
     *
     * It is possible to add extra steps at the END of the current steps list
     * 'getSteps()' is called at the end of each step completed
     */
    void init(Job job);

    Job getJob();

    /**
     * List of ALL steps (already executed, running and to execute).
     * It is called multiple time
     */
    List<Step> getSteps();

}
