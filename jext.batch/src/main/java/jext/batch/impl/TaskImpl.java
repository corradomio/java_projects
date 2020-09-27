package jext.batch.impl;

import jext.batch.Job;
import jext.batch.Step;
import jext.batch.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskImpl implements Task {

    protected Job job;
    protected List<Step> steps = new ArrayList<>();

    @Override
    public void init(Job job) {
        this.job = job;
    }

    @Override
    public Job getJob() {
        return job;
    }

    @Override
    public List<Step> getSteps() {
        return steps;
    }

    protected void addStep(Step step) {
        this.steps.add(step);
    }

}
