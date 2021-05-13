package jext.versioning;

import jext.io.filters.FalseFileFilter;
import jext.logging.Logger;
import jext.net.URL;
import jext.util.FileUtils;
import jext.versioning.util.Authentication;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

public abstract class AbstractVersioningSystem implements VersioningSystem {

    protected URL url;
    protected Properties properties = new Properties();
    protected FileFilter localExclude;
    protected Logger logger;

    protected AbstractVersioningSystem(String surl, Properties properties) {
        this.url = new URL(surl);
        this.properties.putAll(properties);
        this.localExclude = FalseFileFilter.INSTANCE;
        this.logger = Logger.getLogger(getClass());
    }

    @Override
    public boolean exists(File localDirectory) {
        if (!localDirectory.exists() || !localDirectory.isDirectory())
            return false;
        String[] content = localDirectory.list();
        return content != null && content.length > 1;
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

    // ----------------------------------------------------------------------

    @Override
    public void copy(File localDirectory, File savedDirectory) {
        FileUtils.copy(localDirectory, savedDirectory, copyExclude());
    }

    @Override
    public void delete(File localDirectory) {
        FileUtils.delete(localDirectory, localExclude);
    }

    // ----------------------------------------------------------------------

    protected FileFilter copyExclude() {
        return localExclude;
    }

    protected Authentication getAuthentication() {
        return new Authentication(properties);
    }
}
