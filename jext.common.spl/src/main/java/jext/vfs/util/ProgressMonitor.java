package jext.vfs.util;

import jext.vfs.VProgressMonitor;

public class ProgressMonitor implements VProgressMonitor {

    private boolean aborted;
    private Throwable catchedException;


    @Override
    public void abort() {
        aborted = true;
    }

    @Override
    public boolean isAborted() {
        return aborted;
    }

    @Override
    public void onStart(int totalTasks) {

    }

    @Override
    public void onStartTask(String title, int totalWorks) {

    }

    @Override
    public void onUpdate(int completed) {

    }

    @Override
    public void endTask() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(Throwable e) {
        catchedException = e;
    }

    @Override
    public void onAborted() {

    }

    @Override
    public Throwable getThrowedException() {
        return catchedException;
    }
}
