package jext.sourcecode.project.java;

import jext.maven.MavenDownloader;
import jext.sourcecode.project.LibraryDownloader;

public class JavaLibraryDownloader extends MavenDownloader implements LibraryDownloader {

    public JavaLibraryDownloader addRepository(String url) {
        super.addRepository(url);
        return this;
    }

    /**
     * Create a clone of the current downloader, useful to add new custom repositories
     */
    public JavaLibraryDownloader newDownloader() {
        MavenDownloader md = new JavaLibraryDownloader()
            .setDownloadDirectory(downloadDir)
            .addRepositories(repoUrls)
            .addLocalDirectories(localDirs)
            .setDownloadTimeout(downloadTimeout)
            .setCheckTimeout(checkTimeout)
            .setParallelDownloads(parallelDownloads)
            .initialize()
            ;

        return (JavaLibraryDownloader) md;
    }

}