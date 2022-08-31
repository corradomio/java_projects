package jext.sourcecode.project.lfm.python;

import jext.configuration.Configuration;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.lfm.DownloaderConfiguration;
import jext.sourcecode.project.lfm.LanguageFinderConfiguration;
import jext.sourcecode.project.python.PyPiDownloader;
import jext.sourcecode.project.python.PythonLibraryFinder;

import java.io.File;

public class PythonFinderConfiguration extends LanguageFinderConfiguration {

    @Override
    public void configure(Configuration configuration) {
        super.configure(configuration);
    }

    @Override
    protected DownloaderConfiguration newDownloaderConfiguration(String name) {
        return new PipConfiguration();
    }

    @Override
    public LibraryFinder createFinder() {
        PythonLibraryFinder lfinder = new PythonLibraryFinder();

        libraries.forEach((lname, lconfig) -> {
            File file = lconfig.getFile();
            lfinder.setNamedLibrary(lname, file);
        });

        configureDownloader(lfinder.getDownloader());

        return lfinder;
    }

    private void configureDownloader(LibraryDownloader ld) {
        PyPiDownloader downloader = (PyPiDownloader) ld;
        DownloaderConfiguration dconfig = getDownloaderConfiguration(ld.getName());

        downloader.setDownloadDirectory(dconfig.getDownloadDirectory());
        downloader.setDownloadTimeout(dconfig.getDownloadTimeout());
        downloader.setCheckTimeout(dconfig.getCheckTimeout());
        downloader.setParallelDownloads(dconfig.getParallelDownloads());

        dconfig.getRepositories().forEach(drepo -> {
            downloader.addRepository(drepo.getName(), drepo.getUrl());
        });

        dconfig.getLocalDirectories().forEach(localDir -> {
            downloader.addLocalDirectory(localDir);
        });

        downloader.initialize();
    }
}
