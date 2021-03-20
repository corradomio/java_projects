package jext.exception;

import jext.lang.RuntimeException;

public class AbortedException extends RuntimeException {

    public AbortedException() {
        super();
    }

    public AbortedException(String message) {
        super(message);
    }

    public AbortedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbortedException(Throwable cause) {
        super(cause);
    }

    protected AbortedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
