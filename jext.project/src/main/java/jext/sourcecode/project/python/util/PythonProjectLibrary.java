package jext.sourcecode.project.python.util;

import jext.name.Name;
import jext.sourcecode.project.python.PythonConstants;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.util.BaseLibrary;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PythonProjectLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonProjectLibrary(Project project) {
        super(project.getName(), project);
        this.libraryFile = project.getProjectHome();
        this.libraryType = LibraryType.LOCAL;
        this.language = PythonConstants.PYTHON;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public List<File> getFiles() {
        return project.getModules().getModule().getSources().getSourceRootDirectories();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
