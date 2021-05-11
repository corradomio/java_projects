package jext.versioning.git.util;

import jext.versioning.VSProgressMonitor;
import org.eclipse.jgit.lib.ProgressMonitor;

public class GitProgressMonitor implements ProgressMonitor, VSProgressMonitor {

    @Override
    public void start(int totalTasks) {

    }

    @Override
    public void beginTask(String title, int totalWork) {

    }

    @Override
    public void update(int completed) {

    }

    @Override
    public void endTask() {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
