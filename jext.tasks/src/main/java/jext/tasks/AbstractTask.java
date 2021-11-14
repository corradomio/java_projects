package jext.tasks;

import jext.logging.Logger;
import jext.util.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Future;


public abstract class AbstractTask implements Task, TaskStatusListener {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected Logger logger;

    // task type (a generic string)
    protected String taskType;

    // unique task id
    protected String id;

    // task status
    private TaskStatus status;
    private long timestamp;
    // current message
    private String message = "";

    private List<StatusChange> history;


    // task parameters
    protected final Parameters parameters = new Parameters();

    // progress status
    protected Progress progress = new Progress();

    // user has request the task abort
    protected boolean aborted;

    // catched exception
    private Throwable exception;

    // ----------------------------------------------------------------------
    // Task Manager

    // set when the task is inserted in the task manager queue
    private TaskManager manager = NullTaskManager.getInstance();

    // not used, for now
    private Future<?> future;

    // ----------------------------------------------------------------------
    // Task Listeners

    private class Listeners {
        List<TaskStatusListener> listeners = new ArrayList<>();

        Listeners addListener(TaskStatusListener listener) {
            listeners.add(listener);
            return this;
        }

        void fireStatusChanged(TaskStatus previousStatus) {
            for (TaskStatusListener l : listeners) {
                l.onStatusChanged(previousStatus,AbstractTask.this);
            }
        }

        void fireProgressChanged() {
            for (TaskStatusListener l : listeners) {
                l.onProgressChanged(AbstractTask.this);
            }
        }

        void fireDone() {
            for (TaskStatusListener l : listeners) {
                l.onDone(AbstractTask.this);
            }
        }
    }

    private final Listeners listeners;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public AbstractTask() {
        this.id = TaskManagerService.newTaskId();
        this.taskType = getClass().getSimpleName();

        this.logger = Logger.getLogger(String.format("jext.tasks.Task.%s.%s", taskType, id));

        this.status = TaskStatus.CREATED;
        this.timestamp = System.currentTimeMillis();

        this.history = new Stack<>();
        this.history.add(new StatusChange(this.status, this.timestamp));

        this.listeners = new Listeners().addListener(this);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() { return id; }

    @Override
    public String getType() { return taskType; }

    @Override
    public String getMessage() { return message; }

    @Override
    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public boolean isTerminated() {
        if (future == null) return false;
        return future.isCancelled() || future.isDone();
    }

    @Override
    public boolean hasRequirements() {
        return false;
    }

    @Override
    public TaskRequirements getRequirements() {
        throw new UnsupportedOperationException();
    }

    public void setParameters(Parameters params) {
        this.parameters.putAll(params);
    }

    // ----------------------------------------------------------------------
    // Progress
    // ----------------------------------------------------------------------

    public Progress getProgress() {
        return progress;
    }

    // ----------------------------------------------------------------------
    // Status
    // ----------------------------------------------------------------------

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public List<StatusChange> getStatusHistory() {
        return history;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    //
    // DO NOT implement THIS method in the derived classes
    // Implements: 'process()'
    //
    @Override
    public final void run() {
        try {
            manager.runningTask(this);

            if (!aborted) {
                setStatus(TaskStatus.RUNNING, "Processing ...");

                process();
            }

            if (aborted)
                setStatus(TaskStatus.ABORTED, "Aborted by user");
            else
                setStatus(TaskStatus.SUCCESS, "Success");
        }
        catch (Throwable t) {
            exception = t;
            logger.error(t, t);

            if (aborted)
                setStatus(TaskStatus.ABORTED, t.toString());
            else
                setStatus(TaskStatus.FAILED, t.toString());
        }
        finally {
            done();
            setStatus(TaskStatus.DONE, "Done");

            manager.finishedTask(this);
        }
    }

    //
    // The derived class MUST implement THIS method
    //
    protected abstract void process() throws Exception;

    // ----------------------------------------------------------------------
    // Abort handling
    // ----------------------------------------------------------------------

    // Request for abort
    @Override
    public final void abort() {
        aborted = true;
        onAbort();
    }

    /**
     * Executed when "abort()" is called.
     * It is used to ""abort"" the current process!
     *
     * Note: IT IS NOT THE SAME THING of "onAborted()"!!!
     */
    protected void onAbort() {

    }

    // ----------------------------------------------------------------------
    // Support
    // ----------------------------------------------------------------------

    protected void messagef(String message, Object... args) {
        String msg = String.format(message, args);

        this.message = msg;
        this.logger.debugt(msg);
    }

    // ----------------------------------------------------------------------
    // Listeners handling
    // ----------------------------------------------------------------------

    @Override
    public void addListener(TaskStatusListener listener) {
        listeners.addListener(listener);
    }

    // ----------------------------------------------------------------------
    // Status change
    // ----------------------------------------------------------------------

    /**
     * Set the new state of the task
     *
     * @param newStatus new state
     * @param message a message related to the state change
     */
    private void setStatus(TaskStatus newStatus, String message) {
        TaskStatus previousStatus = this.status;

        if (message == null)
            message = String.format("%s -> %s", previousStatus, newStatus);
        else
            message = String.format("%s -> %s: %s", previousStatus, newStatus, message);

        this.status = newStatus;
        this.timestamp = System.currentTimeMillis();
        this.history.add(new StatusChange(this.status, this.timestamp, message));

        logger.info(message);

        listeners.fireStatusChanged(previousStatus);
    }

    // ----------------------------------------------------------------------
    // TaskStatusListener callbacks
    // ----------------------------------------------------------------------

    @Override
    public void onStatusChanged(TaskStatus prevStatus, Task task) {
        assert this == task;
        switch(getStatus()) {
            case RUNNING:
                onStarted();
                break;
            case SUCCESS:
                onSuccess();
                break;
            case ABORTED:
                onAborted();
                break;
            case FAILED:
                onFailed(exception);
                break;
            case DONE:
                onDone();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDone(Task task) {
        assert this == task;
        onDone();
    }

    @Override
    public void onProgressChanged(Task task) {
        assert this == task;
        onProgressChanged();
    }

    // ----------------------------------------------------------------------
    // Events
    // ----------------------------------------------------------------------

    /**
     * Called when the task is started and BEFORE to call "process()"
     */
    protected void onStarted()  { }

    /**
     * Callsed if the task is terminated with onSuccess
     */
    protected void onSuccess() { }

    /**
     * Called if the task is terminated for usero bort
     */
    protected void onAborted() { }

    /**
     * Called if the task is terminated for exception
     * @param t
     */
    protected void onFailed(Throwable t) { }

    /**
     * Called as LAST action AFTER the task is terminated
     */
    protected void onDone() { }

    /**
     * Called on each progress status changed
     */
    protected void onProgressChanged() { }

    // ----------------------------------------------------------------------
    // Task handling
    // ----------------------------------------------------------------------

    // Used by 'Task Manager' when the task is submitted into the queue
    @Override
    public void waiting(TaskManager manager, Future<?> future) {
        this.future = future;
        this.manager = manager;

        setStatus(TaskStatus.WAITING, "Waiting");
    }

    // ----------------------------------------------------------------------
    // Progress handling
    // ----------------------------------------------------------------------
    // The job is composed of n tasks and each task of k steps of work
    //
    //      start(nWorks)
    //          startWork(nWorks)
    //              update(stepsDone)
    //              setWorkDone(stepsDone, totalSteps)
    //          [endTask()]
    //          startWork(nWorks)
    //              ...
    //          [endTask()]
    //          ...
    //      done()

    protected void start(int totalWorks) {
        messagef("Start %d works", totalWorks);
        progress.start(totalWorks);
        listeners.fireProgressChanged();
    }

    protected void startWork(String message, int totalSteps) {
        messagef(message);
        progress.startWork(message, totalSteps);
        listeners.fireProgressChanged();
    }

    protected void update(int stepsDone) {
        update("updated", stepsDone);
    }

    protected void update(String message, int stepsDone) {
        progress.update(message, stepsDone);

        ProgressStatus steps = progress.steps;
        message = String.format("%s (%d/%d)", message, steps.getCurrent(), steps.getTotal());

        messagef(message);
        listeners.fireProgressChanged();
    }

    protected void endWork() {
        messagef("endWork");
        progress.endWork();
        listeners.fireProgressChanged();
    }

    protected void done() {
        messagef("done");
        progress.done();
        listeners.fireProgressChanged();
    }

    protected void setWorkDone(int stepsDone, int totalSteps) {
        progress.setStepsDone(stepsDone, totalSteps);
        listeners.fireProgressChanged();
    }

    protected void addWorks(int nWorks) {
        progress.addWorks(nWorks);
    }

}
