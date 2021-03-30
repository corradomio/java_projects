package jext.tasks;

public class TaskRequirements {

    public static TaskRequirements NO_REQUIREMENTS = new TaskRequirements(0, 0);

    public static TaskRequirements noRequirements() {
        return NO_REQUIREMENTS;
    }


    private long memoryRequirementsInBytes;
    private long timeRequirementsInSeconds;

    public TaskRequirements(long memoryRequirementsInBytes) {
        this(memoryRequirementsInBytes, 0);
    }

    public TaskRequirements(long memoryRequirementsInBytes, long timeRequirementsInSecond) {
        this.memoryRequirementsInBytes = memoryRequirementsInBytes;
        this.timeRequirementsInSeconds = timeRequirementsInSecond;
    }

    public long getMemoryRequirements(long unitInBytes) {
        if (unitInBytes <= 0) unitInBytes = 1;
        return memoryRequirementsInBytes/unitInBytes;
    }
}
