package jext.sourcecode.project.java;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.java.libraries.JDKLibrary;
import jext.sourcecode.project.java.maven.MavenLibrary;
import jext.util.Parameters;
import jext.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Logger logger;

    private Project project;

    // Maven downloader
    private JavaLibraryDownloader md = new JavaLibraryDownloader();

    // name -> directory
    private Map<String, File> namedLibraries = new HashMap<>();

    // directory -> library
    private Map<File, Library> rtLibraries = new HashMap<>();

    // maven coords -> library
    private Map<MavenCoords, Library> mavenLibraries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JavaLibraryFinder() {
        this.logger = Logger.getLogger(JavaLibraryFinder.class);
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public JavaLibraryFinder setProject(Project project) {
        this.project = project;
        return this;
    }

    public JavaLibraryFinder setDownloader(LibraryDownloader ld) {
        this.md = (JavaLibraryDownloader) ld;
        return this;
    }

    /**
     * Add a 'named' library: it can me a file descriptor or a directory.
     * If it is a directory, the directory is scanned recursively for ".jar"
     * and ".jmod" files
     */
    public JavaLibraryFinder setNamedLibrary(String libraryName, File libraryPath) {
        if (namedLibraries.containsKey(libraryName)) {
            logger.warnf("Library %s already registered with %s (new: %s): SKIPPED",
                libraryName, namedLibraries.get(libraryName), libraryPath);
        }
        else {
            namedLibraries.put(libraryName, libraryPath);
        }

        return this;
    }

    public JavaLibraryFinder setNamedLibraries(Map<String, File> librariesMap){
        for(String libraryName : librariesMap.keySet()) {
            File libraryPath = librariesMap.get(libraryName);
            setNamedLibrary(libraryName, libraryPath);
        }
        return this;
    }

    public JavaLibraryFinder addLibrary(String libraryName, String libraryPath) {
        return setNamedLibrary(libraryName, new File(libraryPath));
    }

    // @Override
    // public JavaLibraryFinder initialize() {
    //     return this;
    // }

    @Override
    public LibraryDownloader getDownloader() {
        return md.newDownloader();
    }

    // ----------------------------------------------------------------------
    // Library handling
    // ----------------------------------------------------------------------

    /**
     * Set extra configurations
     */
    @Override
    public LibraryFinder configure(Parameters parameters) {
        parameters.keySet().forEach(name -> {
            if (name.startsWith("maven.repository"))
                addRepositories(parameters.getString(name));
        });
        return this;
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {

        synchronized(namedLibraries) {

            // library not defined
            if (!namedLibraries.containsKey(libraryName)) {
                logger.errorf("Library %s not defined", libraryName);
                return null;
            }

            // library already registered
            File libraryPath = namedLibraries.get(libraryName);
            if (rtLibraries.containsKey(libraryPath))
                return rtLibraries.get(libraryPath);

            // register the library
            Library rtLibrary = new JDKLibrary(libraryName, libraryPath, project);
            rtLibraries.put(libraryPath, rtLibrary);

            return rtLibrary;
        }
    }

    @Override
    public Library getLibrary(MavenCoords coords) {

        synchronized (mavenLibraries) {
            coords = md.getVersioned(coords);

            if (mavenLibraries.containsKey(coords))
                return mavenLibraries.get(coords);

            Library library = new MavenLibrary(coords, md, project);
            mavenLibraries.put(coords, library);
            return library;
        }
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        // if (!library.getLibraryType().equals(LibraryType.MAVEN))
        //     return library.getVersion();
        //
        // MavenCoords coords = MavenCoords.of(library.getName().getFullName());
        return md.getLatestVersion(coords);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void addRepositories(String listUrls) {
        List<String> repositories = StringUtils.split(listUrls,",");
        md.addRepositories(repositories);
    }

}