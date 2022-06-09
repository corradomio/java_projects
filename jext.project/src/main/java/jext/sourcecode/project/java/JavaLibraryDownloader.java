package jext.sourcecode.project.java;

import jext.maven.MavenDownloader;
import jext.sourcecode.project.LibraryDownloader;

public class JavaLibraryDownloader extends MavenDownloader implements LibraryDownloader {

    @Override
    public String getName() {
        return "maven";
    }

    /**
     * Create a clone of the current downloader, useful to add new custom repositories
     */
    public JavaLibraryDownloader newDownloader() {
        JavaLibraryDownloader md = new JavaLibraryDownloader();
        md.setDownloadDirectory(downloadDir);
        md.addRepositories(repoUrls);
        md.addLocalDirectories(localDirs);
        md.setDownloadTimeout(downloadTimeout);
        md.setCheckTimeout(checkTimeout);
        md.setParallelDownloads(parallelDownloads);
        md.initialize();

        return md;
    }

    @Override
    public void addRepository(String name, String repoUrl) {
        super.addRepository(repoUrl);
    }

}
