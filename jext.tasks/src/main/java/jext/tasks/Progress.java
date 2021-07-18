package jext.tasks;

import java.io.Serializable;

/**
 * A task is composed of several 'works' and each work of several 'steps'
 *
 *      task(n)
 *          work_1(m)
 *              step_11
 *              ...
 *              step_1m
 *          ...
 *          work_n(m)
 *              step_n1
 *              ...
 *
 * Sequence of calls
 *
 *      start(nWorks)
 *          startWork(nSteps)   -- call the previous endWork() if necessary
 *              update(iSteps)
 *          endWork()           -- optional
 *      done()                  -- complete all steps and all works
 */


public class Progress implements Serializable {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private boolean inWork;
    protected ProgressStatus works = new ProgressStatus();
    protected ProgressStatus steps = new ProgressStatus();

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

    public void startWork(String message, int totalSteps) {
        if (inWork) endWork();
        works.update(message, 1);
        steps.setTotal(totalSteps);
        inWork = true;
    }

    public int update(String message, int wd) {
        return steps.update(message, wd);
    }

    public void endWork() {
        steps.completed();
        inWork = false;
    }

    public void done() {
        steps.completed();
        works.completed();
    }

    // ----------------------------------------------------------------------
    // Special operations operations
    // ----------------------------------------------------------------------

    public void addWorks(int nWorks) {
        works.setTotal(works.getTotal() + nWorks);
    }

    public void setStepsDone(int currentStep, int totalSteps) {
        steps.setCurrent(currentStep, totalSteps);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
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
