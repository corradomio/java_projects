package jext.sourcecode.project.lfm.java;

import jext.configuration.Configuration;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.java.JavaLibraryDownloader;
import jext.sourcecode.project.java.JavaLibraryFinder;
import jext.sourcecode.project.lfm.DownloaderConfiguration;
import jext.sourcecode.project.lfm.LanguageFinderConfiguration;

import java.io.File;

public class JavaFinderConfiguration extends LanguageFinderConfiguration {

    @Override
    public void configure(Configuration configuration) {
        super.configure(configuration);
    }

    @Override
    public LibraryFinder createFinder() {
        JavaLibraryFinder lfinder = new JavaLibraryFinder(language);

        // register 'not ref' libraries
        libraries.forEach((lname, lconfig) -> {
            File file = lconfig.getFile();
            String version = lconfig.getVersion();
            String ref = lconfig.getRef();

            if (!ref.isEmpty())
                return;

            lfinder.setNamedLibrary(lname, version, file);
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

            lfinder.setNamedLibrary(lname, version, refLibrary.getFile());
        });

        configureDownloader(lfinder.getDownloader());

        return lfinder;
    }

    private void configureDownloader(LibraryDownloader ld) {
        JavaLibraryDownloader downloader = (JavaLibraryDownloader) ld;
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

    @Override
    protected DownloaderConfiguration newDownloaderConfiguration(String name) {
        return new MavenConfiguration();
    }

}
