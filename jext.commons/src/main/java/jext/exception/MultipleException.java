package jext.exception;

import java.util.ArrayList;
import java.util.List;

public class MultipleException extends RuntimeException {

    private List<Throwable> causes = new ArrayList<>();

    public MultipleException(String message) {
        super(message);
    }

    public void addCause(Throwable cause) {
        causes.add(cause);
    }

    public boolean isEmpty() {
        return causes.isEmpty();
    }

    public List<Throwable> getCauses() {
        return causes;
    }

    public void rethrow() {
        if (!causes.isEmpty())
            throw this;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        for (Throwable cause : causes)
            sb.append("\n    ").append(ExceptionUtils.getMessages(cause));
        return sb.toString();
    }
}
