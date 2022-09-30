package jext.sourcecode.project.python;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Version;
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
    private Library defaultRuntimeLibrary;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        PythonLibraryFinder lfinder = new PythonLibraryFinder();
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
        Map<String, Library> runtimeLibraries,
        Library rtLibraryDefault
    ) {
        this.libraries.putAll(libraries);
        this.runtimeLibraries.putAll(runtimeLibraries);
        this.defaultRuntimeLibrary = rtLibraryDefault;
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
                "%s/%s",
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

        return selectedLibrary;
    }

    private static String libraryVersion(String libraryName) {
        String version = libraryName;
        if (!version.startsWith("py"))
            return "";
        // python3.8
        if (version.startsWith("python"))
            version = version.substring("python".length());
        if (version.startsWith("py"))
            version = version.substring("py".length());
        // 38
        // 3.8
        if (version.contains(".") || version.length() == 1)
            return version;
        if (version.startsWith("3"))
            return version.charAt(0) + "." + version.substring(1);
        else
            return version;
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

            if (defaultRuntimeLibrary == null)
                defaultRuntimeLibrary = runtimeLibrary;
        }

    }

    private PythonLibrary createLibrary(String libraryName, String version, File libraryDirectory) {
        // IF the directory contains "python.exe", it is the ""runtime library""
        if (!libraryDirectory.exists() || libraryDirectory.isFile())
            logger.errorf("Runtime library %s:%s: Invalid directory %s", libraryName, version, libraryDirectory);

        File winPython = new File(libraryDirectory, "python.exe");
        File linPython = new File(libraryDirectory, "bin/python");
        if (winPython.exists() || linPython.exists()) {
            return new PythonRTLibrary(libraryName, version, libraryDirectory);
        }
        else {
            return new PythonLocalLibrary(libraryName, version, libraryDirectory);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
