package jext.versioning.local;

import jext.io.filters.FileFilters;
import jext.io.filters.WildcardFileFilter;
import jext.util.FileUtils;
import jext.util.StringUtils;
import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Properties;

public class LocalVersioningSystem extends AbstractVersioningSystem {

    private static final String DEFAULT_EXCLUDES = ".*";
    private static final String EXCLUDE = "exclude";
    private FileFilter excludeFilter;
    private File sourceDirectory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LocalVersioningSystem(String surl, Properties properties, File localDirectory) {
        super(surl, properties, localDirectory);
        sourceDirectory = new File(url.getPath());
        composeExcludeFilter();
    }

    /**
     * Compose the filter used to exclude some files.
     * It is composed by the parameter "exclude", and the content of
     * the file ".fileignore"
     */
    private void composeExcludeFilter() {
        File ignoreFile = getIgnoreFile();
        List<String> ignorePatterns = FileUtils.toStrings(ignoreFile);
        List<String> excludePatterns = StringUtils.split(properties.getProperty(EXCLUDE, StringUtils.empty()), ",");

        this.excludeFilter = FileFilters.wildcards(DEFAULT_EXCLUDES);

        if (excludePatterns.isEmpty() && ignorePatterns.isEmpty())
            ;
        else if (ignorePatterns.isEmpty()) {
            this.excludeFilter = FileFilters.or(
                this.excludeFilter,
                new WildcardFileFilter(excludePatterns)
            );
        }
        else if (excludePatterns.isEmpty()) {
            this.excludeFilter = FileFilters.or(
                this.excludeFilter,
                new WildcardFileFilter(ignorePatterns)
            );
        }
        else {
            this.excludeFilter = FileFilters.or(
                this.excludeFilter,
                new WildcardFileFilter(excludePatterns),
                new WildcardFileFilter(ignorePatterns)
            );
        }
    }

    @Override
    public void checkout() {
        if (!sourceDirectory.exists())
            throw new VersioningSystemException("Source directory " + sourceDirectory + " not existent");

        // copy the source directory inside the local directory
        // if the local directory doesn't exist, it will be created
        FileUtils.copy(sourceDirectory, localDirectory, excludeFilter);
    }

    @Override
    public void update() {
        if (!sourceDirectory.exists())
            throw new VersioningSystemException("Source directory " + sourceDirectory + " not existent");

        FileUtils.align(sourceDirectory, localDirectory, excludeFilter);
    }

    @Override
    public void commit() {
        if (!sourceDirectory.exists())
            throw new VersioningSystemException("Source directory " + sourceDirectory + " not existent");
    }

    @Override
    public void delete() {
        super.delete();
    }

}
