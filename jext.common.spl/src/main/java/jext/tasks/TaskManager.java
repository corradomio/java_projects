package jext.tasks;

public interface TaskManager {

    void runningTask(Task task);

    void finishedTask(Task task);
}
