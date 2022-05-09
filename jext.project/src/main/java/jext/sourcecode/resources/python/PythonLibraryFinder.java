package jext.sourcecode.resources.python;

import jext.maven.MavenCoords;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.resources.python.libraries.PythonLibrary;
import jext.sourcecode.resources.python.libraries.PythonRTLibrary;
import jext.util.FileUtils;
import jext.util.HashMap;
import jext.util.Parameters;

import java.io.File;
import java.util.Map;

public class PythonLibraryFinder implements LibraryFinder {

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private PythonLibraryDownloader pld = new PythonLibraryDownloader();

    @Override
    public LibraryFinder setProject(Project project) {
        this.project = project;
        return this;
    }

    @Override
    public LibraryFinder configure(Parameters params) {
        return this;
    }

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
    public LibraryDownloader getLibraryDownloader() {
        return pld;
    }

    public PythonLibraryFinder addLibraries(String librariesPath) {
        File librariesRoot = new File(librariesPath);
        FileUtils.asList(librariesRoot.listFiles(File::isDirectory))
            .forEach(this::addLibrary);
        return this;
    }

    public PythonLibraryFinder addLibrary(String libraryName, String libraryDirectory) {
        Library pyLibrary = createLibrary(new File(libraryDirectory));
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
}
