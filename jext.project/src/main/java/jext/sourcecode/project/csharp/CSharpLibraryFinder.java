package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.util.HashMap;
import jext.util.Parameters;

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
    private Map<String, Library> runtimeLibraries = new HashMap<>();
    private NuGetDownloader downloader = new NuGetDownloader();

    // name -> directory
    // private Map<String, File> namedLibraries = new java.util.HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        CSharpLibraryFinder lfinder = new CSharpLibraryFinder();
        lfinder.setProject(project);
        lfinder.setLibraries(libraries, runtimeLibraries);
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

    private void setLibraries(Map<Name, Library> libraries, Map<String, Library> rtLibraries) {
        this.libraries.putAll(libraries);
        this.runtimeLibraries.putAll(rtLibraries);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void setNamedLibrary(String libraryName, String version, List<File> libraryDirectories) {
        CSharpRuntimeLibrary rtLibrary = new CSharpRuntimeLibrary(libraryName, version, libraryDirectories);

        runtimeLibraries.put(libraryName, rtLibrary);
    }

    // public void setNamedLibrary(String libraryName, File libraryDirectory) {
    //     Library library = new CSharpRuntimeLibrary(libraryName, libraryDirectory);
    //     setNamedLibrary(library);
    // }
    //
    // public void setNamedLibrary(Library library) {
    //     setNamedLibrary(library.getName().getFullName(), library);
    // }
    //
    // public void setNamedLibrary(String libraryName, Library library) {
    //     rtLibraries.put(libraryName, library);
    // }

    // public void setNamedLibraries(Map<String, File> librariesMap){
    //     for(String libraryName : librariesMap.keySet()) {
    //         File libraryPath = librariesMap.get(libraryName);
    //         setNamedLibrary(libraryName, "1.0", Collections.singletonList(libraryPath));
    //     }
    // }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        // return new CSharpRuntimeLibrary(libraryName, "0.0", new File("."));
        Library rtLibrary = runtimeLibraries.get(libraryName);
        if (rtLibrary == null)
            throw new ProjectException(String.format("No runtime library with name %s for %s language", libraryName, getLanguage()));
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
