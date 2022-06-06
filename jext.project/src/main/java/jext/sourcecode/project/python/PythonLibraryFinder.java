package jext.sourcecode.project.python;

import jext.maven.MavenCoords;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.python.libraries.PythonLibrary;
import jext.sourcecode.project.python.libraries.PythonRTLibrary;
import jext.util.FileUtils;
import jext.util.HashMap;
import jext.util.Parameters;

import java.io.File;
import java.util.Map;

public class PythonLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private LibraryDownloader downloader = new PythonLibraryDownloader();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------


    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public PythonLibraryFinder setProject(Project project) {
        this.project = project;
        return this;
    }

    @Override
    public PythonLibraryFinder configure(Parameters params) {
        return this;
    }

    // @Override
    // public PythonLibraryFinder initialize() {
    //     return this;
    // }

    @Override
    public Library getLibrary(MavenCoords coords) {
        return null;
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return null;
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        Name name = PathName.of(libraryName);
        return libraries.get(name);
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    @Override
    public LibraryFinder setDownloader(LibraryDownloader ld) {
        this.downloader = ld;
        return this;
    }

    public PythonLibraryFinder addLibraries(File librariesRoot) {
        // File librariesRoot = new File(librariesPath);
        FileUtils.asList(librariesRoot.listFiles(File::isDirectory))
            .forEach(this::addLibrary);
        return this;
    }

    public PythonLibraryFinder setNamedLibrary(String libraryName, File libraryDirectory) {
        Library pyLibrary = createLibrary(libraryDirectory);
        libraries.put(pyLibrary.getName(), pyLibrary);
        libraries.put(PathName.of(libraryName), pyLibrary);
        return this;
    }

    public LibraryFinder addLibrary(File libraryDirectory) {
        // skip "__pycache__" directory
        if (libraryDirectory.getName().startsWith("__"))
            return this;

        Library pyLibrary = createLibrary(libraryDirectory);
        libraries.put(pyLibrary.getName(), pyLibrary);
        return this;
    }

    private Library createLibrary(File libraryDirectory) {
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

    public PythonLibraryFinder setNamedLibraries(Map<String, File> librariesMap){
        for(String libraryName : librariesMap.keySet()) {
            File libraryPath = librariesMap.get(libraryName);
            setNamedLibrary(libraryName, libraryPath);
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
