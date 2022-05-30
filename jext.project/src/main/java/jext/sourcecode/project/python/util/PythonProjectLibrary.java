package jext.sourcecode.project.python.util;

import jext.name.Name;
import jext.python.PythonConstants;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.resources.BaseLibrary;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PythonProjectLibrary extends BaseLibrary implements Library {

    public PythonProjectLibrary(Project project) {
        super(project.getName(), project);
        this.libraryFile = project.getProjectHome();
        this.libraryType = LibraryType.LOCAL;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getLanguage() {
        return PythonConstants.PYTHON;
    }

    @Override
    public List<File> getFiles() {
        return project.getModules().getModule().getSources().getSourceRootDirectories();
    }

    @Override
    public Set<RefType> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean contains(Name typeName) {
        return false;
    }
}
