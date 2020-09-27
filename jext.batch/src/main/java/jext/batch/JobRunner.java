package jext.batch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class JobRunner implements Runnable {

    private static JobRunner runner = new JobRunner();

    public static JobRunner getRunner() {
        return runner;
    }

    private volatile boolean aborted;
    private Queue<JobProcessor> waiting = new LinkedList<JobProcessor>();
    private Map<Long, JobProcessor> running = new HashMap<>();
    private Map<Long, JobProcessor> jobs = new HashMap<>();

    public void submit(Job job) {
        JobProcessor jp = new JobProcessor(this, job);
        synchronized (jobs) {
            jobs.put(jp.getId(), jp);
        }

        synchronized (waiting) {
            waiting.add(jp);
            waiting.notify();
        }
    }

    public void abort() {
        aborted = true;
    }

    public void run() {
        while(!aborted) {
            JobProcessor jp = null;

            synchronized (waiting) {
                try {
                    waiting.wait(1000);
                } catch(Exception e) { }

                if (aborted)
                    break;
                if (!waiting.isEmpty())
                    jp = waiting.remove();
            }

            if (jp != null)
                new Thread(jp).start();
        }
    }

    void running(JobProcessor jp) {
        synchronized (jobs) {
            running.put(jp.getId(), jp);
        }
    }

    void done(JobProcessor jp) {
        synchronized (jobs) {
            running.remove(jp.getId());
            jobs.remove(jp.getId());
        }
    }
}
