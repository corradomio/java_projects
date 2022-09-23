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
import jext.sourcecode.project.python.libraries.PythonLocalLibrary;
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

    // used internally
    public Library getRTLibrary(String libraryName) {
        return runtimeLibraries.get(libraryName);
    }

    @Override
    public Collection<Library> getRuntimeLibraries() {
        return runtimeLibraries.values();
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
    // Implementation
    // ----------------------------------------------------------------------

    /**
     * Add a 'named' library
     *
     * @param libraryName library name or name list comma separated
     * @param libraryDirectory library home directory
     */
    public void setNamedLibrary(String libraryName, String version, File libraryDirectory) {
        String[] names = libraryName.split(",");
        PythonLibrary runtimeLibrary = createLibrary(libraryName, version, libraryDirectory);

        for (String name : names) {
            name = name.trim();

            runtimeLibrary.setLibraryName(name);

            if (runtimeLibrary.getLibraryType() == LibraryType.RUNTIME) {
                runtimeLibraries.put(runtimeLibrary.getName().getFullName(), runtimeLibrary);
                runtimeLibraries.put(name, runtimeLibrary);
            }
            else {
                libraries.put(runtimeLibrary.getName(), runtimeLibrary);
                libraries.put(PathName.of(name), runtimeLibrary);
            }

            if (rtLibraryDefault == null)
                rtLibraryDefault = runtimeLibrary;
        }

    }

    private PythonLibrary createLibrary(String name, String version, File libraryDirectory) {
        // IF the directory contains "python.exe", it is the ""runtime library""
        File winPython = new File(libraryDirectory, "python.exe");
        File linPython = new File(libraryDirectory, "bin/python");
        if (winPython.exists() || linPython.exists()) {
            return new PythonRTLibrary(name, version, libraryDirectory);
        }
        else {
            return new PythonLocalLibrary(name, version, libraryDirectory);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
