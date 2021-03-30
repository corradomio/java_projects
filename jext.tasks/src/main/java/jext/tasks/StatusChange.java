package jext.tasks;

public class StatusChange {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private TaskStatus status;
    private long timestamp;
    private String message;

    public StatusChange(TaskStatus status, long timeout) {
        this(status, timeout, null);
    }

    public StatusChange(TaskStatus status, long timestamp, String message) {
        if (message == null)
            message = status.toString();

        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public TaskStatus getStatus() { return status; }

    public long getTimestamp() { return timestamp; }

    public String getMessage() { return message; }
}
