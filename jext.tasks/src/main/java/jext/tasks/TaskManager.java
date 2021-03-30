package jext.tasks;

public interface TaskManager {

    // boolean isTaskRunning(String repositoryName, String projectName, String modelType);

    void runningTask(Task task);

    void finishedTask(Task task);
}
