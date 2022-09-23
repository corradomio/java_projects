package jext.sourcecode.project.python.libraries;

import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.python.PythonConstants;
import jext.sourcecode.project.util.BaseLibrary;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;

public abstract class PythonLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonLibrary(Name name) {
        super(name);
        this.language = PythonConstants.PYTHON;
    }

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
