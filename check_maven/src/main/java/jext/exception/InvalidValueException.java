package jext.exception;

public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String var, String value) {
        super(String.format("%s=%s", var, value));
    }
}
