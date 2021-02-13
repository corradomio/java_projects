package jext.sourcecode.libraries;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.util.Parameters;

import java.io.File;
import java.util.Arrays;
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

    private static final String DESCRIPTOR     = ".descriptor";
    private static final String DESCRIPTOR_GZ  = ".descriptor.gz";
    private static final String DESCRIPTOR_ZIP = ".descriptor.zip";

    private Logger logger;

    private Project project;

    // Maven downloader
    private MavenDownloader downloader;

    // name -> directory
    private Map<String, File> namedLibraries = new HashMap<>();

    // directory -> library
    private Map<File, Library> directoryLibraries = new HashMap<>();

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


    public JavaLibraryFinder setDownloader(MavenDownloader downloader) {
        this.downloader = downloader;
        return this;
    }

    // /**
    //  * Add the descriptors available in the specified directory.
    //  * The descriptor files have extensions:
    //  *
    //  *      .descriptor
    //  *      .descriptor.gz
    //  *      .descriptor.zip
    //  *
    //  * @param librariesDir directory
    //  * @return this
    //  */
    // public JavaLibraryFinder setLibrariesDir(File librariesDir) {
    //     addDescriptors(librariesDir, DESCRIPTOR_GZ);
    //     addDescriptors(librariesDir, DESCRIPTOR);
    //     addDescriptors(librariesDir, DESCRIPTOR_ZIP);
    //
    //     return this;
    // }

    // private void addDescriptors(File librariesDir, String ext) {
    //     FileUtils.listFiles(librariesDir, ext)
    //         .forEach(libraryFile -> {
    //             String libraryName = libraryFile.getName();
    //             libraryName = libraryName.substring(0, libraryName.length() - ext.length());
    //
    //             namedLibrary(libraryName, libraryFile);
    //         });
    // }

    // public JavaLibraryFinder addLibrariesDirs(List<File> librariesDirs) {
    //     librariesDirs.forEach(this::setLibrariesDir);
    //     return this;
    // }

    /**
     * Add a 'named' library: it can me a file descriptor or a directory.
     * If it is a directory, the directory is scanned recursively for ".jar"
     * and ".jmod" files
     */
    public void setNamedLibrary(String libraryName, File libraryPath) {
        if (namedLibraries.containsKey(libraryName)) {
            logger.warnf("Library %s already registered with %s (new: %s): SKIPPED",
                libraryName, namedLibraries.get(libraryName), libraryPath);
        }
        else {
            namedLibraries.put(libraryName, libraryPath);
        }
    }

    public JavaLibraryFinder setNamedLibraries(Map<String, File> librariesMap){
        for(String libraryName : librariesMap.keySet()) {
            File libraryPath = librariesMap.get(libraryName);
            setNamedLibrary(libraryName, libraryPath);
        }
        return this;
    }

    public JavaLibraryFinder initialize() {
        return this;
    }

    @Override
    public MavenDownloader getDownloader() {
        return downloader;
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
    public  Library getLibrary(String libraryName) {

        synchronized(namedLibraries) {

            // library not defined
            if (!namedLibraries.containsKey(libraryName)) {
                logger.errorf("Library %s not defined", libraryName);
                return null;
            }

            // library already registered
            File libraryPath = namedLibraries.get(libraryName);
            if (directoryLibraries.containsKey(libraryPath))
                return directoryLibraries.get(libraryPath);

            // register the library
            Library library = new DirectoryLibrary(libraryName, libraryPath, project);
            directoryLibraries.put(libraryPath, library);

            return library;
        }
    }

    @Override
    public Library getLibrary(MavenCoords coords) {

        synchronized (mavenLibraries) {
            coords = downloader.getVersioned(coords);

            if (mavenLibraries.containsKey(coords))
                return mavenLibraries.get(coords);

            Library library = new MavenLibrary(coords, downloader, project);
            mavenLibraries.put(coords, library);
            return library;
        }

    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void addRepositories(String listUrls) {
        List<String> repositories = Arrays.asList(listUrls.split(","));
        downloader.addRepositories(repositories);
    }

}
