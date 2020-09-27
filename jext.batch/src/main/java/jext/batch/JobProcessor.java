package jext.batch;

import jext.batch.util.Done;

public class JobProcessor implements Runnable {

    private static Task[] NO_TASKS = new Task[0];
    private static Step[] NO_STEPS = new Step[0];

    private JobRunner runner;
    private Status status = Status.CREATED;
    private Long id;
    private volatile boolean aborted;

    private Job job;
    private Task[] tasks;
    private Step[] steps;
    private int itask;
    private int istep;

    private Throwable exception;

    public JobProcessor(JobRunner runner, Job job) {
        this.runner = runner;
        this.job = job;
        this.id = System.currentTimeMillis();
        setStatus(Status.WAITING);
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Done getTasksDone() {
        return new Done(itask, tasks != null ? tasks.length : 0);
    }

    public Done getStepsDone() {
        return new Done(istep, steps != null ? steps.length : 0);
    }

    public void abort() {
        aborted = true;
        steps[istep].abort();
    }

    @Override
    public void run() {

        if(!aborted)
        try {
            setStatus(Status.RUNNING);
            runner.running(this);

            job.init();

            tasks = job.getTasks().toArray(NO_TASKS);
            itask = 0;
            while (itask < tasks.length && !aborted) {
                Task task = tasks[itask++];

                task.init(job);

                steps = task.getSteps().toArray(NO_STEPS);
                istep = 0;
                while (istep < steps.length && !aborted) {
                    Step step = steps[istep++];

                    step.init(task);

                    step.run();

                    steps = task.getSteps().toArray(NO_STEPS);
                }
            }
        }
        catch (Throwable e) {
            exception = e;
        }
        finally {
            if (aborted || exception != null && exception instanceof AbortedException)
                setStatus(Status.ABORTED);
            else if (exception != null)
                setStatus(Status.FAILED);
            else
                setStatus(Status.SUCCESS);

            runner.done(this);
            setStatus(Status.DONE);
        }

    }

    private void setStatus(Status status) {
        this.status = status;
        switch (status) {
            case CREATED:
                job.onCreate();
                break;

            case WAITING:
                job.onWaiting();
                break;

            case RUNNING:
                job.onRunning();
                break;

            case SUCCESS:
                job.onSuccess();
                break;

            case FAILED:
                job.onFailed();
                break;

            case ABORTED:
                job.onAborted();
                break;

            case DONE:
                job.onDone();
                break;
        }
    }

}
