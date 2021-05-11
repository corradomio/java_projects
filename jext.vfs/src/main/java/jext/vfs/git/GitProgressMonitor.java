package jext.vfs.git;

import jext.vfs.VProgressMonitor;
import org.eclipse.jgit.lib.ProgressMonitor;

class GitProgressMonitor implements ProgressMonitor {

    private VProgressMonitor pm;

    GitProgressMonitor(VProgressMonitor pm) {
        this.pm = pm;
    }

    @Override
    public void start(int totalTasks) {
        pm.onStart(totalTasks);
    }

    @Override
    public void beginTask(String title, int totalWork) {
        pm.onStartTask(title, totalWork);
    }

    @Override
    public void update(int completed) {
        pm.onUpdate(completed);
    }

    @Override
    public void endTask() {
        pm.endTask();
    }

    @Override
    public boolean isCancelled() {
        return pm.isAborted();
    }
}
