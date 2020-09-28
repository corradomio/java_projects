package jext.batch.example;

import jext.batch.Job;
import jext.batch.impl.TaskImpl;

public class ExampleTask extends TaskImpl {

    private int t;
    private int count;

    ExampleTask(int t, int count) {
        this.t = t;
        this.count = count;
    }

    @Override
    public void onInit(Job job) {
        super.onInit(job);

        for (int i=0; i<count; ++i)
            addStep(new ExampleStep(t, i+1));
    }
}
