package jext.batch.impl;

import jext.batch.Step;
import jext.batch.Task;

public class StepImpl implements Step {

    protected Task task;
    protected volatile boolean aborted;

    @Override
    public void init(Task task) {
        this.task = task;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public void abort() {
        aborted = true;
    }

    @Override
    public void run() {

    }
}
