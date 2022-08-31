package jext.sourcecode.project.python;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.python.libraries.PyPiLibrary;
import jext.sourcecode.project.python.libraries.PythonLibrary;
import jext.sourcecode.project.python.libraries.PythonRTLibrary;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PythonLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(PythonLibraryFinder.class);

    // project owner of this finder
    private Project project;

    private final Map<Name, Library> libraries = new HashMap<>();
    private final Map<String, Library> runtimeLibraries = new HashMap<>();
    private PyPiDownloader downloader = new PyPiDownloader();
    private Library rtLibraryDefault;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        PythonLibraryFinder lfinder = new PythonLibraryFinder();
        lfinder.setProject(project);
        lfinder.setLibraries(libraries, runtimeLibraries, rtLibraryDefault);
        lfinder.setDownloader(downloader.newDownloader());
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
        Map<String, Library> runtimeLibraries,
        Library rtLibraryDefault
    ) {
        this.libraries.putAll(libraries);
        this.runtimeLibraries.putAll(runtimeLibraries);
        this.rtLibraryDefault = rtLibraryDefault;
    }

    private void setDownloader(LibraryDownloader downloader) {
        this.downloader = (PyPiDownloader) downloader;
    }

    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public String getLanguage() {
        return PythonConstants.PYTHON;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Library getLibrary(MavenCoords coords) {
        // [downloadDir]/<artifactId>/<version>
        String relativePath = String.format(
                "%1$s/%2$s",
                coords.artifactId,
                coords.version);

        File libraryDirectory = new File(downloader.getDownloadDirectory(), relativePath);
        return new PyPiLibrary(coords, libraryDirectory);
    }

    @Override
    public String getLatestVersion(String libraryName) {
        return getLatestVersion(MavenCoords.of(libraryName, ""));
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return downloader.getLatestVersion(coords);
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

    @Override
    public Collection<Library> getRuntimeLibraries() {
        return runtimeLibraries.values();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public void setNamedLibrary(String libraryName, File libraryDirectory) {
        PythonLibrary library = createLibrary(libraryDirectory);
        library.setLibraryName(libraryName);
        if (library.getLibraryType() == LibraryType.RUNTIME) {
            runtimeLibraries.put(library.getName().getFullName(), library);
            runtimeLibraries.put(libraryName, library);
        }
        else {
            libraries.put(library.getName(), library);
            libraries.put(PathName.of(libraryName), library);
        }
    }

    public void addLibraries(File librariesRoot) {
        // File librariesRoot = new File(librariesPath);
        FileUtils.asList(librariesRoot.listFiles(File::isDirectory))
            .forEach(this::addLibrary);
    }

    public void addLibrary(File libraryDirectory) {
        // skip "__pycache__" directory
        if (libraryDirectory.getName().startsWith("__"))
            return;

        Library pyLibrary = createLibrary(libraryDirectory);
        libraries.put(pyLibrary.getName(), pyLibrary);
    }

    private PythonLibrary createLibrary(File libraryDirectory) {
        // IF the directory contains "python.exe", it is the ""runtime library""
        File winPython = new File(libraryDirectory, "python.exe");
        File linPython = new File(libraryDirectory, "bin/python");
        if (winPython.exists() || linPython.exists()) {
            return new PythonRTLibrary(libraryDirectory.getName(), libraryDirectory);
        }
        else {
            return new PythonLibrary(libraryDirectory);
        }
    }

    // public void setNamedLibraries(Map<String, File> librariesMap){
    //     for(String libraryName : librariesMap.keySet()) {
    //         File libraryPath = librariesMap.get(libraryName);
    //         setNamedLibrary(libraryName, libraryPath);
    //     }
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
