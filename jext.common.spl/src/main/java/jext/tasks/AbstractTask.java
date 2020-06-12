package jext.tasks;

import jext.logging.Logger;
import jext.util.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public abstract class AbstractTask implements Task, TaskStatusListener {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected Logger logger;

    // task type
    private String type;

    // task id
    private String id;

    // task status
    private TaskStatus previousStatus = TaskStatus.CREATED;
    private TaskStatus status = TaskStatus.CREATED;

    // task properties
    private Parameters parameters = new Parameters();

    // current message
    private String message = "";

    // catched exception
    private Throwable cathedException;

    // progress status
    private Progress progress = new Progress();

    // user has request the task abort
    protected boolean aborted;

    // ----------------------------------------------------------------------
    // Task Manager

    // set when the task is inserted in the tasksmanager queue
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

        Listeners addListeners(List<TaskStatusListener> llist) {
            listeners.addAll(llist);
            return this;
        }

        void fireStatusChanged(TaskStatus prevStatus) {
            for (TaskStatusListener l : listeners) {
                try {
                    l.onStatusChanged(prevStatus,AbstractTask.this);
                }
                catch(Throwable t) {
                    logger.error(t);
                }
            }
        }

        void fireProgressChanged() {
            for (TaskStatusListener l : listeners) {
                try {
                    l.onProgressChanged(AbstractTask.this);
                }
                catch(Throwable t) {
                    logger.error(t);
                }
            }
        }

        void fireDone() {
            for (TaskStatusListener l : listeners) {
                try {
                    l.onDone(AbstractTask.this);
                }
                catch(Throwable t) {
                    logger.error(t);
                }
            }
        }
    }

    private Listeners listeners;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public AbstractTask() {
        this.id = TaskManagerService.newTaskId();
        this.status = TaskStatus.CREATED;
        this.type = getClass().getSimpleName();
        this.logger = Logger.getLogger(String.format("jext.tasks.Task.%s.%s", type, id));

        this.listeners = new Listeners().addListener(this);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getId() { return id; }

    public String getType() { return type; }

    public TaskStatus getStatus() { return status; }

    public TaskStatus getPreviousStatus() { return previousStatus; }

    public String getMessage() { return message; }

    public Progress getProgress() { return progress; }

    public Parameters getParameters() {
        return parameters;
    }

    public AbstractTask setParameters(Parameters params) {
        this.parameters.putAll(params);
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    //
    // NOT implement THIS method in the derived classes
    // Implements: 'process()'
    //
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
        catch(Throwable t) {
            cathedException = t;
            logger.error(t, t);

            if (aborted)
                setStatus(TaskStatus.ABORTED, t.toString());
            else
                setStatus(TaskStatus.FAILED, t.toString());
        }
        finally {
            // done();
            TaskStatus prevStatus = getStatus();
            setStatus(TaskStatus.DONE, prevStatus.toString());
            manager.finishedTask(this);
            progress.done();
            listeners.fireDone();
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
    public final void abort() {
        aborted = true;
        //setStatus(TaskStatus.ABORTED, "Aborted by user");
        onAbort();
    }

    protected abstract void onAbort();

    // ----------------------------------------------------------------------
    // Support
    // ----------------------------------------------------------------------

    // There are two type of messages:
    //
    //      messagef:   used to set prepare the CURRENT message: for example the status
    //                  of the download (10% ... 20% ...) or something similar
    //                  to the progress bar
    //
    //                  messagef is also logged but using 'debugft': a temporized
    //                  (in general each 3 seconds) formatted DEBUG message
    //

    protected void messagef(String message, Object... args) {
        String msg = String.format(message, args);

        this.message = msg;
        this.logger.debugft(msg);
    }

    // ----------------------------------------------------------------------
    // Listeners handling
    // ----------------------------------------------------------------------

    public void addListener(TaskStatusListener listener) {
        listeners.addListener(listener);
    }

    public void addListeners(List<TaskStatusListener> llist) {
        listeners.addListeners(llist);
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
        assert message != null;

        this.previousStatus = this.status;
        this.status = newStatus;
        //statusChanges.add(new StatusChange(status, message));

        logger.infof("Status %s -> %s: %s",
            previousStatus.toString(),
            status.toString(),
            message);

        // onStatusChanged(previousStatus);
        listeners.fireStatusChanged(previousStatus);
    }

    // ----------------------------------------------------------------------
    // TaskStatusListener callbacks
    // ----------------------------------------------------------------------

    public void onStatusChanged(TaskStatus prevStatus, Task task) {
        assert this == task;
        onStatusChanged(prevStatus);
    }

    public void onDone(Task task) {
        assert this == task;
        onDone();
    }

    public void onProgressChanged(Task task) {
        assert this == task;
        onProgressChanged();
    }

    // ----------------------------------------------------------------------
    // Events
    // ----------------------------------------------------------------------

    /**
     * Called when the status of the
     * @param previousStatus
     */
    private void onStatusChanged(TaskStatus previousStatus) {
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
                onFailed(cathedException);
            default:
                break;
        }
    }

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
    public void waiting(TaskManager manager, Future<?> future) {
        this.future = future;
        this.manager = manager;

        setStatus(TaskStatus.WAITING, "Waiting");
    }

    // Used in the code to wait explicitely for the task termination
    public void waitForCompletion(long timeout, TimeUnit timeUnit) {
        try {
            future.get(timeout, timeUnit);
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    // Progress handling
    // ----------------------------------------------------------------------
    // The job is composed of n tasks and each task of k steps of work
    //
    //      start(nWorks)
    //          startWork(nSteps)
    //              update(stepsDone)
    //              setStepsDone(stepsDone, totalSteps)
    //          endWord()
    //      done()

    protected void start(int totalWorks) {
        progress.start(totalWorks);
        listeners.fireProgressChanged();
    }

    protected void startWork(String message, int totalSteps) {
        messagef(message);
        progress.startWork(totalSteps);
        listeners.fireProgressChanged();
    }

    protected void update(int stepsDone) {
        progress.update(stepsDone);
        listeners.fireProgressChanged();
    }

    protected void update(String message, int stepsDone) {
        messagef(message);
        progress.update(stepsDone);
        listeners.fireProgressChanged();
    }

    protected void setStepsDone(int stepsDone, int totalSteps) {
        progress.setStepsDone(stepsDone, totalSteps);
        listeners.fireProgressChanged();
    }

    protected void endWork() {
        progress.endWork();
        listeners.fireProgressChanged();
    }

    protected void done() {
        progress.done();
        listeners.fireProgressChanged();
    }

    // ----------------------------------------------------------------------
    // Deprecated methods
    // ----------------------------------------------------------------------
    // The name 'task' referred to a work is in conflict with the term 'task'
    // used in the 'Task' and 'ProjectTask' interfaces

    /**
     * @deprecated
     * Uses 'startWork'
     */
    @Deprecated
    protected void startTask(String message, int totalSteps) {
        startWork(message, totalSteps);
    }

    /**
     * @deprecated
     * Uses 'endWork'
     */
    @Deprecated
    protected void endTask() {
        endWork();
    }

    /**
     * @deprecated
     * Uses 'setStepDone'
     */
    @Deprecated
    protected void setWorkDone(int stepsDone, int totalSteps) {
        setStepsDone(stepsDone, totalSteps);
    }

}
