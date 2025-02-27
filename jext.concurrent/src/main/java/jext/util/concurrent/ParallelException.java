package jext.util.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ParallelException extends java.lang.RuntimeException {

    // private static class LinkedCauseException extends java.lang.RuntimeException {
    //     private Throwable cause;
    //
    //     LinkedCauseException(Throwable t) {
    //         super(t);
    //     }
    //
    //     void setCause(Throwable cause) {
    //         this.cause = cause;
    //     }
    //
    //     @Override
    //     public Throwable getCause() {
    //         return cause != null ? cause : super.getCause();
    //     }
    // }

    // private LinkedCauseException lce;

    private final Set<String> messages = new HashSet<>();
    private final List<Throwable> causes = new ArrayList<>();

    public ParallelException() {
        super("ParallelException");
    }

    boolean isEmpty() {
        return messages.isEmpty();
    }

    void add(Throwable t) {
        String message = t.getMessage();
        if (message == null)
            message = t.getClass().getName();
        messages.add(message);
        causes.add(t);
    }

    public String getMessage() {
        if (messages.isEmpty())
            return super.getMessage();
        if (messages.size() == 1)
            return messages.iterator().next();

        Iterator<String> it = messages.iterator();
        StringBuilder sb = new StringBuilder(it.next());
        while (it.hasNext()) {
            sb.append(" | ").append(it.next());
        }
        return sb.toString();
    }

    public List<Throwable> getCauses() {
        return causes;
    }
}
