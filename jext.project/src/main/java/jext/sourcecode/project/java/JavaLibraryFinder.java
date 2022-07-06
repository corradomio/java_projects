package jext.sourcecode.project.java;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.java.libraries.JDKLibrary;
import jext.sourcecode.project.java.maven.MavenLibrary;
import jext.util.StringUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

    // name -> directory
    // private Map<String, File> namedLibraries = new HashMap<>();

    // directory -> library
    // private Map<File, Library> runtimeLibraries = new HashMap<>();

    // maven coords -> library
    // private Map<MavenCoords, Library> mavenLibraries = new HashMap<>();

    private String language;

    private Map<Name, Library> libraries = new HashMap<>();

    private Map<String, Library> runtimeLibraries = new HashMap<>();

    private Map<MavenCoords, Library> mavenLibraries = new HashMap<>();


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
        lfinder.setProject(project);
        lfinder.setLibraries(libraries, runtimeLibraries, mavenLibraries);
        lfinder.setDownloader(downloader.newDownloader());
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
        Map<String, Library> runtimeLibraries,
        Map<MavenCoords, Library> mavenLibraries) {
        this.libraries.putAll(libraries);
        this.runtimeLibraries.putAll(runtimeLibraries);
        this.mavenLibraries.putAll(mavenLibraries);
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

    // /**
    //  * Set extra configurations
    //  */
    // @Override
    // public void configure(Parameters parameters) {
    //     parameters.keySet().forEach(name -> {
    //         if (name.startsWith("maven.repository"))
    //             addRepositories(parameters.getString(name));
    //     });
    // }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    /**
     * Add a 'named' library: it can me a file descriptor or a directory.
     * If it is a directory, the directory is scanned recursively for ".jar"
     * and ".jmod" files
     */
    public void setNamedLibrary(String libraryName, File libraryPath) {
        // if (namedLibraries.containsKey(libraryName)) {
        //     logger.warnf("Library %s already registered with %s (new: %s): SKIPPED",
        //         libraryName, namedLibraries.get(libraryName), libraryPath);
        // }
        // else {
        //     namedLibraries.put(libraryName, libraryPath);
        // }

        Library runtimeLibrary = new JDKLibrary(libraryName, libraryPath, null);
        runtimeLibraries.put(libraryName, runtimeLibrary);
    }

    // public void setNamedLibraries(Map<String, File> librariesMap){
    //     for(String libraryName : librariesMap.keySet()) {
    //         File libraryPath = librariesMap.get(libraryName);
    //         setNamedLibrary(libraryName, libraryPath);
    //     }
    // }

    // public JavaLibraryFinder addLibrary(String libraryName, String libraryPath) {
    //     return setNamedLibrary(libraryName, new File(libraryPath));
    // }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        Library rtLibrary = runtimeLibraries.get(libraryName);
        if (rtLibrary == null)
            throw new ProjectException(String.format("No runtime library with name %s for Java language", libraryName));
        return rtLibrary;
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
    public String getLatestVersion(String libraryName) {
        MavenCoords coords = MavenCoords.of(libraryName);
        return getLatestVersion(coords);
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return downloader.getLatestVersion(coords);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    // private void addRepositories(String listUrls) {
    //     List<String> repositories = StringUtils.split(listUrls,",");
    //     downloader.addRepositories(repositories);
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
