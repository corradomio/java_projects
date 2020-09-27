package jext.batch.example;

import jext.batch.impl.StepImpl;

public class ExampleStep extends StepImpl {

    private int t;
    private int i;

    public ExampleStep(int t, int i) {
        this.t = t;
        this.i = i;
    }


    @Override
    public void run() {
        System.out.println((t)*10 + (i));
    }
}
