package jext.tasks;

public class TaskRequirements {

    private long memoryRequirementsInBytes;
    private long timeRequirementsInSeconds;

    public TaskRequirements() {
        this(0, 0);
    }

    public TaskRequirements(long memoryRequirementsInBytes) {
        this(memoryRequirementsInBytes, 0);
    }

    public TaskRequirements(long memoryRequirementsInBytes, long timeRequirementsInSecond) {
        this.memoryRequirementsInBytes = memoryRequirementsInBytes;
        this.timeRequirementsInSeconds = timeRequirementsInSecond;
    }

    public long getMemoryRequirements() {
        return memoryRequirementsInBytes;
    }

    public void add(TaskRequirements req) {
        memoryRequirementsInBytes += req.memoryRequirementsInBytes;
        timeRequirementsInSeconds += req.timeRequirementsInSeconds;
    }

    public TaskRequirements max(TaskRequirements req) {
        if (req == null)
            return this;

        // if (memoryRequirementsInBytes >= req.memoryRequirementsInBytes && timeRequirementsInSeconds >= req.timeRequirementsInSeconds)
        //     return this;
        // if (memoryRequirementsInBytes <= req.memoryRequirementsInBytes && timeRequirementsInSeconds <= req.timeRequirementsInSeconds)
        //     return req;

        TaskRequirements max = new TaskRequirements();
        max.memoryRequirementsInBytes = Math.max(this.memoryRequirementsInBytes, req.memoryRequirementsInBytes);
        max.timeRequirementsInSeconds = Math.max(this.timeRequirementsInSeconds, req.timeRequirementsInSeconds);
        return max;
    }

}
