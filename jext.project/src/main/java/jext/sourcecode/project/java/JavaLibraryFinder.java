package jext.sourcecode.project.java;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Version;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.java.libraries.JDKLibrary;
import jext.sourcecode.project.java.maven.MavenLibrary;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static jext.sourcecode.project.java.JavaConstants.JAVA;

public class JavaLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------
    // A library is composed of a name and a version:
    //
    //      commons-math3-3.6.1.jat
    //      <libraryName>-<version>.jar
    //
    // If it is not specified the version, it is necessary to find the most
    // recent version. If it doesn't exists, it is possible to use the library
    // with name
    //
    //      <libraryName>.jar
    //
    // Warning: the JDK is composed of MULTIPLE jars, but it is referred as
    // a single library.
    // Can be a good idea to configure some directories as "aggregate libraries"
    // with a specific name, and, when it is necessary to load a library,
    // if this library is an 'aggregate library', loads the content of the directory
    // as a single library name
    //.

    private static Logger logger = Logger.getLogger(JavaLibraryFinder.class);

    private Project project;

    // Maven downloader
    private JavaLibraryDownloader downloader = new JavaLibraryDownloader();

    private String language;

    private Map<Name, Library> libraries = new HashMap<>();

    private Map<MavenCoords, Library> mavenLibraries = new HashMap<>();

    private Map<String, Library> runtimeLibraries = new HashMap<>();
    private Library defaultRuntimeLibrary;


    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JavaLibraryFinder() {
        this(JAVA);
    }

    public JavaLibraryFinder(String language) {
        this.language = language;
    }

    @Override
    public LibraryFinder newFinder(Project project) {
        JavaLibraryFinder lfinder = new JavaLibraryFinder(language);
        lfinder.setLibraries(libraries, mavenLibraries, runtimeLibraries, defaultRuntimeLibrary);
        lfinder.setDownloader(downloader.newDownloader());
        lfinder.setProject(project);
        return lfinder;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    private void setLibraries(
        Map<Name, Library> libraries,
        Map<MavenCoords, Library> mavenLibraries,
        Map<String, Library> runtimeLibraries,
        Library rtLibraryDefault
    ) {
        this.libraries.putAll(libraries);
        this.mavenLibraries.putAll(mavenLibraries);
        this.runtimeLibraries.putAll(runtimeLibraries);
        this.defaultRuntimeLibrary = rtLibraryDefault;
    }

    private void setDownloader(LibraryDownloader downloader) {
        this.downloader = (JavaLibraryDownloader) downloader;
    }

    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    /**
     * Add a 'named' library: it can be a file descriptor or a directory.
     * If it is a directory, the directory is scanned recursively for ".jar"
     * and ".jmod" files
     *
     * @param libraryName library name or name list comma separated
     * @param libraryDirectory library home directory
     */
    public void setNamedLibrary(String libraryName, String version, File libraryDirectory) {
        String[] names = libraryName.split(",");

        if (!libraryDirectory.exists())
            logger.errorf("Runtime library %s:%s: Invalid directory %s", libraryName, version, libraryDirectory);

        for (String name : names) {
            name = name.trim();

            Library runtimeLibrary = new JDKLibrary(name, version, libraryDirectory);
            runtimeLibraries.put(name, runtimeLibrary);

            if (defaultRuntimeLibrary == null)
                defaultRuntimeLibrary = runtimeLibrary;
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

    public static String libraryVersion(String libraryName) {
        String version = libraryName;
        if (!version.startsWith("jdk"))
            return "";
        // jdk1.8
        // jdk8
        version = version.substring("jdk".length());
        // jdk1.8
        if (version.startsWith("1."))
            version = version.substring("1.".length());
        return version;
    }

    private Library selectDefaultRuntimeLibrary(String libraryName) {
        logger.warnf("Unable to retrieve the runtime library %s. Used the default %s",
                libraryName, defaultRuntimeLibrary.getName().getFullName());
        return defaultRuntimeLibrary;
    }

    // used locally
    public Library getRTLibrary(String libraryName) {
        return runtimeLibraries.get(libraryName);
    }

    @Override
    public Collection<Library> getRuntimeLibraries() {
        return runtimeLibraries.values();
    }

    @Override
    public Library getLibrary(MavenCoords coords) {

        synchronized (mavenLibraries) {
            coords = downloader.getVersioned(coords);

            if (mavenLibraries.containsKey(coords))
                return mavenLibraries.get(coords);

            Library library = new MavenLibrary(coords, downloader, project);
            mavenLibraries.put(coords, library);
            libraries.put(library.getName(), library);
            return library;
        }
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
    // End
    // ----------------------------------------------------------------------

}
