package org.hls.check;

import jext.batch.JobRunner;
import jext.batch.example.ExampleJob;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(JobRunner.getRunner());
        t.start();

        JobRunner.getRunner().submit(new ExampleJob());


        t.join();


    }
}
