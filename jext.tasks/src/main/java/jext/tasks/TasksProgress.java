package jext.tasks;

public class TasksProgress extends Progress {

    private ProgressStatus group = new ProgressStatus();

    public ProgressStatus getTasks() {
        return group;
    }

    public void startGroup(int totalTasks) {
        group.setTotal(totalTasks);
    }

    public void startTask(String message) {
        group.update(message, 1);
    }

    public void setTaskProgress(Task task) {
        this.works = task.getProgress().getWorks();
        this.steps = task.getProgress().getSteps();
    }

    public void endGroup() {
        group.completed();
    }

}
