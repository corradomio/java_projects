package jext.sourcecode.resources.java;

import jext.maven.MavenDownloader;
import jext.sourcecode.project.LibraryDownloader;

public class JavaLibraryDownloader extends MavenDownloader implements LibraryDownloader {

    public MavenDownloader addRepository(String url) {
        return super.addRepository(url);
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
