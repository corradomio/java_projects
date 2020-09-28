package jext.batch;

public class JobProcessor implements Runnable {

    private final JobRunner runner;
    private final Long id;

    private final Job job;
    private final Progress p;

    private Status status = Status.CREATED;
    private volatile boolean aborted;
    private Throwable exception;

    public JobProcessor(JobRunner runner, Job job) {
        this.runner = runner;
        this.job = job;
        this.id = System.currentTimeMillis();
        this.p = new Progress();
        setStatus(Status.WAITING);
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Progress getProgress() {
        return p;
    }

    public void abort() {
        aborted = true;
        p.get().ifPresent(Step::abort);
    }

    @Override
    public void run() {

        try {
            runner.running(this);
            setStatus(Status.RUNNING);

            job.init();

            doTasks();
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

            try { job.done(); } catch(Throwable t) { }

            setStatus(Status.DONE);
            runner.done(this);
        }

    }

    private void doTasks() throws Exception {
        p.setTasks(job.getTasks());
        while (p.hasNextTask() && !aborted) {
            Task task = p.nextTask();
            job.onProgress(getProgress());

            try {
                task.init(job);

                doSteps(task);
            }
            finally {
                task.done();
            }
            p.setTasks(job.getTasks());
        }
    }

    private void doSteps(Task task) throws Exception {
        p.setSteps(task.getSteps());
        while (p.hasNextStep() && !aborted) {
            Step step = p.nextStep();
            job.onProgress(getProgress());

            try {
                step.init(task);

                step.run();

            } finally {
                step.done();
            }
            p.setSteps(task.getSteps());
        }
    }

    private void setStatus(Status status) {
        this.status = status;
        switch (status) {
            case CREATED:
                break;

            case WAITING:
                break;

            case RUNNING:
                job.onInit();
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
