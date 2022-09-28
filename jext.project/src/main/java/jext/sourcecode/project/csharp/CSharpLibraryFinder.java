package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.sourcecode.project.csharp.libraries.NuGetLibrary;
import jext.util.HashMap;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSharpLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(CSharpLibraryFinder.class);

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private NuGetDownloader downloader = new NuGetDownloader();
    private Map<String, Library> runtimeLibraries = new HashMap<>();
    private Library rtLibraryDefault;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        CSharpLibraryFinder lfinder = new CSharpLibraryFinder();
        lfinder.setLibraries(libraries, runtimeLibraries, rtLibraryDefault);
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
        this.rtLibraryDefault = rtLibraryDefault;
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
        // [downloadDir]/<artifactId>/<version>
        String relativePath = String.format(
                "%s/%s",
                coords.artifactId.toLowerCase(),
                coords.version.toLowerCase());

        File libraryDirectory = new File(downloader.getDownloadDirectory(), relativePath);
        return new NuGetLibrary(coords, libraryDirectory);
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

    private static Pattern DOTNET_VERSION  = Pattern.compile("([0-9]+\\.[0-9]+)\\.[0-9]+");

    public void setNamedLibrary(String libraryName, String version, File libraryDirectory) {
        setNamedLibrary(libraryName, version, Collections.singletonList(libraryDirectory));
    }

    public void setNamedLibrary(String libraryName, String version, List<File> libraryDirectories) {
        String[] names = libraryName.split(",");

        // check directories
        libraryDirectories.forEach(libraryDirectory -> {
            if (!libraryDirectory.exists())
                logger.errorf("Runtime library %s:%s: Invalid directory %s", libraryName, version, libraryDirectory);
        });

        for (String name : names) {
            name = name.trim();

            CSharpRuntimeLibrary rtLibrary = new CSharpRuntimeLibrary(name, version, libraryDirectories);

            runtimeLibraries.put(name, rtLibrary);
            if (rtLibraryDefault == null)
                rtLibraryDefault = rtLibrary;
        }
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        Library rtLibrary = runtimeLibraries.get(libraryName);
        if (rtLibrary == null) {
            rtLibrary = rtLibraryDefault;
            logger.warnf("Unable to retrieve the runtime library %s. Used the default %s",
                libraryName, rtLibrary.getName().getFullName());
        }
        return rtLibrary;
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
    // End
    // ----------------------------------------------------------------------

}
