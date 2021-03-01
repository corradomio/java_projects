package jext.util.concurrent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ParallelException extends jext.lang.RuntimeException {

    private static class LinkedCauseException extends jext.lang.RuntimeException {
        LinkedCauseException(Throwable t) {
            super(t);
        }
    }

    private LinkedCauseException lce;
    private Set<String> messages = new HashSet<>();

    public ParallelException() {

    }

    boolean isEmpty() {
        return lce == null;
    }

    public void add(Throwable t) {
        String message = t.getMessage();
        if (message == null)
            message = t.getClass().getName();
        messages.add(message);

        LinkedCauseException lce = new LinkedCauseException(t);
        if (this.lce == null) {
            this.lce = lce;
            this.setCause(lce);
        }
        else {
            this.lce.setCause(lce);
            this.lce = lce;
        }
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
}
