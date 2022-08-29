package jext.sourcecode.project.java.libraries;

import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.java.JavaConstants;
import jext.sourcecode.project.util.BaseLibrary;

public abstract class JavaLibrary extends BaseLibrary {

    protected JavaLibrary(Name libraryName, Project project) {
        super(libraryName, project);
        this.language = JavaConstants.JAVA;
    }

}
