package jext.util.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ParallelException extends jext.lang.RuntimeException {

    private List<Throwable> throwables = new ArrayList<>();
    Set<String> messages = new HashSet<>();

    public ParallelException() {

    }

    boolean isEmpty() {
        return throwables.isEmpty();
    }

    public void add(Throwable t) {
        String message = t.getMessage();
        if (message == null)
            message = t.getClass().getName();

        throwables.add(t);
        messages.add(message);
    }

    public List<Throwable> getExceptions() {
        return throwables;
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
