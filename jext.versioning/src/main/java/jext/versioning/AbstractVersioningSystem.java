package jext.versioning;

import jext.io.filters.FalseFileFilter;
import jext.logging.Logger;
import jext.net.URL;
import jext.util.FileUtils;
import jext.versioning.util.Authentication;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractVersioningSystem implements VersioningSystem {

    protected URL url;
    protected Properties properties = new Properties();
    protected FileFilter localExclude;
    protected Logger logger;
    protected File localDirectory;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected AbstractVersioningSystem(String surl, Properties properties, File localDirectory) {
        this.url = new URL(surl);
        this.properties.putAll(properties);
        this.localDirectory = localDirectory;
        this.localExclude = FalseFileFilter.INSTANCE;
        this.logger = Logger.getLogger(getClass());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean exists() {
        if (!localDirectory.exists() || !localDirectory.isDirectory())
            return false;
        String[] content = localDirectory.list();
        return content != null && content.length > 1;
    }

    @Override
    public void checkout() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void commit() {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------

    // @Override
    // public void copy(File savedDirectory) {
    //     FileUtils.copy(localDirectory, savedDirectory, copyExclude());
    // }

    @Override
    public void delete() {
        FileUtils.delete(localDirectory, localExclude);
    }

    // ----------------------------------------------------------------------

    protected FileFilter copyExclude() {
        return localExclude;
    }

    protected Authentication getAuthentication() {
        return new Authentication(properties);
    }

    // ----------------------------------------------------------------------
    // Ignore support
    // ----------------------------------------------------------------------

    private static final String[] IGNORE_NAMES = new String[]{
        ".fileignore", // (Local filesystem)
        ".gitignore", // (Git)
        ".hgignore", // (Mercurial)
        ".npmignore", // (NPM)
        ".dockerignore", // (Docker)
        ".chefignore", // (Chef)
        ".cvsignore", // (CVS)
        ".bzrignore", // (Bazaar)
        ".boringignore", // (Darcs)
        ".mtn-ignore", // (Monotone)
        " ignore-glob", // (Fossil)
        ".jshintignore", // (JSHint)
        ".tfignore", // (Team Foundation)
        ".p4ignore", // (Perforce)
        ".flooignore", // (Floobits)
        ".eslintignore", // (ESLint)
        ".cfignore", // (Cloud Foundry)
        ".jpmignore", // (Jetpack)
        ".stylelintignore", // (StyleLint)
        ".stylintignore", // (Stylint)
        ".swagger-codegen-ignore", // (Swagger Codegen)
        ".helmignore", // (Kubernetes Helm)
        ".upignore", // (Up)
        ".prettierignore", // (Prettier)
        ".ebignore", // (ElasticBeanstalk)
        ".gcloudignore" // (Google Cloud)
    };

    @Override
    public void addIgnore(String ignorePattern) {
        File ignoreFile = getIgnoreFile();
        List<String> patterns;

        // load the file content
        if (!ignoreFile.exists()) {
            patterns = new ArrayList<>();
        }
        else {
            patterns = FileUtils.toStrings(ignoreFile);
        }

        // scan if the pattern is already present
        for (String pattern : patterns)
            if (pattern.equals(ignorePattern))
                return;

        // pattern not present, add it and save the file
        patterns.add(ignorePattern);
        FileUtils.asFile(ignoreFile, patterns);
        logger.infof("Updated %s", ignoreFile.getName());
    }

    // ----------------------------------------------------------------------
    // Ignore support
    // ----------------------------------------------------------------------

    protected File getIgnoreFile() {
        return new File(localDirectory, ".fileignore");
    }
}
