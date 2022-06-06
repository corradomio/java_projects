package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.sourcecode.project.java.JavaLibraryFinder;
import jext.util.HashMap;
import jext.util.Parameters;

import java.io.File;
import java.util.Map;

public class CSharpLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(CSharpLibraryFinder.class);

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private LibraryDownloader downloader = new CSharpLibraryDownloader();

    // name -> directory
    private Map<String, File> namedLibraries = new java.util.HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------


    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public LibraryFinder setProject(Project project) {
        this.project =  project;
        return this;
    }

    @Override
    public LibraryFinder setDownloader(LibraryDownloader ld) {
        this.downloader = ld;
        return this;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    @Override
    public LibraryFinder configure(Parameters params) {
        return this;
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        return new CSharpRuntimeLibrary(libraryName, "0.0", new File("."));
    }

    @Override
    public Library getLibrary(MavenCoords coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        throw new UnsupportedOperationException();
    }

    public CSharpLibraryFinder setNamedLibraries(Map<String, File> librariesMap) {
        for(String libraryName : librariesMap.keySet()) {
            File libraryPath = librariesMap.get(libraryName);
            setNamedLibrary(libraryName, libraryPath);
        }
        return this;
    }

    public CSharpLibraryFinder setNamedLibrary(String libraryName, File libraryPath) {
        if (namedLibraries.containsKey(libraryName)) {
            logger.warnf("Library %s already registered with %s (new: %s): SKIPPED",
                libraryName, namedLibraries.get(libraryName), libraryPath);
        }
        else {
            namedLibraries.put(libraryName, libraryPath);
        }

        return this;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
