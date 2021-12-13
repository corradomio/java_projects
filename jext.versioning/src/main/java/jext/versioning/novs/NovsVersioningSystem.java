package jext.versioning.novs;

import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;

import java.io.File;
import java.util.Properties;

public class NovsVersioningSystem extends AbstractVersioningSystem {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NovsVersioningSystem(String surl, Properties properties, File localDirectory) {
        super(surl, properties, localDirectory);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean exists() {
        return localDirectory.exists();
    }

    @Override
    public void checkout() throws VersioningSystemException {
        if (!localDirectory.exists())
            throw new VersioningSystemException("Local directory " + localDirectory + " not existent");
    }

    @Override
    public void update() throws VersioningSystemException {
        if (!localDirectory.exists())
            throw new VersioningSystemException("Local directory " + localDirectory + " not existent");
    }

    @Override
    public void commit() throws VersioningSystemException {
        if (!localDirectory.exists())
            throw new VersioningSystemException("Local directory " + localDirectory + " not existent");
    }

    @Override
    public void delete() {

    }

    @Override
    public void addIgnore(String pattern) {

    }
}
