package jext.tasks;

import java.io.Serializable;

/**
 * A task is composed of several 'works' and each work of several 'steps'
 *
 *      task
 *          work_1
 *              step_11
 *              ...
 *          ...
 *          work_n
 *              step_n1
 *              ...
 */


public class Progress implements Serializable {
    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private ProgressStatus works = new ProgressStatus();
    private ProgressStatus steps = new ProgressStatus();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Progress() { }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------
    // DO NOT REMOVE: used in serialization

    public ProgressStatus getWorks() { return works; }

    public ProgressStatus getSteps() { return steps; }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void start(int totalWorks) {
        works.setTotal(totalWorks);
        steps.setTotal(0);
    }

    // public void setWork(int currentWork, int totalWorks) {
    //     works.setCurrent(currentWork, totalWorks);
    //     steps.setTotal(0);
    // }

    public void startWork(int totalSteps) {
        works.update(1);
        steps.setTotal(totalSteps);
    }

    public int update(int wd) {
        return steps.update(wd);
    }

    // public void setWorkDone(int currentWork) {
    //     steps.setCurrent(currentWork);
    // }

    public void setStepsDone(int currentStep, int totalSteps) {
        steps.setCurrent(currentStep, totalSteps);
    }

    public void endWork() {
        steps.completed();
    }

    public void done() {
        works.completed();
    }

    public String toString() {
        return String.format("work[%d/%d (%f)]/step[%d/%d (%f)]",
            works.getCurrent(),
            works.getTotal(),
            works.getDone(),
            steps.getCurrent(),
            steps.getTotal(),
            steps.getDone());
    }

}
