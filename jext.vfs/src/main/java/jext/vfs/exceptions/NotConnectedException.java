package jext.vfs.exceptions;

import jext.vfs.VFileSystemException;

public class NotConnectedException extends VFileSystemException {

    public NotConnectedException() {
        super("Call 'connect()' before to access the vfs");
    }

    public NotConnectedException(Exception e) {
        super("Call 'connect()' before to access the vfs", e);
    }
}
