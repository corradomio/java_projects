package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryDownloader;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NuGetDownloader implements LibraryDownloader {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(NuGetDownloader.class);

    protected final Set<String> repoUrls = new HashSet<>();
    protected final List<File> localDirs = new ArrayList<>();
    protected File downloadDir = new File(".m2/repository");
    protected long downloadTimeout = 500;
    protected long checkTimeout = 24*60*60;
    protected int parallelDownloads = 5;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NuGetDownloader() {

    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public void setDownloadDirectory(File downloadDir) {
        this.downloadDir = downloadDir;
    }

    // --

    public void addRepository(String repoUrl) {
        // remove the last "/"
        if (repoUrl.endsWith("/"))
            repoUrl = StringUtils.substring(repoUrl,0, -1);
        // check the protocol: for now it supports only "http(s)://"
        if (repoUrl.startsWith("http"))
            this.repoUrls.add(repoUrl);
        else
            logger.errorf("Unsupported Maven repository %s", repoUrl);
    }

    public void addRepositories(Collection<String> repoUrls) {
        repoUrls.forEach(this::addRepository);
    }

    public void addLocalDirectory(File localDir) {
        this.localDirs.add(localDir);
    }

    public void addLocalDirectories(Collection<File> localDirs) {
        localDirs.forEach(this::addLocalDirectory);
    }

    public void setDownloadTimeout(long millis) {
        this.downloadTimeout = millis;
    }

    /**
     * Sometimes a server doesn't works. In this case it is created a 'flag' file (*.invalid)
     * to skip another check the next time. However the flag is not forever. It has a expiration time
     * (for default 24 hours). After this time, it will be deleted.
     */
    public void setCheckTimeout(long seconds) {
        this.checkTimeout = seconds;
    }

    /** Parallel downloads permitted */
    public void setParallelDownloads(int parallelDownloads) {
        this.parallelDownloads = parallelDownloads;
    }

    /** To call at the configuration end */
    public void initialize() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return "nuget";
    }

    @Override
    public void addRepository(String name, String url) {

    }

    @Override
    public LibraryDownloader newDownloader() {
        return this;
    }

    @Override
    public void checkArtifacts(Collection<MavenCoords> artifacts, boolean b, boolean parallel) {

    }
}
