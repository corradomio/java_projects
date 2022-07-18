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

    private List<File> libraryFiles;

    public CSharpLocalLibrary(
        Name libraryName,
        File libraryDirectory,
        List<File> libraryFiles) {
        super(libraryName);
        this.libraryFile = libraryDirectory;
        this.libraryFiles = libraryFiles;
        this.libraryType = LibraryType.LOCAL;
    }

    @Override
    public boolean isValid() {
        return getFile().exists();
    }

    @Override
    public List<File> getFiles() {
        return libraryFiles;
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
