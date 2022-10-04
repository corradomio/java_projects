package jext.sourcecode.project.csharp.libraries;

import jext.name.Name;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.sourcecode.project.util.BaseLibrary;

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
