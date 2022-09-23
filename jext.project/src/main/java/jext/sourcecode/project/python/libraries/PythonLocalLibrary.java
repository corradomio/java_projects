package jext.sourcecode.project.python.libraries;

import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.python.PythonConstants;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;

public class PythonLocalLibrary extends PythonLibrary {

    public PythonLocalLibrary(String name, String version, File libraryDirectory) {
        super(PathName.of(name));
        this.version = version;
        this.libraryFile = libraryDirectory;
        this.libraryType = LibraryType.LOCAL;
    }

    @Override
    public List<File> getFiles() {
        if (libraryFiles == null)
            populate();
        return libraryFiles;
    }

    private void populate() {
        libraryFiles = FileUtils.listFiles(libraryFile, PythonConstants.PYTHON_EXT);
    }

}
