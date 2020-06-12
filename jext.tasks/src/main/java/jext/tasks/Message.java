package jext.tasks;

import java.util.Date;

public class Message {

    public enum Level {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

    private Date timestamp;
    private String message;
    private Level level;


    public Message(String message) {
        this.timestamp = new Date();
        this.level = Level.INFO;
        this.message = message;

        assert message != null;
    }

    public Message(Level level, String message) {
        this(message);
        this.level = level;
    }

    public Level getLevel() { return level;}

    public Date getTimestamp() { return timestamp; }

    public String getMessage() { return message; }
}
