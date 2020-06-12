package jext.tasks;

public class NullTaskManager implements TaskManager {

    private static NullTaskManager INSTANCE = new NullTaskManager();

    public static TaskManager getInstance() { return INSTANCE; }

    private NullTaskManager() { }

    @Override
    public void runningTask(Task task) {

    }

    @Override
    public void finishedTask(Task task) {

    }

}
