package jext.batch.impl;

import jext.batch.Status;
import jext.batch.Step;
import jext.batch.Task;

import java.util.Optional;


public class StepImpl implements Step {

    protected Task task;
    protected Status status = Status.CREATED;
    protected Throwable exception;
    protected volatile boolean aborted;

    @Override
    public void onInit(Task task) {
        this.task = task;
        status = Status.CREATED;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Optional<Throwable> getException() {
        return exception == null ? Optional.empty() : Optional.of(exception);
    }

    @Override
    public void abort() {
        aborted = true;
        status = Status.ABORTED;
    }

    @Override
    public void run() {
        try {
            status = Status.RUNNING;
            onRun();
            status = Status.SUCCESS;
        }
        catch (Throwable e) {
            exception = e;
            status = status.FAILED;
        }
    }

    @Override
    public void onRun() throws Exception {

    }

    @Override
    public void onDone() {

    }
}
