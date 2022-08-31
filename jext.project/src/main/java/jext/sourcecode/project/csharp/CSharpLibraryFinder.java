package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.libraries.CSharpLocalLibrary;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.sourcecode.project.csharp.libraries.NuGetLibrary;
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
    private Library rtLibraryDefault;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        CSharpLibraryFinder lfinder = new CSharpLibraryFinder();
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
                "%1$s/%2$s",
                coords.artifactId,
                coords.version);

        File libraryDirectory = new File(downloader.getDownloadDirectory(), relativePath);
        return new NuGetLibrary(coords, libraryDirectory);
    }

    @Override
    public String getLatestVersion(String libraryName) {
        return "";
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return "";
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void setNamedLibrary(String libraryName, File libraryDirectory) {
        setNamedLibrary(libraryName, Collections.singletonList(libraryDirectory));
    }

    public void setNamedLibrary(String libraryName, List<File> libraryDirectories) {
        setNamedLibrary(libraryName, "", libraryDirectories);
    }

    public void setNamedLibrary(String libraryName, String version, List<File> libraryDirectories) {
        CSharpRuntimeLibrary rtLibrary = new CSharpRuntimeLibrary(libraryName, version, libraryDirectories);

        runtimeLibraries.put(libraryName, rtLibrary);
        if (rtLibraryDefault == null)
            rtLibraryDefault = rtLibrary;
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
    // End
    // ----------------------------------------------------------------------

}
