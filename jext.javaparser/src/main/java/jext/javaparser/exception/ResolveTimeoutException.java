package jext.javaparser.exception;

public class ResolveTimeoutException extends RuntimeException {

    public ResolveTimeoutException() {
        super("Timeout");
    }
}
