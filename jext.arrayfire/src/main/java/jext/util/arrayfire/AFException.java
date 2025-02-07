package jext.util.arrayfire;

public class AFException extends RuntimeException {

    public AFException(String message) {
        super(message);
    }

    public AFException(int code) {
        super(String.valueOf(code));
    }
}
