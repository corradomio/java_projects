package jext.sourcecode.project.lfm.csharp;

import jext.configuration.Configuration;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.csharp.CSharpLibraryFinder;
import jext.sourcecode.project.csharp.NuGetDownloader;
import jext.sourcecode.project.lfm.DownloaderConfiguration;
import jext.sourcecode.project.lfm.LanguageFinderConfiguration;

import java.io.File;
import java.util.List;

public class CSharpFinderConfiguration extends LanguageFinderConfiguration {

    @Override
    public void configure(Configuration configuration) {
        super.configure(configuration);
    }

    @Override
    protected DownloaderConfiguration newDownloaderConfiguration(String name) {
        return new NuGetConfiguration();
    }

    @Override
    public LibraryFinder createFinder() {
        CSharpLibraryFinder lfinder = new CSharpLibraryFinder();

        libraries.forEach((lname, lconfig) -> {
            List<File> files = lconfig.getFiles();
            String version = lconfig.getVersion();
            lfinder.setNamedLibrary(lname, version, files);
        });

        configureDownloader(lfinder.getDownloader());

        return lfinder;
    }

    private void configureDownloader(LibraryDownloader ld) {
        NuGetDownloader downloader = (NuGetDownloader) ld;
        DownloaderConfiguration dconfig = getDownloaderConfiguration(ld.getName());
        downloader.setDownloadDirectory(dconfig.getDownloadDirectory());
        downloader.setDownloadTimeout(dconfig.getDownloadTimeout());
        downloader.setCheckTimeout(dconfig.getCheckTimeout());
        downloader.setParallelDownloads(dconfig.getParallelDownloads());

        dconfig.getRepositories().forEach(drepo -> {
            downloader.addRepository(drepo.getUrl());
        });

        dconfig.getLocalDirectories().forEach(localDir -> {
            downloader.addLocalDirectory(localDir);
        });

        downloader.initialize();
    }

}
