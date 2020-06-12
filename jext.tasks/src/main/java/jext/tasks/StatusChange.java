package jext.tasks;

import java.util.Date;

public class StatusChange {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------


    private Date timestamp;
    private TaskStatus status;
    private String message;

    public StatusChange(TaskStatus status, String message) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;

        assert message != null;
    }

    public Date getTimestamp() { return timestamp; }

    public TaskStatus getStatus() { return status; }

    public String getMessage() { return message; }
}
