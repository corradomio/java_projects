package jext.igrapht;

/** An error reported by the native igraph library. */
public class IGraphException extends RuntimeException {
    private final int code;

    public IGraphException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
