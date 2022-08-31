package jext.sourcecode.project.csharp.libraries;

import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.sourcecode.project.util.BaseLibrary;

import java.io.File;
import java.util.List;

public abstract class CSharpLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected CSharpLibrary(Name libraryName) {
        super(libraryName);
        this.language = CSharpConstants.CSHARP;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
