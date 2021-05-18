package jext.versioning.util;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemException;

import java.io.File;

public class VersioningSystemWrapper implements VersioningSystem {

    private VersioningSystem vs;

    public VersioningSystemWrapper(VersioningSystem vs) {
        this.vs = vs;
    }

    @Override
    public boolean exists() {
        return vs.exists();
    }

    @Override
    public void checkout() throws VersioningSystemException {
        vs.checkout();
    }

    @Override
    public void update() throws VersioningSystemException {
        vs.update();
    }

    @Override
    public void commit() throws VersioningSystemException {
        vs.commit();
    }

    @Override
    public void delete() {
        vs.delete();
    }

    // @Override
    // public void copy(File savedDirectory) {
    //     vs.copy(savedDirectory);
    // }

    @Override
    public void addIgnore(String pattern) {
        vs.addIgnore(pattern);
    }

}
