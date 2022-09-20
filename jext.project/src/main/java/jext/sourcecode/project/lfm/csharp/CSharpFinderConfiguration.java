package jext.sourcecode.project.lfm.csharp;

import jext.configuration.Configuration;
import jext.sourcecode.project.Library;
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

        // register 'not ref' libraries
        libraries.forEach((lname, lconfig) -> {
            List<File> files = lconfig.getFiles();
            String version = lconfig.getVersion();
            String ref = lconfig.getRef();

            // if is a 'ref' library, skip it
            if (!ref.isEmpty())
                return;

            lfinder.setNamedLibrary(lname, version, files);
        });

        // register 'ref' libraries
        libraries.forEach((lname, lconfig) -> {
            String version = lconfig.getVersion();
            String ref = lconfig.getRef();

            // if is a 'ref' library, skip it
            if (ref.isEmpty())
                return;

            Library refLibrary = lfinder.getRTLibrary(ref);
            if (refLibrary == null)
                return;

            lfinder.setNamedLibrary(lname, version, refLibrary.getFiles());
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
            downloader.addRepository(drepo.getName(), drepo.getUrl());
        });

        dconfig.getLocalDirectories().forEach(localDir -> {
            downloader.addLocalDirectory(localDir);
        });

        downloader.initialize();
    }

}
