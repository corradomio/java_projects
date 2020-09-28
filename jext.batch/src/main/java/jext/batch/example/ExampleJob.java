package jext.batch.example;

import jext.batch.Progress;
import jext.batch.impl.JobImpl;

public class ExampleJob extends JobImpl {

    @Override
    public void onInit() {
        addTask(new ExampleTask(1, 2));
        addTask(new ExampleTask(2, 2));
    }

    // ----------------------------------------------------------------------

    @Override
    public void onProgress(Progress progress) {
        System.out.printf("... %.2f / %.2f\n", progress.tasks().getDone(), progress.steps().getDone());
    }

    @Override
    public void onSuccess() {
        System.out.println("onSuccess");
    }

    @Override
    public void onFailed() {
        System.out.println("onFailed");
    }

    @Override
    public void onAborted() {
        System.out.println("onAborted");
    }

    @Override
    public void onDone() {
        System.out.println("onDone");
    }
}
