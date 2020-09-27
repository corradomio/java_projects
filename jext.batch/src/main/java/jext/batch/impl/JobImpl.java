package jext.batch.impl;

import jext.batch.Job;
import jext.batch.Task;

import java.util.ArrayList;
import java.util.List;

public class JobImpl implements Job {

    protected List<Task> tasks = new ArrayList<>();

    @Override
    public void init() {

    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    protected JobImpl addTask(Task task) {
        tasks.add(task);
        return this;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onRunning() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onAborted() {

    }

    @Override
    public void onDone() {

    }
}
