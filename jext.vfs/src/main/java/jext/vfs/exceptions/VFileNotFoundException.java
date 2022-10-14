package jext.vfs.exceptions;

import jext.vfs.VFileSystemException;

public class VFileNotFoundException extends VFileSystemException {
    public VFileNotFoundException(String msg) {
        super(msg);
    }
}
