package jext.sourcecode.project.python.libraries;

import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.python.PythonConstants;
import jext.sourcecode.project.util.BaseLibrary;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PythonLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonLibrary(File libraryDirectory) {
        super(PathName.of(libraryDirectory.getName()));
        this.libraryFile = libraryDirectory;
        this.language = PythonConstants.PYTHON;
        this.libraryType = LibraryType.LOCAL;
    }

    public PythonLibrary(Name name) {
        super(name);
        this.language = PythonConstants.PYTHON;
    }

    //public PythonLibrary(MavenCoords coords, File libraryDirectory) {
    //    super(MavenName.of(coords));
    //    this.version = coords.version;
    //    this.libraryFile = libraryDirectory;
    //    this.language = PythonConstants.PYTHON;
    //    this.libraryType = LibraryType.REMOTE;
    //
    //    this.pythonFiles = Arrays.asList(libraryDirectory);
    //}

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return libraryFile.isDirectory();
    }

    public void setProject(Project project) {
        this.project = project;
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

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public void setLibraryName(String name) {
        setNameWithId(PathName.of(name));
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
