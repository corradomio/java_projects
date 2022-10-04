package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Version;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.sourcecode.project.csharp.libraries.NuGetLibrary;
import jext.sourcecode.project.csharp.util.DotNetNormalizer;
import jext.util.HashMap;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CSharpLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(CSharpLibraryFinder.class);

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private NuGetDownloader downloader = new NuGetDownloader();
    private Map<String, Library> runtimeLibraries = new HashMap<>();
    private Library defaultRuntimeLibrary;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        CSharpLibraryFinder lfinder = new CSharpLibraryFinder();
        lfinder.setLibraries(libraries, runtimeLibraries, defaultRuntimeLibrary);
        lfinder.setDownloader(downloader.newDownloader());
        lfinder.setProject(project);
        return lfinder;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    private void setLibraries(
        Map<Name, Library> libraries,
        Map<String, Library> rtLibraries,
        Library rtLibraryDefault
    ) {
        this.libraries.putAll(libraries);
        this.runtimeLibraries.putAll(rtLibraries);
        this.defaultRuntimeLibrary = rtLibraryDefault;
    }

    private void setDownloader(LibraryDownloader downloader) {
        this.downloader = (NuGetDownloader) downloader;
    }

    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public String getLanguage() {
        return CSharpConstants.CSHARP;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    @Override
    public Library getLibrary(MavenCoords coords) {
        return new NuGetLibrary(coords, downloader);
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return downloader.getLatestVersion(coords);
    }

    // ----------------------------------------------------------------------
    // Check maven coordinates
    // ----------------------------------------------------------------------

    public MavenCoords normalize(MavenCoords coords) {
        String version = coords.version;

        // if version contains some strange character, replace it with ""
        if (version.contains("$") || version.contains("(") || version.contains(","))
            version = "";

        // no version -> latest
        if (version.isEmpty())
            version = getLatestVersion(coords);

        if (!version.isEmpty())
            return MavenCoords.of(coords, version);
        else
            return coords;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // private static Pattern DOTNET_VERSION  = Pattern.compile("([0-9]+\\.[0-9]+)\\.[0-9]+");

    public void setNamedLibrary(String libraryName, String version, File libraryDirectory) {
        setNamedLibrary(libraryName, version, Collections.singletonList(libraryDirectory));
    }

    public void setNamedLibrary(String libraryName, String version, List<File> libraryFiles) {
        String[] names = libraryName.split(",");

        if (version.isEmpty())
            version = DotNetNormalizer.versionOf(libraryName);

        // check directories
        String ver = version;
        libraryFiles.forEach(libraryFile -> {
            if (!libraryFile.exists())
                logger.errorf("Runtime library %s:%s: Invalid directory %s", libraryName, ver, libraryFile);
        });

        for (String name : names) {
            name = name.trim();

            CSharpRuntimeLibrary rtLibrary = new CSharpRuntimeLibrary(name, version, libraryFiles);

            runtimeLibraries.put(name, rtLibrary);
            if (defaultRuntimeLibrary == null)
                defaultRuntimeLibrary = rtLibrary;
        }
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        Library rtLibrary = runtimeLibraries.get(libraryName);
        if (rtLibrary == null)
            rtLibrary = findBestRuntimeLibrary(libraryName);
        if (rtLibrary == null)
            rtLibrary = selectDefaultRuntimeLibrary(libraryName);

        return rtLibrary;
    }

    private Library findBestRuntimeLibrary(String libraryName) {
        String version = libraryVersion(libraryName);
        if (version.isEmpty())
            return null;

        Version libVersion = Version.of(version);
        Version selectedVersion = Version.of("10000.0");
        Library selectedLibrary = null;

        for(Library rtlib : getRuntimeLibraries()) {
            Version thisVersion = Version.of(rtlib.getVersion());

            // this version must be greater than library version and lower than the current
            // selected version
            if (thisVersion.compareTo(libVersion) > 0 && thisVersion.compareTo(selectedVersion) < 0) {
                selectedVersion = thisVersion;
                selectedLibrary = rtlib;
            }
        }

        if (selectedLibrary != null)
            logger.warnf("Requested %s, selected %s", libraryName, selectedLibrary.getName().getName());

        return selectedLibrary;
    }

    private Library selectDefaultRuntimeLibrary(String libraryName) {
        logger.warnf("Unable to retrieve the runtime library %s. Used the default %s",
                libraryName, defaultRuntimeLibrary.getName().getFullName());
        return defaultRuntimeLibrary;
    }

    // used internally
    public Library getRTLibrary(String libraryName) {
        return runtimeLibraries.get(libraryName);
    }

    @Override
    public Collection<Library> getRuntimeLibraries() {
        return runtimeLibraries.values();
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    public static String libraryVersion(String libraryName) {
        return DotNetNormalizer.versionOf(libraryName);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
