package jext.versioning;

public class VersioningSystemException extends RuntimeException {

    public VersioningSystemException() {
        super();
    }

    public VersioningSystemException(String message) {
        super(message);
    }

    public VersioningSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public VersioningSystemException(Throwable cause) {
        super(cause);
    }

    protected VersioningSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
