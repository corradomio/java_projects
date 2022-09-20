package jext.sourcecode.project.csharp.libraries;

import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CSharpLocalLibrary extends CSharpLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpLocalLibrary(
            Name libraryName,
            File libraryFile) {
        super(libraryName);
        this.libraryFile = libraryFile.getParentFile();
        this.libraryFiles = Collections.singletonList(libraryFile);
        this.libraryType = LibraryType.LOCAL;
    }

    public CSharpLocalLibrary(
        Name libraryName,
        File libraryDirectory,
        List<File> libraryFiles) {
        super(libraryName);
        this.libraryFile = libraryDirectory;
        this.libraryFiles = libraryFiles;
        this.libraryType = LibraryType.LOCAL;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return getFile().exists();
    }

    @Override
    public List<File> getFiles() {
        return libraryFiles;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
