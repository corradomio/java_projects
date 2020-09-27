package jext.batch.example;

import jext.batch.impl.JobImpl;

public class ExampleJob extends JobImpl {

    @Override
    public void init() {
        addTask(new ExampleTask(1, 4));
        addTask(new ExampleTask(2, 6));
    }

}
