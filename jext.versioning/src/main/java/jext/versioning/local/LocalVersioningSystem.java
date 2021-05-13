package jext.versioning.local;

import jext.io.filters.FalseFileFilter;
import jext.io.filters.WildcardFileFilter;
import jext.net.URL;
import jext.util.FileUtils;
import jext.util.StringUtils;
import jext.versioning.AbstractVersioningSystem;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Properties;

public class LocalVersioningSystem extends AbstractVersioningSystem {

    private static final String EXCLUDE = "exclude";
    private FileFilter excludeFilter;
    private File sourceDirectory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LocalVersioningSystem(String surl, Properties properties) {
        super(surl, properties);

        URL url = new URL(surl);
        String excludePatterns = properties.getProperty(EXCLUDE, StringUtils.empty());

        if (excludePatterns.isEmpty())
            this.excludeFilter = FalseFileFilter.INSTANCE;
        else {
            List<String> patterns = StringUtils.split(excludePatterns, ",");
            this.excludeFilter = new WildcardFileFilter(patterns);
        }

        this.sourceDirectory = new File(url.getPath());
    }

    @Override
    public boolean exists(File localDirectory) {
        return super.exists(localDirectory);
    }

    @Override
    public void checkout(File localDirectory) {
        // copy the remote directory inside the local directory
        // (if the local directory doesn't exist, it will be created
        FileUtils.copy(sourceDirectory, localDirectory, excludeFilter);
    }

    @Override
    public void update(File localDirectory) {
        FileUtils.align(sourceDirectory, localDirectory, excludeFilter);
    }

}
