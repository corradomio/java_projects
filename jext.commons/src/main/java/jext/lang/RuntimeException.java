package jext.lang;

import java.lang.reflect.Field;

public class RuntimeException extends java.lang.RuntimeException {

    public RuntimeException() {
        super();
    }

    public RuntimeException(String message) {
        super(message);
    }

    public RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeException(Throwable cause) {
        super(cause);
    }

    protected RuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void setMessage(String message) {
        try {
            if (message == null)
                message = getClass().getName();
            Field detailedMessage = Throwable.class.getDeclaredField("detailMessage");
            detailedMessage.setAccessible(true);
            detailedMessage.set(this, message);
        } catch (NoSuchFieldException | IllegalAccessException e) { }
    }

    public void setCause(Throwable t) {
        try {
            if (t == null) return;
            Field cause = Throwable.class.getDeclaredField("cause");
            cause.setAccessible(true);
            cause.set(this, t);
        } catch (NoSuchFieldException | IllegalAccessException e) { }
    }
}
