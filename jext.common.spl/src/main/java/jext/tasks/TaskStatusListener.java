package jext.tasks;

public interface TaskStatusListener {

    void onStatusChanged(TaskStatus prevStatus, Task task);

    void onDone(Task task);

    void onProgressChanged(Task task);
}
