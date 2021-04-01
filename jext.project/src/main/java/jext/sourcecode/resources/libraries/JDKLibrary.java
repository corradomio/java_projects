package jext.sourcecode.resources.libraries;

import jext.lang.JavaUtils;
import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RuntimeLibrary;

import java.io.File;

public class JDKLibrary extends DirectoryLibrary implements RuntimeLibrary {

    public JDKLibrary(String libraryName, File libraryFile, Project project) {
        super(libraryName, libraryFile, project);
    }

    @Override
    public boolean contains(Name typeName) {
        if (JavaUtils.PRIMITIVE_TYPES.contains(typeName.getFullName()))
            return true;
        else
            return super.contains(typeName);
    }

}
