package jext.vfs;

public class VFileSystemException extends RuntimeException {

    public VFileSystemException(String msg) {
        super(msg);
    }

    public VFileSystemException(String msg, Exception e) {
        super(msg, e);
    }
}
