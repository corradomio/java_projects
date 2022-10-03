package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.lang.OperatingSystemUtils;
import jext.sourcecode.project.LibraryFinder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class LanguageFinderConfiguration implements LibraryFinderConfiguration {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected HierarchicalConfiguration configuration;
    protected DownloaderConfiguration downloaderConfiguration;
    // keep the order of insertion
    protected Map<String, LibraryConfiguration> libraries = new LinkedHashMap<>();
    protected String language;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected LanguageFinderConfiguration() {

    }

    // ----------------------------------------------------------------------
    // Property
    // ----------------------------------------------------------------------

    @Override
    public String getLanguage() {
        return language;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public void configure(Configuration configuration) {
        this.configuration = (HierarchicalConfiguration) configuration;
        this.language = configuration.getString("@name");

        // for now we support a single downloader
        Configuration downloaderConfiguration = this.configuration.configurationAt("downloader");
        String name = downloaderConfiguration.getString("@name", "default");
        this.downloaderConfiguration = newDownloaderConfiguration(name);
        this.downloaderConfiguration.configure(downloaderConfiguration);

        // configure the list of libraries (NEW syntax: <library .../>)
        this.configuration.configurationsAt("library")
            .forEach(this::configureLibrary);

        // configure the list of libraries (OLD syntax: <path .../>)
        this.configuration.configurationsAt("path")
            .forEach(this::configureLibrary);
    }

    protected void configureLibrary(Configuration configuration) {
        String os = OperatingSystemUtils.getPlatform();
        LibraryConfiguration libraryConfiguration = new LibraryConfiguration();
        libraryConfiguration.configure(configuration);

        List<String> names = libraryConfiguration.getNames();
        if (names.isEmpty())
            return;

        // retrieve the first name & version
        String name = names.get(0);
        String version = libraryConfiguration.getVersion();
        String platform = libraryConfiguration.getPlatform();

        // library configured for a different Operating System
        if (!platform.isEmpty() && !platform.equals(os))
            return;

        // register the library using the first name
        this.libraries.put(name, libraryConfiguration);

        // register the remaining names as references to the first name
        for(int i=1; i<names.size(); ++i)
            this.libraries.put(names.get(i), LibraryConfiguration.ref(names.get(i), version, name));
    }

    public DownloaderConfiguration getDownloaderConfiguration(String name) {
        return downloaderConfiguration;
    }

    protected abstract DownloaderConfiguration newDownloaderConfiguration(String name);

    @Override
    public abstract LibraryFinder createFinder();

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
