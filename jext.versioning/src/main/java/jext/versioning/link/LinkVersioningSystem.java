package jext.versioning.link;

import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;

import java.io.File;
import java.util.Properties;

public class LinkVersioningSystem  extends AbstractVersioningSystem {

    private File linkDirectory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LinkVersioningSystem(String surl, Properties properties, File localDirectory) {
        super(surl, properties, localDirectory);
        linkDirectory = new File(url.getPath());
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean exists() {
        return linkDirectory.exists();
    }

    @Override
    public void checkout() throws VersioningSystemException {

    }

    @Override
    public void update() throws VersioningSystemException {

    }

    @Override
    public void commit() throws VersioningSystemException {

    }

    @Override
    public void delete() {

    }

    @Override
    public void addIgnore(String pattern) {

    }
}