package jext.lang;

public class RuntimeException extends java.lang.RuntimeException {

    private String message;
    private Throwable cause;

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
        // if (message == null)
        //     message = getClass().getName();
        this.message = message;
    }

    public String getMessage() {
        return message != null ? message : super.getMessage();
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause != null ? cause : super.getCause();
    }
}
