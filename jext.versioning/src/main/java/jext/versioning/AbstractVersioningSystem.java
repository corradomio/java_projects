package jext.versioning;

import jext.net.URL;

import java.io.File;
import java.util.Properties;

public abstract class AbstractVersioningSystem implements VersioningSystem {

    protected URL url;
    protected Properties properties;

    protected AbstractVersioningSystem(Properties properties) {
        this.url = new URL(properties.getProperty(VersioningSystems.URL));
        this.properties = properties;
    }

    @Override
    public boolean exists(File localDirectory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(File localDirectory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkout(File localDirectory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(File localDirectory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void commit(File localDirectory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(File savedDirectory) {
        throw new UnsupportedOperationException();
    }

}
