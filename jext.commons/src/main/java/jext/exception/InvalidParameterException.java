package jext.exception;

public class InvalidParameterException  extends RuntimeException {

    public InvalidParameterException(String parameter, String message) {
        super(String.format("%s: %s", parameter, message));
    }
}
