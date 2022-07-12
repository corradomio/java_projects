package jext.sourcecode.project.csharp.libraries;

import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.sourcecode.project.util.BaseLibrary;

public abstract class CSharpLibrary extends BaseLibrary {

    protected CSharpLibrary(Name libraryName) {
        super(libraryName);
        this.language = CSharpConstants.CSHARP;
    }

    protected CSharpLibrary(Name libraryName, Project project) {
        super(libraryName, project);
        this.language = CSharpConstants.CSHARP;
    }
}
