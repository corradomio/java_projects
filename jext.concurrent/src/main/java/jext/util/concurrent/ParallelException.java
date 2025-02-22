package jext.util.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ParallelException extends jext.lang.RuntimeException {

    private static class LinkedCauseException extends jext.lang.RuntimeException {
        LinkedCauseException(Throwable t) {
            super(t);
        }
    }

    private LinkedCauseException lce;
    // private final List<Throwable> causes = new ArrayList<>();
    private final Set<String> messages = new HashSet<>();

    public ParallelException() {

    }

    boolean isEmpty() {
        return messages.isEmpty();
    }

    public void add(Throwable t) {
        String message = t.getMessage();
        if (message == null)
            message = t.getClass().getName();
        messages.add(message);
        // causes.add(t);

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
